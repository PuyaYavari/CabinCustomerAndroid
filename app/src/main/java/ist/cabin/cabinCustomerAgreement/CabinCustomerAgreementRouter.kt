package ist.cabin.cabinCustomerAgreement

import android.app.Activity
import android.util.Log

class CabinCustomerAgreementRouter(var activity: Activity?) : CabinCustomerAgreementContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun goBack() {
        activity?.finish()
    }

    override fun goForward() {
        Log.d("next page is","?")
    }

    //endregion
}