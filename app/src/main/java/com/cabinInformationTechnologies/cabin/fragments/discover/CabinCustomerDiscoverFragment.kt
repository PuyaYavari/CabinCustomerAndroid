package com.cabinInformationTechnologies.cabin.fragments.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.MainActivity
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct


class CabinCustomerDiscoverFragment : com.cabinInformationTechnologies.cabinCustomerBase.BaseFragment(), CabinCustomerDiscoverContracts.View {

    var presenter: CabinCustomerDiscoverContracts.Presenter? = CabinCustomerDiscoverPresenter(this)
    private lateinit var pageView: View
    private lateinit var recyclerView: RecyclerView
    private val myDataset: MutableList<MODELProduct> = mutableListOf()
    private lateinit var viewAdapter: CabinCustomerDiscoverAdapter
    private lateinit var viewManager: GridLayoutManager

    private val pageSize = 20
    private var page = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_discover, container, false)

        (activity!! as MainActivity).layoutBackToDefault()
        (activity!! as MainActivity).unblockPage()
        (activity!! as MainActivity).setHeader(resources.getString(R.string.discover_label),null)
        (activity!! as MainActivity).hideBackButton()
        (activity!! as MainActivity).lockDrawer()
        (activity!! as MainActivity).hideBackButton()
        (activity!! as MainActivity).hideClear()
        (activity!! as MainActivity).hideCross()
        showHeaderAndNavbar()
        hideProgressBar()

        viewAdapter = CabinCustomerDiscoverAdapter(this, myDataset)
        viewManager = GridLayoutManager(this.context, 2)

        setupPage()
        return pageView
    }

    override fun onResume() {
        super.onResume()
        val context = context
        if (context != null)
            presenter?.updateLastEnteredProduct(context)
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
        pageView.findViewById<FrameLayout>(R.id.discover_header_bottom_bar_filter_layout)
            .setOnClickListener { presenter?.moveToFilter() }
//        if (){
//            clearPage()
            reloadProducts()
//        }
    }

    override fun showHeaderAndNavbar() {
        (activity!! as MainActivity).showHeaderNavbar()
    }

    private fun reloadProducts(){
        presenter?.getProducts(page,pageSize)

        recyclerView = pageView.findViewById(R.id.discover_recyclerview)

        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 30) {
                        hideHeaderAndNavbar()
                    } else if (dy < -30) {
                        showHeaderAndNavbar()
                    }
                }
            })
        }
    }

    private fun clearPage(){ //FIXME
        myDataset.clear()
        presenter?.resetPage()
        viewAdapter.notifyDataSetChanged()
    }

    override fun hideHeaderAndNavbar() {
        (activity!! as MainActivity).hideHeaderNavbar()
    }

    override fun moveToProductDetail(product: MODELProduct, position: Int) {
        presenter?.moveToProductDetail(product, position)
    }

    override fun addData(products: List<MODELProduct>?) {
        hideNoInternet()
        if (products != null)
            myDataset.addAll(products as Iterable<MODELProduct>)
        viewAdapter.notifyDataSetChanged()
        hideProgressBar()
    }

    override fun updateProduct(product: MODELProduct, position: Int) {
        viewAdapter.updateProduct(product, position)
    }

    override fun showProgressBar() {
        (activity!! as MainActivity).showProgressBar()
    }

    override fun hideProgressBar() {
        (activity!! as MainActivity).hideProgressBar()
    }

    override fun getCurrentItemCount(): Int {
        return pageView.findViewById<RecyclerView>(R.id.discover_recyclerview).adapter?.itemCount ?: 0
    }

    override fun feedback(message: String) {
        Toast.makeText(this.context, message, Toast.LENGTH_LONG).show()
    }

    override fun showNoInternet() {
        hideProgressBar()
        pageView.findViewById<ConstraintLayout>(R.id.discover_no_internet_layout).visibility = View.VISIBLE
        pageView.findViewById<Button>(R.id.discover_no_internet_button).setOnClickListener { setupPage() }
    }

    override fun hideNoInternet() {
        pageView.findViewById<ConstraintLayout>(R.id.discover_no_internet_layout).visibility = View.INVISIBLE
    }

    override fun unsetFilterButton() {
        pageView.findViewById<FrameLayout>(R.id.discover_header_bottom_bar_filter_layout)
            .setOnClickListener { }
    }

    override fun setFilterButton() {
        pageView.findViewById<FrameLayout>(R.id.discover_header_bottom_bar_filter_layout)
            .setOnClickListener { presenter?.moveToFilter() }
    }

    //endregion
}