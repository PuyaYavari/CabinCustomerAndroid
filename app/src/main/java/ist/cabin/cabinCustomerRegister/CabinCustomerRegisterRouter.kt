package ist.cabin.cabinCustomerRegister

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import ist.cabin.cabinCustomerAgreement.CabinCustomerAgreementActivity
import ist.cabin.cabinCustomerBase.BaseContracts

class CabinCustomerRegisterRouter(var activity: Activity?) : CabinCustomerRegisterContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToAgreementPage() {
        val customerAgreementPage: BaseContracts.View = CabinCustomerAgreementActivity()
        val intent = Intent(activity, customerAgreementPage::class.java)

        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        ContextCompat.startActivity(activity!!.applicationContext, intent, Bundle.EMPTY)
    }

    //endregion
}