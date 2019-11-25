package com.cabinInformationTechnologies.cabinCustomerFinishTrade


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.BaseActivity
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress


class CabinCustomerFinishTradeActivity : BaseActivity(),
    CabinCustomerFinishTradeContracts.View {

    var presenter: CabinCustomerFinishTradeContracts.Presenter? =
        CabinCustomerFinishTradePresenter(this)

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cabin_customer_finish_trade)
        presenter?.onCreate(intent.extras)
    }

    override fun onResume() {
        super.onResume()
        presenter?.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter?.onPause()
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }

    //endregion

    //region View

    override fun setDeliveryAddress(address: MODELAddress?) {
        Log.i(null, "setting delivery address to ${address.toString()}")
        presenter?.deliveryAddress = address
    }

    override fun setInvoiceAddress(address: MODELAddress?) {
        Log.i(null, "setting invoice address to ${address.toString()}")
        presenter?.invoiceAddress = address
    }

    override fun addressesSelected(): Boolean? {
        return presenter?.addressesSelected()
    }

    override fun paymentSelected(): Boolean? {
        return presenter?.paymentSelected()
    }

    override fun notifySuccess() {
        OrderActivatedDialogFragment(this).show(supportFragmentManager,null)
    }

    override fun finishActivity() {
        val data = Intent()
        data.putExtra("toPage", 0)
        setResult(Activity.RESULT_OK, data)
        finish()
    }

    //endregion
}