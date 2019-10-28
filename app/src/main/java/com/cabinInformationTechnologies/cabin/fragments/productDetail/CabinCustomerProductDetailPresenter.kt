package com.cabinInformationTechnologies.cabin.fragments.productDetail

import android.app.Activity
import android.content.Context
import android.os.Bundle

class CabinCustomerProductDetailPresenter(var view: CabinCustomerProductDetailContracts.View?) :
    CabinCustomerProductDetailContracts.Presenter,
    CabinCustomerProductDetailContracts.InteractorOutput {

    var interactor: CabinCustomerProductDetailContracts.Interactor? =
        CabinCustomerProductDetailInteractor(this)
    var router: CabinCustomerProductDetailContracts.Router? = null

    private var selectedColor: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor? = null
    private var selectedSize: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize? = null
    private lateinit var product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct
    private var initialColor: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor? = null
    private var initialColorIsPicked = false

    private var colorsDataset : MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor> = mutableListOf()
    private var sizesDataset: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize> = mutableListOf()
    private var colorSizesDataset : MutableMap<Int ,MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize>> = mutableMapOf()

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerProductDetailRouter(activity)

        bundle?.let {
            //you can delete this if there's no need to get extras from the intent
            //TODO: Do something
        }
    }

    override fun onDestroy() {
        view = null
        interactor?.unregister()
        interactor = null
        router?.unregister()
        router = null
    }

    //endregion

    //region Presenter

    override fun requestProduct(context: Context, id: Int) {
        interactor?.requestProduct(context, id)
    }

    override fun setInitialColor(color: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor?) {
        initialColor = color
        initialColorIsPicked = color != null
    }

    override fun setProduct(product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct) {
        this.product = product
        view?.setupProductDetail(product)
        setupDatasets()
    }

    override fun addToCart(context: Context,
                           productId: Int,
                           amount: Int,
                           colorId: Int,
                           sizeId: Int
    ){
        interactor?.addToCart(context,
            productId,
            amount,
            colorId,
            sizeId
            )
    }

    override fun addToCartButtonListener(context: Context) {
        if (selectedSize != null) {
            try {
                val selectedColor = selectedColor
                val selectedSize = selectedSize
                if (selectedColor != null && selectedSize != null) {
                    view?.addToCart(product.getId(), 1, selectedColor.id, selectedSize.id)
                }
            } catch (exception: Exception) {
                com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                    context,
                    this::class.java.name,
                    "SelectedSize is null!!",
                    exception)
            }
        } else {
            val selectedColor = selectedColor
            if (selectedColor != null)
                view?.showSelectSizeFor(
                    product,
                    selectedColor,
                    object : com.cabinInformationTechnologies.cabin.MainContracts.SelectSizeCallback{
                        override fun selectSize(size: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize) {
                            view?.indicateSelectedSize(size)
                            setSelectedSize(size)
                        }

                        override fun confirm() {
                            val selectedSize = selectedSize
                            if (selectedSize != null)
                                addToCart(
                                    context,
                                    product.getId(),
                                    1,
                                    selectedColor.id,
                                    selectedSize.id
                                )
                        }
                    })
        }
    }

    override fun setupDatasets() {
        colorsDataset  = mutableListOf()
        sizesDataset = mutableListOf()
        colorSizesDataset  = mutableMapOf()
        if (!initialColorIsPicked)
            initialColor = null
        val colors = product.getColors()
        colors.forEach {modelColor ->
            val colorSizes: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize> = mutableListOf()
            modelColor.sizes.forEach{ modelSize ->
                colorSizes.add(modelSize)
            }

            if (initialColor == null)
                initialColor = modelColor


            colorsDataset.add(modelColor)
            colorSizesDataset[modelColor.id] = colorSizes
        }
        view?.setupColors(colorsDataset)
        val color = initialColor
        if (color != null) {
            view?.setSelectedColor(color)
            view?.setupSizes(sizesDataset, color.id)
            view?.setTickOnColor(color)
        }
        val size = selectedSize
        if (size != null)
            view?.indicateSelectedSize(size)
    }

    override fun setSelectedColor(color: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor) {
        selectedColor = color
    }

    override fun setSelectedSize(size: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize?) {
        selectedSize = size
    }

    override fun setSizesDataset(sizesDataset: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize>) {
        this.sizesDataset = sizesDataset
    }

    override fun getSizesOfColor(id: Int): MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize>? = colorSizesDataset[id]

    //endregion

    //region InteractorOutput

    override fun showMessage(message: String?) {
        if (message == null)
            view?.showDefaultMessage()
        else
            view?.showMessage(message, false)//FIXME: ISSUCCESSFUL?
    }

    override fun updateProduct(product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct) {
        setProduct(product)
    }

    override fun setupFavoriteButton(isFavorite: Boolean) {
        if (isFavorite)
            view?.checkFavorite()
        else
            view?.uncheckFavorite()
    }

    override fun addToFavorite(context: Context, product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct, color: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor) {
        interactor?.addFavorite(context, product, color)
    }

    override fun removeFromFavorite(context: Context, product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct, color: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor) {
        interactor?.removeFavorite(context, product, color)
    }

    //endregion
}