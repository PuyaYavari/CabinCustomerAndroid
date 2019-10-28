package com.cabinInformationTechnologies.cabin.fragments.cart

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log

class CabinCustomerCartPresenter(var view: CabinCustomerCartContracts.View?) : CabinCustomerCartContracts.Presenter,
    CabinCustomerCartContracts.InteractorOutput {

    var interactor: CabinCustomerCartContracts.Interactor? =
        CabinCustomerCartInteractor(view?.getActivityContext(),this)
    var router: CabinCustomerCartContracts.Router? = null

    private var priceDetailIsVisible: Boolean = false

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerCartRouter(activity)

        bundle?.let {
            //you can delete this if there's no need to get extras from the intent
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

    override fun togglePriceDetail() {
        priceDetailIsVisible = if (!priceDetailIsVisible) {
            view!!.showPriceDetail()
            true
        } else {
            view!!.hidePriceDetail()
            false
        }
    }

    override fun moveToFinishTrade() {
        router?.moveToFinishTrade()
    }

    override fun getCart(context: Context) {
        view?.showProgressBar()
        interactor?.getCart(context)
    }

    override fun updateProduct(context: Context, product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct) {
        interactor?.updateProduct(context, product)
    }

    override fun areProductsEqual(first: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct, second: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct): Boolean {
        if (first.getId() == second.getId() &&
            first.getColors()[0].id == second.getColors()[0].id &&
            first.getColors()[0].sizes[0].id == second.getColors()[0].sizes[0].id)
            return true
        return false
    }

    override fun moveToProductDetail(product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct, color: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor) {
        router?.moveToProductDetail(product, color)
    }
    //endregion

    //region InteractorOutput

    override fun setCart(cart: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELCart?) {
        view?.hideProgressBar()
        if (cart != null) {
            var sellerIter = cart.getSellers().iterator()
            while (sellerIter.hasNext()) {
                val seller = sellerIter.next()
                if (view?.myDataset!!.isNotEmpty()) {
                    val datasetIter = view?.myDataset!!.iterator()
                    while (datasetIter.hasNext()) {
                        val myProduct = datasetIter.next()
                        var isIncluded = false
                        var index = 0
                        while (!isIncluded && index < seller.getProducts().size) {
                            val indexProduct = seller.getProducts()[index]
                            var areEqual: Boolean? = false
                            if (indexProduct != null) {
                                areEqual =
                                    areProductsEqual(indexProduct, myProduct)
                            }
                            if (areEqual != null && areEqual) {
                                isIncluded = true
                                if (myProduct.getAmount() != indexProduct?.getAmount())
                                    myProduct.setAmount(indexProduct?.getAmount())
                            }
                            index++
                        }
                        if (!isIncluded) {
                            Log.i(myProduct.getId().toString(), "is not included and is removed")
                            datasetIter.remove()
                        }
                    }
                }
            }
            sellerIter = cart.getSellers().iterator()
            view?.clearCargoPrices()
            while (sellerIter.hasNext()) {
                val seller = sellerIter.next()
                val productIter = seller.getProducts().iterator()
                while (productIter.hasNext()) {
                    val product = productIter.next()
                    var isIncluded = false
                    var index = 0
                    if (product != null) {
                        while (!isIncluded && index < view?.myDataset!!.size) {
                            val areEqual = areProductsEqual(product, view?.myDataset!![index])
                            if (areEqual)
                                isIncluded = true
                            index++
                        }
                        if (!isIncluded)
                            view?.myDataset!!.add(product)
                    }
                }
                val shippingPrice = seller.getShippingPrice()
                if (shippingPrice != 0.0 && shippingPrice != null && view?.myDataset!!.isNotEmpty()) {
                    view?.addShippingPrice(seller.getName(), shippingPrice)
                }
            }
            if (view?.myDataset!!.isNotEmpty())
                view?.setData(cart)
            else
                view?.clearAll()
        } else {
            view?.clearAll()
        }
    }

    override fun feedback(message: String) {
        view?.feedback(message)
    }

    //endregion
}