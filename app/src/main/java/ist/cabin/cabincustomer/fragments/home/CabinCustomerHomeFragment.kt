package ist.cabin.cabincustomer.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ist.cabin.cabinCustomerBase.BaseFragment
import ist.cabin.cabinCustomerBase.GlobalData
import ist.cabin.cabincustomer.MainActivity
import ist.cabin.cabincustomer.R

class CabinCustomerHomeFragment : BaseFragment(), CabinCustomerHomeContracts.View {

    var presenter: CabinCustomerHomeContracts.Presenter? = CabinCustomerHomePresenter(this)
    private lateinit var pageView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_home, container, false)

        GlobalData.appRunning = true

        (activity!! as MainActivity).hideNeedLogin()
        (activity!! as MainActivity).setHeader(resources.getString(R.string.homepage_label),null)
        (activity!! as MainActivity).hideBackButton()
        (activity!! as MainActivity).showHeaderNavbar()
        (activity!! as MainActivity).unlockDrawer()
        (activity!! as MainActivity).hideBackButton()

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