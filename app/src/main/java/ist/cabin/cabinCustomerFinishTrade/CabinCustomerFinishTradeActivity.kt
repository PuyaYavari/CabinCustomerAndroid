package ist.cabin.cabinCustomerFinishTrade

import android.os.Bundle
import ist.cabin.cabinCustomerBase.BaseActivity
import ist.cabin.cabincustomer.R





class CabinCustomerFinishTradeActivity : BaseActivity(),
    CabinCustomerFinishTradeContracts.View {

    var presenter: CabinCustomerFinishTradeContracts.Presenter? =
        CabinCustomerFinishTradePresenter(this)

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cabin_customer_finish_trade)
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