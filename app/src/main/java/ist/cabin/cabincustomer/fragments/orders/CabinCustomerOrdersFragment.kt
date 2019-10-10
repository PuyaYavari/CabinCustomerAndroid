package ist.cabin.cabincustomer.fragments.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import ist.cabin.cabinCustomerBase.BaseFragment
import ist.cabin.cabinCustomerBase.GlobalData
import ist.cabin.cabincustomer.MainActivity
import ist.cabin.cabincustomer.R


class CabinCustomerOrdersFragment : BaseFragment(), CabinCustomerOrdersContracts.View {

    var presenter: CabinCustomerOrdersContracts.Presenter? = CabinCustomerOrdersPresenter(this)
    private lateinit var pageView: View
    private lateinit var mPager: ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_orders, container, false)

        (activity!! as MainActivity).layoutBackToDefault()
        (activity!! as MainActivity).setHeader(resources.getString(R.string.order_label),null)
        (activity!! as MainActivity).hideBackButton()
        (activity!! as MainActivity).showNavbar()
        (activity!! as MainActivity).lockDrawer()

        if (GlobalData.loggedIn)
            setupPage()

        return pageView
    }

    override fun onResume() {
        super.onResume()

        (activity!! as MainActivity).layoutBackToDefault()
        (activity!! as MainActivity).setHeader(resources.getString(R.string.order_label),null)
        (activity!! as MainActivity).hideBackButton()
        (activity!! as MainActivity).showNavbar()
        (activity!! as MainActivity).lockDrawer()
        if (GlobalData.loggedIn)
            setupPage()

        presenter?.onResume()
    }

    override fun onPause() {
        presenter?.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
        presenter = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter?.onCreate()
    }

    //region View

    private fun setupPage () {
        mPager = pageView.findViewById(R.id.orders_pager)
        val pagerAdapter =
            CabinCustomerOrdersScreenSlidePagerAdapter(
                childFragmentManager, 0
            )
        mPager.adapter = pagerAdapter
    }
    //endregion
}
