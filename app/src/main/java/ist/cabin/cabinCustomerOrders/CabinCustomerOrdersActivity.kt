package ist.cabin.cabinCustomerOrders

import android.os.Bundle
import ist.cabin.cabinCustomerBase.BaseActivity

class CabinCustomerOrdersActivity : BaseActivity(),
    CabinCustomerOrdersContracts.View { //TODO: DON'T FORGET TO ADD THIS ACTIVITY TO THE MANIFEST FILE!!!

    var presenter: CabinCustomerOrdersContracts.Presenter? = CabinCustomerOrdersPresenter(this)

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.) //TODO create the layout and add it here
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