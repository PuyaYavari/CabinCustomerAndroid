package ist.cabin.cabinCustomerAgreement

import android.app.Activity

class CabinCustomerAgreementRouter(var activity: Activity?) :
    CabinCustomerAgreementContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun goBack() {
        activity?.finish()
    }

    override fun goForward() {

    }

    //endregion
}