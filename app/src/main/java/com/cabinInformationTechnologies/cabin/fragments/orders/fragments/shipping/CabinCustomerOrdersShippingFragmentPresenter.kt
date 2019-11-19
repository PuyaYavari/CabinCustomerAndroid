package com.cabinInformationTechnologies.cabin.fragments.orders.fragments.shipping

import android.app.Activity
import android.os.Bundle
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELOrder

class CabinCustomerOrdersShippingFragmentPresenter(var view: CabinCustomerOrdersShippingFragmentContracts.View?) :
    CabinCustomerOrdersShippingFragmentContracts.Presenter,
    CabinCustomerOrdersShippingFragmentContracts.InteractorOutput {

    var interactor: CabinCustomerOrdersShippingFragmentContracts.Interactor? =
        CabinCustomerOrdersShippingFragmentInteractor(
            this
        )
    var router: CabinCustomerOrdersShippingFragmentContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router =
            CabinCustomerOrdersShippingFragmentRouter(
                activity
            )

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

    override fun moveToDetailsPage(order: MODELOrder) {
        router?.moveToDetailsPage(order)
    }

    //endregion

    //region InteractorOutput

    //TODO: Implement your InteractorOutput methods here

    //endregion
}