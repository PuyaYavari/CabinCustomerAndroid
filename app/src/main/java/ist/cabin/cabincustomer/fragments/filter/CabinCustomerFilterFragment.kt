package ist.cabin.cabincustomer.fragments.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomnavigation.BottomNavigationView
import ist.cabin.cabinCustomerBase.BaseFragment
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

    }

    private fun setupActivityLayout() {
        if ((activity!! as MainActivity).findViewById<BottomNavigationView>(R.id.navbar)
                .translationY != 0f)
            (activity!! as MainActivity).hideNavbarFromHidden()
        else
            (activity!! as MainActivity).hideNavbarFromDefault()
    }

    //TODO: Implement your View methods here

    //endregion
}