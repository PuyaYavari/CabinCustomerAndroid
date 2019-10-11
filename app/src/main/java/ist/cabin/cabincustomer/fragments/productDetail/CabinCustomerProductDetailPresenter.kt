package ist.cabin.cabincustomer.fragments.productDetail

import android.app.Activity
import android.content.Context
import android.os.Bundle
import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.local.MODELColor
import ist.cabin.cabinCustomerBase.models.local.MODELProduct
import ist.cabin.cabinCustomerBase.models.local.MODELSize

class CabinCustomerProductDetailPresenter(var view: CabinCustomerProductDetailContracts.View?) :
    CabinCustomerProductDetailContracts.Presenter,
    CabinCustomerProductDetailContracts.InteractorOutput {

    var interactor: CabinCustomerProductDetailContracts.Interactor? =
        CabinCustomerProductDetailInteractor(this)
    var router: CabinCustomerProductDetailContracts.Router? = null

    private var selectedColor: MODELColor? = null
    private var selectedSize: MODELSize? = null
    private lateinit var product: MODELProduct
    private var initialColor: MODELColor? = null

    private var colorsDataset : MutableList<MODELColor> = mutableListOf()
    private var sizesDataset: MutableList<MODELSize> = mutableListOf()
    private var colorSizesDataset : MutableMap<Int ,MutableList<MODELSize>> = mutableMapOf()

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

    override fun setInitialColor(color: MODELColor?) {
        initialColor = color
    }

    override fun setProduct(product: MODELProduct) {
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

    override fun addToCartButtonListener() {
        if (selectedSize != null) {
            try {
                val selectedColor = selectedColor
                val selectedSize = selectedSize
                if (selectedColor != null && selectedSize != null) {
                    view?.addToCart(product.getId(), 1, selectedColor.id, selectedSize.id)
                } else {
                    //TODO: FORCE SELECT COLOR AND SIZE
                }
            } catch (exception: Exception) {
                Logger.error(this::class.java.name, "SelectedSize is null!!", exception)
            }
        } else {
            //TODO: SHOW MESSAGE OR SOMETHING
        }
    }

    override fun setupDatasets() {
        colorsDataset  = mutableListOf()
        sizesDataset = mutableListOf()
        colorSizesDataset  = mutableMapOf()
        if (initialColor != null) {
            val color = initialColor
            if (color != null) {
                view?.setSelectedColor(color)
            }
        }
        val colors = product.getColors()
        colors.forEach {modelColor ->
            val colorSizes: MutableList<MODELSize> = mutableListOf()
            modelColor.sizes.forEach{ modelSize ->
                colorSizes.add(modelSize)
            }

            if (initialColor == null) {
                initialColor = modelColor
                val color = initialColor
                if (color != null)
                    view?.setSelectedColor(color)
            }

            colorsDataset.add(modelColor)
            colorSizesDataset[modelColor.id] = colorSizes
        }
        view?.setupColors(colorsDataset)
        val color = initialColor
        if (color != null) {
            view?.setupSizes(sizesDataset, color.id)
            view?.setTickOnColor(color)
        }
    }

    override fun setSelectedColor(color: MODELColor) {
        selectedColor = color
    }

    override fun setSelectedSize(size: MODELSize?) {
        selectedSize = size
    }

    override fun setSizesDataset(sizesDataset: MutableList<MODELSize>) {
        this.sizesDataset = sizesDataset

    }

    override fun getSizesOfColor(id: Int): MutableList<MODELSize>? = colorSizesDataset[id]

    //endregion

    //region InteractorOutput

    override fun showMessage(message: String?) {
        if (message == null)
            view?.showDefaultMessage()
        else
            view?.showMessage(message, false)//FIXME: ISSUCCESSFUL?
    }

    override fun updateProduct(product: MODELProduct) {
        setProduct(product)
    }

    override fun setupFavoriteButton(isFavorite: Boolean) {
        if (isFavorite)
            view?.checkFavorite()
        else
            view?.uncheckFavorite()
    }

    override fun addToFavorite(context: Context, product: MODELProduct, color: MODELColor) {
        interactor?.addFavorite(context, product, color)
    }

    override fun removeFromFavorite(context: Context, product: MODELProduct, color: MODELColor) {
        interactor?.removeFavorite(context, product, color)
    }

    //endregion
}