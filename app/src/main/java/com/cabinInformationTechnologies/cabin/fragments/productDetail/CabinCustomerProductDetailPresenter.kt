package com.cabinInformationTechnologies.cabin.fragments.productDetail

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import com.cabinInformationTechnologies.cabin.MainContracts
import com.cabinInformationTechnologies.cabinCustomerBase.GlobalData
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELCart
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize

class CabinCustomerProductDetailPresenter(var view: CabinCustomerProductDetailContracts.View?) :
    CabinCustomerProductDetailContracts.Presenter,
    CabinCustomerProductDetailContracts.InteractorOutput {

    var interactor: CabinCustomerProductDetailContracts.Interactor? =
        CabinCustomerProductDetailInteractor(this)
    var router: CabinCustomerProductDetailContracts.Router? = null

    private var cart: MODELCart = MODELCart()

    private var selectedColor: MODELColor? = null
    private var selectedSize: MODELSize? = null
    private var selectedSizeAmount: Int = 0
    private var product: MODELProduct = MODELProduct()
    private var initialColor: MODELColor? = null
    private var initialColorIsPicked = false

    private var colorsDataset : MutableList<MODELColor> = mutableListOf()
    private var sizesDataset: MutableList<MODELSize> = mutableListOf()
    private var colorSizesDataset : MutableMap<Int ,MutableList<MODELSize>> = mutableMapOf()

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerProductDetailRouter(activity)
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

    override fun requestProduct(context: Context?, id: Int, navController: NavController) {
        if (context != null)
            interactor?.requestProduct(context, id, navController)
    }

    override fun setInitialColor(color: MODELColor?) {
        initialColor = color
        initialColorIsPicked = color != null
    }

    override fun setProduct(product: MODELProduct) {
        this.product = product
        view?.setupProductDetail(product)
        val discountedPrice = product.getDiscountedPrice()
        if (discountedPrice == null) {
            view?.setupPrice(product.getPrice(), product.getPriceUnit())
        } else {
            view?.setupPrice(product.getPrice(), discountedPrice, product.getPriceUnit())
        }
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
        view?.showButtonProgressBar()
    }

    override fun addToCartButtonListener(context: Context) {
        if (GlobalData.loggedIn) {
            if (selectedSize != null) {
                try {
                    val selectedColor = selectedColor
                    val selectedSize = selectedSize
                    if (selectedColor != null && selectedSize != null) {
                        view?.addToCart(product.getId(), 1, selectedColor.id, selectedSize.id)
                    }
                } catch (exception: Exception) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "SelectedSize is null!!",
                        exception
                    )
                }
            } else {
                val selectedColor = selectedColor
                if (selectedColor != null)
                    view?.showSelectSizeFor(
                        product,
                        selectedColor,
                        object : MainContracts.SelectSizeCallback {
                            override fun selectSize(size: MODELSize) {
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
                        }
                    )
            }
        } else {
            view?.directToRegistration()
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
            val colorSizes: MutableList<MODELSize> = mutableListOf()
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

    override fun setSelectedColor(color: MODELColor) {
        selectedColor = color
    }

    override fun setSelectedSize(size: MODELSize?) {
        selectedSize = size
        var amount = 0
        this.cart.getSellers().forEach {seller ->
            seller.getProducts().forEach {product ->
                if (product != null && product.getId() == this.product.getId()) {
                    product.getColors().forEach {color ->
                        if (color.id == selectedColor?.id)
                            color.sizes.forEach {size ->
                                if (size.id == selectedSize?.id) {
                                    val productAmount = product.getAmount()
                                    if (productAmount != null)
                                        amount = productAmount
                                }
                            }
                    }
                }
            }
        }
        Log.d(null, amount.toString())
        selectedSizeAmount = if (amount > 0) {
            view?.showCounter(amount)
            amount
        } else {
            view?.showAddToCartButton()
            0
        }
    }

    override fun setSizesDataset(sizesDataset: MutableList<MODELSize>) {
        this.sizesDataset = sizesDataset
    }

    override fun getSizesOfColor(id: Int): MutableList<MODELSize>? = colorSizesDataset[id]

    override fun increaseAmount(context: Context?) {
        val colorId = selectedColor?.id
        val sizeId = selectedSize?.id
        if (context != null && colorId != null && sizeId != null) {
            interactor?.updateProduct(context, product.getId(), colorId, sizeId, selectedSizeAmount+1)
        }
    }

    override fun decreaseAmount(context: Context?) {
        val colorId = selectedColor?.id
        val sizeId = selectedSize?.id
        if (context != null && colorId != null && sizeId != null && selectedSizeAmount > 0) {
            interactor?.updateProduct(context, product.getId(), colorId, sizeId, selectedSizeAmount-1)
        } else if (selectedSizeAmount <= 0) {
            selectedSizeAmount = 0
            view?.showAddToCartButton()
        }
    }

    //endregion

    //region InteractorOutput

    override fun showSuccessMessage() {
        view?.showDefaultMessage()
    }

    override fun showButton() {
        view?.showAddToCartButton()
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

    override fun addToFavorite(context: Context?, product: MODELProduct, color: MODELColor) {
        if (context != null)
            interactor?.addFavorite(context, product, color)
    }

    override fun removeFromFavorite(context: Context?, product: MODELProduct, color: MODELColor) {
        if (context != null)
            interactor?.removeFavorite(context, product, color)
    }

    override fun setCart(cart: MODELCart?) {
        if (cart != null) {
            this.cart = cart
            view?.setActivityCart(cart)
            var amount = 0
            this.cart.getSellers().forEach {seller ->
                seller.getProducts().forEach {product ->
                    if (product != null && product.getId() == this.product.getId()) {
                        product.getColors().forEach {color ->
                            if (color.id == selectedColor?.id)
                                color.sizes.forEach {size ->
                                    if (size.id == selectedSize?.id) {
                                        val productAmount = product.getAmount()
                                        if (productAmount != null)
                                            amount = productAmount
                                    }
                                }
                        }
                    }
                }
            }
            if (amount > 0) {
                selectedSizeAmount = amount
                view?.showCounter(amount)
            } else {
                selectedSizeAmount = 0
                view?.showAddToCartButton()
            }
        } else {
            view?.setActivityCart(MODELCart())
        }
    }

    override fun productAddedToCart() {
        view?.showCounter(1)
    }
    //endregion
}