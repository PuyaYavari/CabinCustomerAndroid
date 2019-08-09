package ist.cabin.cabinCustomerForgotpassword

import android.app.Activity

class CabinCustomerForgotpasswordRouter(var activity: Activity?) : CabinCustomerForgotpasswordContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    override fun moveToConfirmationPage() {
        activity?.finish()
    }

    //endregion
}