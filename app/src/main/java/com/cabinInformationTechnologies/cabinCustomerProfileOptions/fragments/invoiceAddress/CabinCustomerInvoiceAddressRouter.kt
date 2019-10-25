package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.invoiceAddress

import android.app.Activity

class CabinCustomerInvoiceAddressRouter(var activity: Activity?) :
    com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.invoiceAddress.CabinCustomerInvoiceAddressContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //endregion
}