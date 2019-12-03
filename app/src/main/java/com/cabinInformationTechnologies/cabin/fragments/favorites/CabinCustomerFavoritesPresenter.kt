package com.cabinInformationTechnologies.cabin.fragments.favorites

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct

class CabinCustomerFavoritesPresenter(var view: CabinCustomerFavoritesContracts.View?) :
    CabinCustomerFavoritesContracts.Presenter,
    CabinCustomerFavoritesContracts.InteractorOutput {

    var interactor: CabinCustomerFavoritesContracts.Interactor? =
        CabinCustomerFavoritesInteractor(this)
    var router: CabinCustomerFavoritesContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerFavoritesRouter(activity)

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

    override fun getFavorites(context: Context, page: Int) {
        view?.showProgressBar()
        interactor?.getFavorites(context, page)
    }

    override fun moveToProductDetail(product: MODELProduct) {
        router?.moveToProductDetail(product)
    }

    override fun removeFromFavorites(context: Context, product: MODELProduct) {
        interactor?.removeFromFavorites(context, product)
    }

    override fun addToCart(
        context: Context,
        amount: Int,
        productId: Int,
        color: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor,
        size: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize
    ) {
        interactor?.addToCart(context, amount, productId, color, size)
    }

    //endregion

    //region InteractorOutput

    override fun setData(products: List<MODELProduct?>) {
        val myDataset: MutableList<MODELProduct> = mutableListOf()
        products.forEach {product ->
            product?.getColors()?.forEach {color ->
                val generatedProduct = MODELProduct()
                generatedProduct.setAll(
                    product.getId(),
                    product.getSellerName(),
                    product.getProductName(),
                    product.getProductId(),
                    product.getPrice(),
                    product.getDiscountedPrice(),
                    product.getAmount(),
                    product.getCargoDurationId(),
                    product.getCargoDuration(),
                    product.getCargoTypeId(),
                    product.getCargoType(),
                    mutableListOf(color)
                )
                myDataset.add(generatedProduct)
            }
        }
        if (myDataset.isNotEmpty())
            view?.showData(myDataset)
    }

    override fun undoRemove() {
        view?.undoRemove()
    }

    override fun noInternet(isNetworkConnected: Boolean) {
        if (isNetworkConnected)
            view?.hideNoInternet()
        else if (view?.getCurrentItemCount() == 0)
            view?.showNoInternet()
    }

    //endregion
}