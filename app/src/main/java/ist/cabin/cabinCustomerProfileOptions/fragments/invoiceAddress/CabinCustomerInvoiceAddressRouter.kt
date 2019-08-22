package ist.cabin.cabinCustomerProfileOptions.fragments.invoiceAddress

import android.app.Activity

class CabinCustomerInvoiceAddressRouter(var activity: Activity?) : CabinCustomerInvoiceAddressContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}