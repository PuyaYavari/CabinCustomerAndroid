package ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions

import android.app.Activity
import android.os.Bundle

class CabinCustomerAddressOptionsPresenter(var view: CabinCustomerAddressOptionsContracts.View?) :
    CabinCustomerAddressOptionsContracts.Presenter, CabinCustomerAddressOptionsContracts.InteractorOutput {

    var interactor: CabinCustomerAddressOptionsContracts.Interactor? = CabinCustomerAddressOptionsInteractor(this)
    var router: CabinCustomerAddressOptionsContracts.Router? = null

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

    //region Presenter

    override fun setupDeliveryAddressList() {
        if (interactor?.getAddressData() == null) { view!!.setupEmptyDeliveryAddressList() }
        else { view!!.setupDeliveryAddressList() }
    }

    override fun setupInvoiceAddressList() {
        if (interactor?.getAddressData() == null) { view!!.setupEmptyInvoiceAddressList() }
        else { view!!.setupInvoiceAddressList() }
    }

    override fun moveToAddDeliveryAddressPage() {
        router?.moveToAddDeliveryAddressPage()
    }

    override fun moveToAddInvoiceAddressPage() {
        router?.moveToAddInvoiceAddressPage()
    }

    //endregion

    //region InteractorOutput

    //TODO: Implement your InteractorOutput methods here

    //endregion
}