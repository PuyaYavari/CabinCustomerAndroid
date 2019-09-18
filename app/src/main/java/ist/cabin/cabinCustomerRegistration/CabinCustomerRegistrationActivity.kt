package ist.cabin.cabinCustomerRegistration

import android.os.Bundle
import ist.cabin.cabinCustomerBase.BaseActivity
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

    //TODO: Implement your View methods here

    //endregion
}