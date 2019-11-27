package com.cabinInformationTechnologies.cabin.fragments.cart

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELCart
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct

class CabinCustomerCartPresenter(var view: CabinCustomerCartContracts.View?) : CabinCustomerCartContracts.Presenter,
    CabinCustomerCartContracts.InteractorOutput {

    var interactor: CabinCustomerCartContracts.Interactor? =
        CabinCustomerCartInteractor(view as CabinCustomerCartContracts.ViewForInteractor,this)
    var router: CabinCustomerCartContracts.Router? = null

    override val myDataset: MutableList<MODELProduct> = mutableListOf()

    private var priceDetailIsVisible: Boolean = false

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerCartRouter(activity)
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

    override fun getCart(context: Context) {
        view?.showProgressBar()
        interactor?.getCart(context)
    }

    override fun updateProduct(context: Context, product: MODELProduct) {
        interactor?.updateProduct(context, product)
    }

    override fun areProductsEqual(first: MODELProduct, second: MODELProduct): Boolean {
        if (first.getId() == second.getId() &&
            first.getColors()[0].id == second.getColors()[0].id &&
            first.getColors()[0].sizes[0].id == second.getColors()[0].sizes[0].id)
            return true
        return false
    }

    override fun moveToProductDetail(product: MODELProduct, color: MODELColor) {
        router?.moveToProductDetail(product, color)
    }
    //endregion

    //region InteractorOutput

    override fun setCart(cart: MODELCart?) {
        view?.hideProgressBar()
        view?.hideNoInternet()
        if (cart?.getSellers().isNullOrEmpty())
            myDataset.clear()
        if (cart != null) {
            view?.setActivityCart(cart)
            var sellerIter = cart.getSellers().iterator()
            while (sellerIter.hasNext()) {
                val seller = sellerIter.next()
                if (myDataset.isNotEmpty()) {
                    val datasetIter = myDataset.iterator()
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
                            val context = view?.getActivityContext()
                            if (context != null)
                                Logger.warn(
                                    context,
                                    this::class.java.name,
                                    "${myProduct.getId()} is not included and is removed",
                                    null
                                )
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
                        while (!isIncluded && index < myDataset.size) {
                            val areEqual = areProductsEqual(product, myDataset[index])
                            if (areEqual)
                                isIncluded = true
                            index++
                        }
                        if (!isIncluded)
                            myDataset.add(product)
                    }
                }
                val shippingPrice = seller.getShippingPrice()
                if (shippingPrice != 0.0 && shippingPrice != null && myDataset.isNotEmpty()) {
                    view?.addShippingPrice(seller.getName(), shippingPrice)
                }
            }
            if (myDataset.isNotEmpty())
                view?.setData(cart)
            else
                view?.clearAll()
        } else {
            view?.setActivityCart(MODELCart())
            view?.clearAll()
        }
    }

    override fun noInternet(isNetworkConnected: Boolean) {
        if (isNetworkConnected)
            view?.hideNoInternet()
        else
            view?.showNoInternet()
    }

    //endregion
}