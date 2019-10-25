package com.cabinInformationTechnologies.cabin

import android.app.Activity
import android.content.Context
import android.os.Bundle

class MainPresenter(var view: com.cabinInformationTechnologies.cabin.MainContracts.View?) : com.cabinInformationTechnologies.cabin.MainContracts.Presenter,
    com.cabinInformationTechnologies.cabin.MainContracts.InteractorOutput {

    var interactor: com.cabinInformationTechnologies.cabin.MainContracts.Interactor? =
        com.cabinInformationTechnologies.cabin.MainInteractor(this)
    var router: com.cabinInformationTechnologies.cabin.MainContracts.Router? = null

    override var filter: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilter? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = com.cabinInformationTechnologies.cabin.MainRouter(activity)
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

    //endregion

    //region InteractorOutput

    override fun logout() {
        view?.logout()
//        view?.showNeedLogin()
    }

    //endregion
}