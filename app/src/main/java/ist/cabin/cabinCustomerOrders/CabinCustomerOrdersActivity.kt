package ist.cabin.cabinCustomerOrders

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import ist.cabin.cabincustomer.R
import kotlinx.android.synthetic.main.cabin_customer_orders.*

class CabinCustomerOrdersActivity : FragmentActivity(),
    CabinCustomerOrdersContracts.View {
    private lateinit var mPager: ViewPager

    var presenter: CabinCustomerOrdersContracts.Presenter? = CabinCustomerOrdersPresenter(this)

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cabin_customer_orders)
        presenter?.onCreate(intent.extras)

        overridePendingTransition(0, 0)

        mPager = orders_pager
        val pagerAdapter = CabinCustomerOrdersScreenSlidePagerAdapter(supportFragmentManager)
        mPager.adapter = pagerAdapter

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

    override fun onBackPressed() {
        if (mPager.currentItem == 2) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            mPager.currentItem = 2
        }
    }

    override fun getActivityContext(): Context? {
        return this
    }

    override fun showErrorDialog(error: String?) {
        Log.d("error", error)
    }


    //endregion

    //region View

    private fun setupPage () {
        findViewById<ImageButton>(R.id.orders_homepage_button).setOnClickListener { presenter?.moveToHomePage() }
        findViewById<ImageButton>(R.id.orders_favorites_button).setOnClickListener { presenter?.moveToFavoritesPage() }
        findViewById<ImageButton>(R.id.orders_cart_button).setOnClickListener { presenter?.moveToCartPage() }
        findViewById<ImageButton>(R.id.orders_discover_button).setOnClickListener { presenter?.moveToDiscoverPage() }
    }

    //endregion
}