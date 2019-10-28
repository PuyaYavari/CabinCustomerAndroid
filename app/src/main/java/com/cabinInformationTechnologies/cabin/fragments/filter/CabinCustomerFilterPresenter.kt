package com.cabinInformationTechnologies.cabin.fragments.filter

import android.app.Activity
import android.content.Context
import android.os.Bundle

class CabinCustomerFilterPresenter(var view: CabinCustomerFilterContracts.View?) :
    CabinCustomerFilterContracts.Presenter, CabinCustomerFilterContracts.InteractorOutput {

    var interactor: CabinCustomerFilterContracts.Interactor? = CabinCustomerFilterInteractor(this)
    var router: CabinCustomerFilterContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerFilterRouter(activity)

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

    override fun getFilter(context: Context) {
        interactor?.getFilter(context)
    }

    override fun moveToFilterDetail(filterType: Int) {
        router?.moveToFilterDetail(filterType)
    }

    //endregion

    //region InteractorOutput

    override fun setFilter(filter: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilter) {
        view?.setFilter(filter)
    }

    //endregion
}