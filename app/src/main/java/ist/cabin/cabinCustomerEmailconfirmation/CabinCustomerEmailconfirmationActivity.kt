package ist.cabin.cabinCustomerEmailconfirmation

import android.os.Bundle
import ist.cabin.cabinCustomerBase.BaseActivity
import ist.cabin.cabincustomer.R
import kotlinx.android.synthetic.main.cabin_customer_emailconfirmation.*

class CabinCustomerEmailconfirmationActivity : BaseActivity(),
    CabinCustomerEmailconfirmationContracts.View {
    var presenter: CabinCustomerEmailconfirmationContracts.Presenter? = CabinCustomerEmailconfirmationPresenter(this)

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cabin_customer_emailconfirmation)
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
        emailconfirmation_back_to_root_button.setOnClickListener { presenter?.moveToRootPage() }
    }

    //endregion
}