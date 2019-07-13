package ist.cabin.cabincustomer

import android.os.Bundle
import ist.cabin.cabinCustomerBase.BaseActivity

class MainActivity : BaseActivity(),
    MainContracts.View {

    var presenter: MainContracts.Presenter? = MainPresenter(this)

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter?.onCreate(intent.extras)

        presenter?.moveToRootPage()
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
}