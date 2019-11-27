package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.navigation.NavController
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddresses
import com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions.adapter.AddressBox
import com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions.adapter.TaxInvoiceAddressBox

class CabinCustomerAddressOptionsPresenter(var view: CabinCustomerAddressOptionsContracts.View?) :
    CabinCustomerAddressOptionsContracts.Presenter,
    CabinCustomerAddressOptionsContracts.InteractorOutput {

    var interactor: CabinCustomerAddressOptionsContracts.Interactor? =
        CabinCustomerAddressOptionsInteractor(
            this
        )
    var router: CabinCustomerAddressOptionsContracts.Router? = null

    private var currentTab: Tab = Tab.DELIVERY

    private val deliveryAddresses: MutableList<CabinCustomerAddressOptionsContracts.Addressbox> = mutableListOf()
    private val invoiceAddresses: MutableList<CabinCustomerAddressOptionsContracts.Addressbox> = mutableListOf()

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerAddressOptionsRouter(activity)
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
        if (currentTab == Tab.DELIVERY) { setupDeliveryAddressList() }
        else { setupInvoiceAddressList() }
    }

    override fun getAddresses(context: Context, navController: NavController) {
        interactor?.getAddresses(context, navController)
    }

    override fun setupDeliveryAddressList() {
        if (deliveryAddresses.isEmpty()) { view!!.setupEmptyDeliveryAddressList() }
        else { view!!.setupDeliveryAddressList(deliveryAddresses) }
        currentTab = Tab.DELIVERY
    }

    override fun setupInvoiceAddressList() {
        if (invoiceAddresses.isEmpty()) { view!!.setupEmptyInvoiceAddressList() }
        else { view!!.setupInvoiceAddressList(invoiceAddresses) }
        currentTab = Tab.INVOICE
    }

    override fun moveToAddDeliveryAddressPage(address: MODELAddress?) {
        router?.moveToAddDeliveryAddressPage(address)
    }

    override fun moveToAddInvoiceAddressPage(address: MODELAddress?) {
        router?.moveToAddInvoiceAddressPage(address)
    }

    override fun removeAddress(context: Context, address: MODELAddress) {
        interactor?.removeAddress(context, address)
    }

    //endregion

    //region InteractorOutput

    override fun setAddresses(addresses: MODELAddresses) {
        deliveryAddresses.clear()
        invoiceAddresses.clear()
        if (addresses.getAddresses().isNotEmpty()) {
            addresses.getAddresses().forEach {
                val isInvoice = it?.isInvoice
                if (isInvoice != null) {
                    if (isInvoice) {
                        if (it.isCorporate)
                            invoiceAddresses.add(
                                TaxInvoiceAddressBox(
                                    it
                                )
                            )
                        else
                            invoiceAddresses.add(
                                AddressBox(
                                    it
                                )
                            )
                    } else {
                        deliveryAddresses.add(
                            AddressBox(
                                it
                            )
                        )
                    }
                }
            }
        }
        setupPage()
    }

    override fun undoRemove() {
        view?.undoAddressRemove()
    }

    //endregion

    private enum class Tab{
        DELIVERY, INVOICE
    }
}