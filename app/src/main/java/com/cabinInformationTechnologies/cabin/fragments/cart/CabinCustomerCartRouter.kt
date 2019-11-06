package com.cabinInformationTechnologies.cabin.fragments.cart

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct
import com.cabinInformationTechnologies.cabinCustomerFinishTrade.CabinCustomerFinishTradeActivity

class CabinCustomerCartRouter(var activity: Activity?) :
    CabinCustomerCartContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToFinishTrade() {
        val customerFinishTradeActivity: BaseContracts.View = CabinCustomerFinishTradeActivity()
        val intent = Intent(activity, customerFinishTradeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        ContextCompat.startActivity(activity!!.applicationContext, intent, Bundle.EMPTY)
    }

    override fun moveToProductDetail(product: MODELProduct, color: MODELColor) {
        val action = CabinCustomerCartFragmentDirections.actionCartToProductDetail(product, color)
        activity!!.findNavController(R.id.nav_host_fragment).navigate(action)
    }
    //endregion
}