package com.cabinInformationTechnologies.cabin

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELCart
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilter

class MainPresenter(var view: MainContracts.View?) : MainContracts.Presenter,
    MainContracts.InteractorOutput {

    var interactor: MainContracts.Interactor? =
        MainInteractor(this)
    var router: MainContracts.Router? = null

    override var filter: MODELFilter? = null
    override var cart: MODELCart = MODELCart()
        set(value) {
            field = value
            var productCount = 0
            cart.getSellers().forEach {seller ->
                productCount += seller.getProducts().size
            }
            if (productCount > 0)
                view?.setCartBadge(productCount)
            else
                view?.removeCartBadge()
        }

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = MainRouter(activity)
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

    override fun moveToProfileOptions() {
        router?.moveToProfileOptions()
    }

    override fun moveToMeasure() {
        router?.moveToMeasure()
    }

    override fun moveToExtraditions() {
        router?.moveToExtraditions()
    }

    override fun requestLogout(context: Context) {
        interactor?.logout(context)
    }

    override fun moveToRegistration() {
        router?.moveToRegistration()
    }

    override fun clearFilter(context: Context) {
        filter = null
        interactor?.clearFilter(context)
    }

    override fun updateFilterTo(context: Context, filter: MODELFilter?) {
        interactor?.updateFilterTo(context, filter)
    }

    override fun updateCart(context: Context) {
        interactor?.getCart(context)
    }

    //endregion

    //region InteractorOutput

    override fun logout() {
        view?.logout()
//        view?.showNeedLogin()
    }

    override fun unlockDrawer() {
        view?.unlockDrawer()
    }

    override fun refreshFilter(filter: MODELFilter) {
        this.filter = filter
    }
    //endregion
}