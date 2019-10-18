package ist.cabin.cabincustomer.fragments.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import ist.cabin.cabinCustomerBase.BaseFragment
import ist.cabin.cabinCustomerBase.models.local.MODELFilter
import ist.cabin.cabincustomer.FilterTypeIDs
import ist.cabin.cabincustomer.MainActivity
import ist.cabin.cabincustomer.R

class CabinCustomerFilterFragment : BaseFragment(), CabinCustomerFilterContracts.View {

    var presenter: CabinCustomerFilterContracts.Presenter? = CabinCustomerFilterPresenter(this)
    private lateinit var pageView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_filter, container, false)
        presenter?.getFilter(this.context)
        setupActivityLayout()
        setupPage()
        return pageView
    }

    override fun onResume() {
        super.onResume()
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

    private fun setupPage() {
        val context = this.context
        pageView.findViewById<ConstraintLayout>(R.id.filter_color_layout).setOnClickListener {
            presenter?.moveToFilterDetail(FilterTypeIDs.COLOR)
        }
    }

    private fun setupActivityLayout() {
        if ((activity!! as MainActivity).findViewById<BottomNavigationView>(R.id.navbar)
                .translationY != 0f)
            (activity!! as MainActivity).hideNavbarFromHidden()
        else
            (activity!! as MainActivity).hideNavbarFromDefault()
        (activity!! as MainActivity).lockDrawer()
        hideProgressBar()
    }

    override fun setFilter(filter: MODELFilter) {
        (activity!! as MainActivity).setFilter(filter)
    }

    override fun showProgressBar() {
        (activity!! as MainActivity).showProgressBar()
    }

    override fun hideProgressBar() {
        (activity!! as MainActivity).hideProgressBar()
    }

    //endregion
}