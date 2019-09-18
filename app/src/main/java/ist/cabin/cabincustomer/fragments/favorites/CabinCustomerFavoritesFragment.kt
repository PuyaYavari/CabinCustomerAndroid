package ist.cabin.cabincustomer.fragments.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ist.cabin.cabinCustomerBase.BaseFragment
import ist.cabin.cabincustomer.MainActivity
import ist.cabin.cabincustomer.R

class CabinCustomerFavoritesFragment : BaseFragment(), CabinCustomerFavoritesContracts.View {

    var presenter: CabinCustomerFavoritesContracts.Presenter? = CabinCustomerFavoritesPresenter(this)
    private lateinit var pageView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_favorites, container, false)
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

    }

    //endregion
}