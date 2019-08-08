package ist.cabin.cabinCustomerOrders

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import ist.cabin.cabincustomer.R
import kotlinx.android.synthetic.main.cabin_customer_orders_root.*

class CabinCustomerOrdersActivity : FragmentActivity(),
    CabinCustomerOrdersContracts.View {
    private lateinit var mPager: ViewPager

    var presenter: CabinCustomerOrdersContracts.Presenter? = CabinCustomerOrdersPresenter(this)

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cabin_customer_orders_root)
        presenter?.onCreate(intent.extras)

        mPager = orders_pager
        val pagerAdapter = CabinCustomerOrdersScreenSlidePagerAdapter(supportFragmentManager)
        mPager.adapter = pagerAdapter

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

    //TODO: Implement your View methods here

    //endregion
}