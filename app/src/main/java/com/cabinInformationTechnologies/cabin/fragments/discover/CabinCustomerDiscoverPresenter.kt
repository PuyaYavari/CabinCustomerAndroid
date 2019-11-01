package com.cabinInformationTechnologies.cabin.fragments.discover

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.cabinInformationTechnologies.cabin.R

class CabinCustomerDiscoverPresenter(var view: CabinCustomerDiscoverContracts.View?) :
    CabinCustomerDiscoverContracts.Presenter,
    CabinCustomerDiscoverContracts.InteractorOutput {

    var interactor: CabinCustomerDiscoverContracts.Interactor? =
        CabinCustomerDiscoverInteractor(this)
    var router: CabinCustomerDiscoverContracts.Router? = null
    private var currentPage = 0

    private var lastEnteredProduct: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct? = null
    private var lastEnteredProductPosition: Int = -1

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerDiscoverRouter(activity)

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

    override fun moveToProductDetail(product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct, position: Int) {
        lastEnteredProduct = product
        lastEnteredProductPosition = position
        router?.moveToProductDetail(product)
    }

    override fun getProducts(page: Int, pageSize: Int) {
        val context = view?.getActivityContext()
        if (context != null && currentPage < page) {
            if (view?.getCurrentItemCount() == 0)
                view?.showProgressBar()
            interactor?.getProducts(context, page, pageSize)
        }
    }

    override fun updateLastEnteredProduct(context: Context) {
        val product = lastEnteredProduct
        if (product != null)
            interactor?.getProduct(context, product.getId())
    }

    override fun moveToFilter() {
        router?.moveToFilter()
    }

    //endregion

    //region InteractorOutput

    override fun addData(products: List<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct>?) {
        currentPage += 1
        view?.addData(products)
    }

    override fun resetPage() {
        currentPage = 0
    }

    override fun updateProduct(product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct) {
        if (lastEnteredProductPosition > -1)
            view?.updateProduct(product, lastEnteredProductPosition)
        lastEnteredProductPosition = -1
        lastEnteredProduct = null
    }

    override fun feedback(message: String?) {
        if (message != null) {
            view?.feedback(message)
        } else {
            val defaultMessage = view?.getActivityContext()?.resources?.getString(R.string.default_error_message)
            if (defaultMessage != null)
                view?.feedback(defaultMessage)
        }
        view?.hideProgressBar()
    }

    override fun noInternet(isNetworkConnected: Boolean) {
        if (isNetworkConnected)
            view?.hideNoInternet()
        else if (view?.getCurrentItemCount() == 0)
            view?.showNoInternet()
    }

    //endregion
}