package ist.cabin.cabinCustomerAgreement.fragments.agreement

import android.app.Activity

class CabinCustomerAgreementFragmentRouter(var activity: Activity?) :
    CabinCustomerAgreementFragmentContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun goForward() {

    }

    //endregion
}