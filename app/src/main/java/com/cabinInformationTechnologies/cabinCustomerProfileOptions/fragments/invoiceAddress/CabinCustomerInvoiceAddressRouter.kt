package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.invoiceAddress

import android.app.Activity

class CabinCustomerInvoiceAddressRouter(var activity: Activity?) :
    CabinCustomerInvoiceAddressContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //endregion
}