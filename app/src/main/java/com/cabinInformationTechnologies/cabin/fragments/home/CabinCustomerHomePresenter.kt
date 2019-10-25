package com.cabinInformationTechnologies.cabin.fragments.home

import android.app.Activity
import android.os.Bundle

class CabinCustomerHomePresenter(var view: CabinCustomerHomeContracts.View?) : CabinCustomerHomeContracts.Presenter,
    CabinCustomerHomeContracts.InteractorOutput {

    var interactor: CabinCustomerHomeContracts.Interactor? =
        CabinCustomerHomeInteractor(this)
    var router: CabinCustomerHomeContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerHomeRouter(activity)

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

    override fun moveToRegistration() {
        router?.moveToRegistration()
    }

    //endregion

    //region InteractorOutput

    //TODO: Implement your InteractorOutput methods here

    //endregion
}