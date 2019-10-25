package com.cabinInformationTechnologies.cabin.fragments.productDetail

import android.app.Activity

class CabinCustomerProductDetailRouter(var activity: Activity?) :
    CabinCustomerProductDetailContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //endregion
}