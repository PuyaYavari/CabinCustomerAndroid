package com.cabinInformationTechnologies.cabinCustomerFinishTrade

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress

class CabinCustomerFinishTradePresenter(var view: CabinCustomerFinishTradeContracts.View?) :
    CabinCustomerFinishTradeContracts.Presenter,
    CabinCustomerFinishTradeContracts.InteractorOutput {

    var interactor: CabinCustomerFinishTradeContracts.Interactor? =
        CabinCustomerFinishTradeInteractor(this)
    var router: CabinCustomerFinishTradeContracts.Router? = null

    override var orderId: Int? = null
    override var deliveryAddress: MODELAddress? = null
    override var invoiceAddress: MODELAddress? = null
    override var price: Double? = null
    override var DSAAccepted: Boolean = false
    override var PIFAccepted: Boolean = false
    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerFinishTradeRouter(activity)
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

    override fun addressesSelected(): Boolean {
        return deliveryAddress != null && invoiceAddress != null
    }

    override fun paymentSelected(): Boolean {
        //TODO: DID USER SELECT THE PAYMENT METHOD?
        return true
    }

    override fun activateOrder(context: Context?) {
        val id = this.orderId
        if (DSAAccepted && PIFAccepted && context != null && id != null)
            interactor?.activateOrder(context, id)
    }


    //endregion

    //region InteractorOutput

    override fun success() {
        view?.notifySuccess()
    }

    //endregion
}