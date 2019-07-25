package ist.cabin.cabinCustomerHome

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import ist.cabin.cabincustomer.R
import kotlinx.android.synthetic.main.cabin_customer_home_root.*

class CabinCustomerHomeActivity :
    CabinCustomerHomeContracts.View, FragmentActivity() {
    private lateinit var mPager: ViewPager

    var presenter: CabinCustomerHomeContracts.Presenter? = CabinCustomerHomePresenter(this)

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cabin_customer_home_root)
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


    override fun setupPage() {
        mPager = home_pager
        // The pager adapter, which provides the pages to the view pager widget.
        val pagerAdapter = CabinCustomerHomeScreenSlidePagerAdapter(supportFragmentManager)
        mPager.adapter = pagerAdapter
        mPager.currentItem = 2
        home_main_navbar_home_button.setOnClickListener { presenter?.seeHome() }
    }

    override fun showHome() {
        mPager.setCurrentItem(2,true)
    }
    //endregion
}