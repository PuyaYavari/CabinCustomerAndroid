package ist.cabin.cabinCustomerCart

import android.os.Bundle
import android.widget.ImageButton
import ist.cabin.cabinCustomerBase.BaseActivity
import ist.cabin.cabincustomer.R

class CabinCustomerCartActivity : BaseActivity(),
    CabinCustomerCartContracts.View {

    var presenter: CabinCustomerCartContracts.Presenter? = CabinCustomerCartPresenter(this)

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cabin_customer_cart)
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
        findViewById<ImageButton>(R.id.cart_orders_button).setOnClickListener { presenter?.moveToOrdersPage() }
        findViewById<ImageButton>(R.id.cart_favorites_button).setOnClickListener { presenter?.moveToFavoritesPage() }
        findViewById<ImageButton>(R.id.cart_homepage_button).setOnClickListener { presenter?.moveToHomePage() }
        findViewById<ImageButton>(R.id.cart_discover_button).setOnClickListener { presenter?.moveToDiscoverPage() }
    }

    //endregion
}