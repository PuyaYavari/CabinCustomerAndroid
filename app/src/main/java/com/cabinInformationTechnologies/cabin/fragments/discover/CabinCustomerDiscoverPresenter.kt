package com.cabinInformationTechnologies.cabin.fragments.discover

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSort
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSorts

class CabinCustomerDiscoverPresenter(var view: CabinCustomerDiscoverContracts.View?) :
    CabinCustomerDiscoverContracts.Presenter,
    CabinCustomerDiscoverContracts.InteractorOutput {

    var interactor: CabinCustomerDiscoverContracts.Interactor? =
        CabinCustomerDiscoverInteractor(this)
    var router: CabinCustomerDiscoverContracts.Router? = null
    private var currentPage = 0

    private var lastEnteredProduct: MODELProduct? = null
    private var lastEnteredProductPosition: Int = -1

    override val myDataset: MutableList<MODELProduct> = mutableListOf()

    private var sort: MODELSort? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerDiscoverRouter(activity)
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

    override fun moveToProductDetail(product: MODELProduct, position: Int) {
        lastEnteredProduct = product
        lastEnteredProductPosition = position
        router?.moveToProductDetail(product)
    }

    override fun getProducts(context: Context?, page: Int) {
        if (context != null && currentPage < page) {
            if (view?.getCurrentItemCount() == 0)
                view?.showProgressBar()
            val currentSort = this.sort
            if (currentSort == null)
                interactor?.getProducts(context, page)
            else
                interactor?.getProducts(context, page, currentSort.getId())
        }
    }

    override fun updateLastEnteredProduct(context: Context?) {
        val product = lastEnteredProduct
        if (product != null && context != null)
            interactor?.getProduct(context, product.getId())
    }

    override fun moveToFilter() {
        router?.moveToFilter()
    }

    override fun getSortOptions(context: Context?) {
        if (context != null)
            interactor?.getSortOptions(context)
    }

    override fun setSort(sort: MODELSort?) {
        this.sort = sort
        view?.resetPage()
    }

    override fun resetPage(context: Context?) {
        currentPage = 0
        getProducts(context,currentPage + 1)
    }

    //endregion

    //region InteractorOutput

    override fun addData(products: List<MODELProduct>?) {
        currentPage += 1
        view?.addData(products)
    }

    override fun updateProduct(product: MODELProduct) {
        if (lastEnteredProductPosition > -1)
            view?.updateProduct(product, lastEnteredProductPosition)
        lastEnteredProductPosition = -1
        lastEnteredProduct = null
    }

    override fun noInternet(isNetworkConnected: Boolean) {
        if (isNetworkConnected)
            view?.hideNoInternet()
        else if (view?.getCurrentItemCount() == 0)
            view?.showNoInternet()
    }

    override fun showSorts(sorts: MODELSorts) {
        view?.showSorts(sorts)
    }

    //endregion
}