package ist.cabin.cabinCustomerFavorites

import android.os.Bundle
import android.widget.ImageButton
import ist.cabin.cabinCustomerBase.BaseActivity
import ist.cabin.cabincustomer.R

class CabinCustomerFavoritesActivity : BaseActivity(),
    CabinCustomerFavoritesContracts.View {

    var presenter: CabinCustomerFavoritesContracts.Presenter? = CabinCustomerFavoritesPresenter(this)

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cabin_customer_favorites)
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
        findViewById<ImageButton>(R.id.favorites_orders_button).setOnClickListener { presenter?.moveToOrdersPage() }
        findViewById<ImageButton>(R.id.favorites_homepage_button).setOnClickListener { presenter?.moveToHomePage() }
        findViewById<ImageButton>(R.id.favorites_cart_button).setOnClickListener { presenter?.moveToCartPage() }
        findViewById<ImageButton>(R.id.favorites_discover_button).setOnClickListener { presenter?.moveToDiscoverPage() }
    }

    //endregion
}