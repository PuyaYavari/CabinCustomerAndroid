package ist.cabin.cabinCustomerRoot

import android.os.Bundle
import ist.cabin.cabinCustomerBase.BaseActivity
import ist.cabin.cabincustomer.R
import kotlinx.android.synthetic.main.cabin_customer_root.*

class CabinCustomerRootActivity : BaseActivity(),
    CabinCustomerRootContracts.View {
    var presenter: CabinCustomerRootContracts.Presenter? = CabinCustomerRootPresenter(this)

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cabin_customer_root)
        presenter?.onCreate(intent.extras)

        setupPage()
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
        sign_in_button.setOnClickListener { presenter?.moveToLoginPage() }
        sign_up_button.setOnClickListener { presenter?.moveToRegisterPage() }
        info_button.setOnClickListener { presenter?.moveToInfoPage() }
    }

    //endregion
}