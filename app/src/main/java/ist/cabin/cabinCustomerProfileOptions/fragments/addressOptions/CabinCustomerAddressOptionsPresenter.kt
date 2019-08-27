package ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions

import android.app.Activity
import android.os.Bundle

class CabinCustomerAddressOptionsPresenter(var view: CabinCustomerAddressOptionsContracts.View?) :
    CabinCustomerAddressOptionsContracts.Presenter, CabinCustomerAddressOptionsContracts.InteractorOutput {

    var interactor: CabinCustomerAddressOptionsContracts.Interactor? = CabinCustomerAddressOptionsInteractor(this)
    var router: CabinCustomerAddressOptionsContracts.Router? = null

    private var currentTab: Tab = Tab.DELIVERY

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

    override fun setupDeliveryAddressList() {
        if (interactor?.getAddressData() == null) { view!!.setupEmptyDeliveryAddressList() } //FIXME: NULL SHOULD BE REPLACED WITH PREOPER DATA FROM BACKEND
        else { view!!.setupDeliveryAddressList() }
        currentTab = Tab.DELIVERY
    }

    override fun setupInvoiceAddressList() {
        if (interactor?.getAddressData() == null) { view!!.setupEmptyInvoiceAddressList() } //FIXME: NULL SHOULD BE REPLACED WITH PREOPER DATA FROM BACKEND
        else { view!!.setupInvoiceAddressList() }
        currentTab = Tab.INVOICE
    }

    override fun moveToAddDeliveryAddressPage(
        name: String?,
        surname: String?,
        phone: String?,
        province: String?,
        district: String?,
        address: String?,
        addressHeader: String?
    ) {
        router?.moveToAddDeliveryAddressPage(name, surname, phone, province, district, address, addressHeader)
    }

    override fun moveToAddInvoiceAddressPage(
        name: String?,
        surname: String?,
        phone: String?,
        province: String?,
        district: String?,
        address: String?,
        addressHeader: String?,
        isCorporate: Boolean?,
        corporationName: String?,
        taxNo: String?,
        taxAdministration: String?
    ) {
        if (isCorporate == null){
            router?.moveToAddInvoiceAddressPage(
                name, surname, phone, province, district, address,
                addressHeader, false, null, null, null)
        } else {
            router?.moveToAddInvoiceAddressPage(
                name, surname, phone, province, district, address,
                addressHeader, isCorporate, corporationName, taxNo, taxAdministration
            )
        }
    }

    //endregion

    //region InteractorOutput

    //endregion

    private enum class Tab{
        DELIVERY, INVOICE
    }
}