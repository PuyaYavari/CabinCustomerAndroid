package com.cabinInformationTechnologies.cabin.fragments.cart

import android.app.Activity
import androidx.navigation.findNavController
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct

class CabinCustomerCartRouter(var activity: Activity?) :
    CabinCustomerCartContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToProductDetail(product: MODELProduct, color: MODELColor) {
        val action = CabinCustomerCartFragmentDirections.actionCartToProductDetail(product, color)
        activity!!.findNavController(R.id.nav_host_fragment).navigate(action)
    }
    //endregion
}