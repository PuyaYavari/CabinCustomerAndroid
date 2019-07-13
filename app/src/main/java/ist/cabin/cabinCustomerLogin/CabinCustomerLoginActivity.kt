package ist.cabin.cabinCustomerLogin

import android.os.Bundle
import ist.cabin.cabinCustomerBase.BaseActivity
import ist.cabin.cabincustomer.R
import kotlinx.android.synthetic.main.cabin_customer_login.*

class CabinCustomerLoginActivity : BaseActivity(),
    CabinCustomerLoginContracts.View {

    var presenter: CabinCustomerLoginContracts.Presenter? = CabinCustomerLoginPresenter(this)

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cabin_customer_login)
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

    override fun setupPage() {
        login_button.setOnClickListener { presenter?.login() }
    }

    //endregion
}