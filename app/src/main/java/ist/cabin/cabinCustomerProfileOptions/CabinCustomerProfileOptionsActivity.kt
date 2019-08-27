package ist.cabin.cabinCustomerProfileOptions

import android.os.Bundle
import ist.cabin.cabinCustomerBase.BaseActivity
import ist.cabin.cabincustomer.R

class CabinCustomerProfileOptionsActivity : BaseActivity(),
    CabinCustomerProfileOptionsContracts.View {

    var presenter: CabinCustomerProfileOptionsContracts.Presenter? = CabinCustomerProfileOptionsPresenter(this)

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cabin_customer_profile_options_main)
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

    //endregion
}