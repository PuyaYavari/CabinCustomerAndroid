package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions

import android.app.Activity
import android.content.Context
import android.os.Bundle

class CabinCustomerAddressOptionsPresenter(var view: com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions.CabinCustomerAddressOptionsContracts.View?) :
    com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions.CabinCustomerAddressOptionsContracts.Presenter,
    com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions.CabinCustomerAddressOptionsContracts.InteractorOutput {

    var interactor: com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions.CabinCustomerAddressOptionsContracts.Interactor? =
        com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions.CabinCustomerAddressOptionsInteractor(
            this
        )
    var router: com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions.CabinCustomerAddressOptionsContracts.Router? = null

    private var currentTab: com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions.CabinCustomerAddressOptionsPresenter.Tab =
        com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions.CabinCustomerAddressOptionsPresenter.Tab.DELIVERY

    private val deliveryAddresses: MutableList<com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions.CabinCustomerAddressOptionsContracts.Addressbox> = mutableListOf()
    private val invoiceAddresses: MutableList<com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions.CabinCustomerAddressOptionsContracts.Addressbox> = mutableListOf()

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router =
            com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions.CabinCustomerAddressOptionsRouter(
                activity
            )

        bundle?.let {
            //you can delete this if there's no need to get extras from the intent
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

    override fun setupPage() {
        if (currentTab == com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions.CabinCustomerAddressOptionsPresenter.Tab.DELIVERY) { setupDeliveryAddressList() }
        else { setupInvoiceAddressList() }
    }

    override fun getAddresses(context: Context) {
        interactor?.getAddresses(context)
    }

    override fun setupDeliveryAddressList() {
        if (deliveryAddresses.isEmpty()) { view!!.setupEmptyDeliveryAddressList() }
        else { view!!.setupDeliveryAddressList(deliveryAddresses) }
        currentTab =
            com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions.CabinCustomerAddressOptionsPresenter.Tab.DELIVERY
    }

    override fun setupInvoiceAddressList() {
        if (invoiceAddresses.isEmpty()) { view!!.setupEmptyInvoiceAddressList() }
        else { view!!.setupInvoiceAddressList(invoiceAddresses) }
        currentTab =
            com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions.CabinCustomerAddressOptionsPresenter.Tab.INVOICE
    }

    override fun moveToAddDeliveryAddressPage(address: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress?) {
        router?.moveToAddDeliveryAddressPage(address)
    }

    override fun moveToAddInvoiceAddressPage(address: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress?) {
        router?.moveToAddInvoiceAddressPage(address)
    }

    override fun removeAddress(context: Context, address: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress) {
        interactor?.removeAddress(context, address)
    }

    //endregion

    //region InteractorOutput

    override fun setAddresses(addresses: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddresses) {
        deliveryAddresses.clear()
        invoiceAddresses.clear()
        if (addresses.getAddresses().isNotEmpty()) {
            addresses.getAddresses().forEach {
                val isInvoice = it?.isInvoice
                if (isInvoice != null) {
                    if (isInvoice) {
                        if (it.isCorporate)
                            invoiceAddresses.add(
                                com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions.adapter.TaxInvoiceAddressBox(
                                    it
                                )
                            )
                        else
                            invoiceAddresses.add(
                                com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions.adapter.AddressBox(
                                    it
                                )
                            )
                    } else {
                        deliveryAddresses.add(
                            com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions.adapter.AddressBox(
                                it
                            )
                        )
                    }
                }
            }
        }
        setupPage()
    }

    override fun addressRemovedFeedback(isRemoved: Boolean, message: String?) {
        if (!isRemoved)
            view?.undoAddressRemove()
        //TODO: SHOW MESSAGES
    }

    //endregion

    private enum class Tab{
        DELIVERY, INVOICE
    }
}