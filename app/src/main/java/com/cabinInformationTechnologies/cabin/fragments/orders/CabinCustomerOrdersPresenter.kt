package com.cabinInformationTechnologies.cabin.fragments.orders

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cabinInformationTechnologies.cabin.R
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
        view?.hideProgressBar()
        view?.hideNoInternet()
        currentPage += 1
        this.orders.pending.addAll(orders.pending)
        this.orders.shipped.addAll(orders.shipped)
        this.orders.sent.addAll(orders.sent)
        adapter.notifyNewPage()
    }

    override fun setFirstPage(orders: MODELOrders) {
        view?.hideProgressBar()
        view?.hideNoInternet()
        this.orders.pending.clear()
        this.orders.shipped.clear()
        this.orders.sent.clear()
        currentPage = 1
        this.orders.pending.addAll(orders.pending)
        this.orders.shipped.addAll(orders.shipped)
        this.orders.sent.addAll(orders.sent)
        view?.setupPage()
    }

    override fun feedback(context: Context, message: String?) {
        view?.hideProgressBar()
        if (message == null)
            Toast.makeText(
                context,
                context.resources.getString(R.string.default_error_message),
                Toast.LENGTH_SHORT
            ).show()
        else
            Toast.makeText(
                context,
                message,
                Toast.LENGTH_SHORT
            ).show()
    }

    override fun refresh(orders: MODELOrders) {
        view?.hideProgressBar()
        view?.hideNoInternet()
        this.orders.pending.clear()
        this.orders.shipped.clear()
        this.orders.sent.clear()
        currentPage = 1
        this.orders.pending.addAll(orders.pending)
        this.orders.shipped.addAll(orders.shipped)
        this.orders.sent.addAll(orders.sent)
    }

    override fun showNoInternet() {
        view?.showNoInternet()
    }
    //endregion

    //region FragmentsManager

    override val orders: MODELOrders = MODELOrders()
    override var currentPage: Int = 0

    override fun getFirstPage(context: Context) {
        view?.showProgressBar()
        interactor?.getFirstPage(context)
    }

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

    override fun refresh(context: Context, refreshLayout: SwipeRefreshLayout?, adapter: CabinCustomerOrdersAdapter) {
        interactor?.refresh(context, refreshLayout, adapter)
    }
    //endregion
}