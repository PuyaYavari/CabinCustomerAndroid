package ist.cabin.cabincustomer.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import ist.cabin.cabinCustomerBase.BaseFragment
import ist.cabin.cabincustomer.MainActivity
import ist.cabin.cabincustomer.R

class CabinCustomerHomeFragment : BaseFragment(), CabinCustomerHomeContracts.View {

    var presenter: CabinCustomerHomeContracts.Presenter? = CabinCustomerHomePresenter(this)
    private lateinit var pageView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_home, container, false)
        (activity!! as MainActivity).showNavbar()
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
        pageView.findViewById<ImageButton>(R.id.go_to_login_login_button).setOnClickListener { presenter?.moveToRegistration() }
    }

    //endregion
}