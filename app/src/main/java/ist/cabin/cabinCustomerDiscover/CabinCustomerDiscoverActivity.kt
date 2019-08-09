package ist.cabin.cabinCustomerDiscover

import android.os.Bundle
import android.widget.ImageButton
import ist.cabin.cabinCustomerBase.BaseActivity
import ist.cabin.cabincustomer.R

class CabinCustomerDiscoverActivity : BaseActivity(),
    CabinCustomerDiscoverContracts.View {

    var presenter: CabinCustomerDiscoverContracts.Presenter? = CabinCustomerDiscoverPresenter(this)

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cabin_customer_discover)
        presenter?.onCreate(intent.extras)

        overridePendingTransition(0,0)

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

    private fun setupPage() {
        findViewById<ImageButton>(R.id.discover_orders_button).setOnClickListener { presenter?.moveToOrdersPage() }
        findViewById<ImageButton>(R.id.discover_favorites_button).setOnClickListener { presenter?.moveToFavoritesPage() }
        findViewById<ImageButton>(R.id.discover_homepage_button).setOnClickListener { presenter?.moveToHomePage() }
        findViewById<ImageButton>(R.id.discover_cart_button).setOnClickListener { presenter?.moveToCartPage() }
    }

    //endregion
}