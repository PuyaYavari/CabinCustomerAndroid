package com.cabinInformationTechnologies.cabin.fragments.orders

import android.app.Activity
import android.os.Bundle
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELOrders

class CabinCustomerOrdersPresenter(var view: CabinCustomerOrdersContracts.View?) :
    CabinCustomerOrdersContracts.Presenter,
    CabinCustomerOrdersContracts.InteractorOutput,
    CabinCustomerOrdersContracts.FragmentsManager {

    var interactor: CabinCustomerOrdersContracts.Interactor? =
        CabinCustomerOrdersInteractor(this)
    var router: CabinCustomerOrdersContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerOrdersRouter(activity)

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

    //endregion

    //region InteractorOutput

    override fun setOrdersIn(orders: MODELOrders, adapter: CabinCustomerOrdersAdapter) {
        currentPage += 1
        this.orders.pending.addAll(orders.pending)
        this.orders.shipped.addAll(orders.shipped)
        this.orders.sent.addAll(orders.sent)
        adapter.notifyNewData()
    }


    //endregion

    //region FragmentsManager

    override val orders: MODELOrders = MODELOrders()
    override var currentPage: Int = 0

    override fun getNewPage(page: Int, adapter: CabinCustomerOrdersAdapter) {
        //TODO: DON'T SEND REQUEST IF PAGE <= CURRENT PAGE
        val context = view?.getActivityContext()
        if (context != null) {
            interactor?.getNewPageIn(
                context,
                page,
                adapter
            )
        }
    }

    //endregion
}