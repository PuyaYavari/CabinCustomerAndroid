package ist.cabin.cabinCustomerRegistration

import android.content.Context
import android.os.Bundle
import ist.cabin.cabinCustomerBase.BaseActivity
import ist.cabin.cabinCustomerBase.GlobalData
import ist.cabin.cabinCustomerBase.models.local.MODELUser
import ist.cabin.cabincustomer.R

class CabinCustomerRegistrationActivity : BaseActivity(),
    CabinCustomerRegistrationContracts.View {

    var presenter: CabinCustomerRegistrationContracts.Presenter? =
        CabinCustomerRegistrationPresenter(this)

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cabin_customer_registeration)
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

    override fun setActiveUser(user: MODELUser?) {
        val sharedPref = this.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putInt("userId", GlobalData.userId)
        editor.putString("userSession", GlobalData.session)
        editor.putString("userEmail", GlobalData.userEmail)
        editor.apply()
    }

    //endregion
}