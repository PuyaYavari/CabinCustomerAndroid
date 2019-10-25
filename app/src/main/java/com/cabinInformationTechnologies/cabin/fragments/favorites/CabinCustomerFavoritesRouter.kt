package com.cabinInformationTechnologies.cabin.fragments.favorites

import android.app.Activity
import androidx.navigation.findNavController
import com.cabinInformationTechnologies.cabin.R

class CabinCustomerFavoritesRouter(var activity: Activity?) :
    CabinCustomerFavoritesContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToProductDetail(product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct) {
        val action = CabinCustomerFavoritesFragmentDirections.actionFavoritesToProductDetail(product, product.getColors()[0])
        activity!!.findNavController(R.id.nav_host_fragment).navigate(action)
    }

    //endregion
}