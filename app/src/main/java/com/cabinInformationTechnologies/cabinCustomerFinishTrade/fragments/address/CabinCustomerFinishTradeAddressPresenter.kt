package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.address

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddresses

class CabinCustomerFinishTradeAddressPresenter(var view: CabinCustomerFinishTradeAddressContracts.View?) :
    CabinCustomerFinishTradeAddressContracts.Presenter,
    CabinCustomerFinishTradeAddressContracts.InteractorOutput {

    var interactor: CabinCustomerFinishTradeAddressContracts.Interactor? =
        CabinCustomerFinishTradeAddressInteractor(
            this
        )
    var router: CabinCustomerFinishTradeAddressContracts.Router? = null

    override var deliveryAddress: MODELAddress? = null
        set(value) {
            field = value
            if (value != null) {
                view?.showDeliveryAddressDetail(value)
                if (useDelivery)
                    invoiceAddress = value
            }
            view?.setActivityDeliveryAddress(value)
        }
    override var invoiceAddress: MODELAddress? = null
        set(value) {
            field = value
            if (value != null)
                view?.showInvoiceAddressDetail(value)
            view?.setActivityInvoiceAddress(value)
        }

    private var useDelivery: Boolean = true

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router =
            CabinCustomerFinishTradeAddressRouter(
                activity
            )
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

    override fun getAddresses(context: Context) {
        view?.hideDeliveryAdd()
        interactor?.getAddresses(context)
    }

    override fun setUseDelivery(useDelivery: Boolean) {
        this.useDelivery = useDelivery
        when {
            useDelivery -> invoiceAddress = deliveryAddress
            view?.getSelectedInvoiceAddress() != null -> invoiceAddress = view?.getSelectedInvoiceAddress()
            else -> {
                invoiceAddress = null
                view?.setupNoInvoiceAddress()
            }
        }
    }

    //endregion

    //region InteractorOutput

    override fun setAddresses(addresses: MODELAddresses) {
        val deliveryAddresses: MutableList<MODELAddress> = mutableListOf()
        val invoiceAddresses: MutableList<MODELAddress> = mutableListOf()
        addresses.getAddresses().forEach {
            if (it != null) {
                if (it.isInvoice)
                    invoiceAddresses.add(it)
                else
                    deliveryAddresses.add(it)
            }
        }
        if (deliveryAddresses.isNullOrEmpty())
            view?.setupNoDeliveryAddress()
        else
            view?.setDeliveryAddresses(deliveryAddresses)

        if (invoiceAddresses.isNullOrEmpty())
            view?.setupNoInvoiceAddress()
        else
            view?.setInvoiceAddresses(invoiceAddresses)
        view?.showDeliveryAdd()
    }

    override fun feedback(message: String?) {
        if (message == null)
            view?.showErrorMessage(
                view?.getActivityContext()?.resources?.getText(R.string.default_error_message).toString()
            )
        else
            view?.showErrorMessage(message)
    }

    //endregion
}