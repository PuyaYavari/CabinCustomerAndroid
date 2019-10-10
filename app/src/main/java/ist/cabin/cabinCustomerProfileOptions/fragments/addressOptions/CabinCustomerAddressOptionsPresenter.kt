package ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions

import android.app.Activity
import android.content.Context
import android.os.Bundle
import ist.cabin.cabinCustomerBase.models.local.MODELAddress
import ist.cabin.cabinCustomerBase.models.local.MODELAddresses
import ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions.adapter.AddressBox
import ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions.adapter.TaxInvoiceAddressBox

class CabinCustomerAddressOptionsPresenter(var view: CabinCustomerAddressOptionsContracts.View?) :
    CabinCustomerAddressOptionsContracts.Presenter, CabinCustomerAddressOptionsContracts.InteractorOutput {

    var interactor: CabinCustomerAddressOptionsContracts.Interactor? = CabinCustomerAddressOptionsInteractor(this)
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
        if (currentTab == Tab.DELIVERY) { setupDeliveryAddressList() }
        else { setupInvoiceAddressList() }
    }

    override fun getAddresses(context: Context) {
        interactor?.getAddresses(context)
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
                            invoiceAddresses.add(TaxInvoiceAddressBox(it))
                        else
                            invoiceAddresses.add(AddressBox(it))
                    } else {
                        deliveryAddresses.add(AddressBox(it))
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