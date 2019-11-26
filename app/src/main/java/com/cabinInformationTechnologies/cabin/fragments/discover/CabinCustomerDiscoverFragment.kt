package com.cabinInformationTechnologies.cabin.fragments.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.MainActivity
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSorts


class CabinCustomerDiscoverFragment : com.cabinInformationTechnologies.cabinCustomerBase.BaseFragment(), CabinCustomerDiscoverContracts.View {

    var presenter: CabinCustomerDiscoverContracts.Presenter? = CabinCustomerDiscoverPresenter(this)
    private lateinit var pageView: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: CabinCustomerDiscoverAdapter
    private lateinit var viewManager: GridLayoutManager

    private var page = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_discover, container, false)

        setupActivity()

        viewAdapter = CabinCustomerDiscoverAdapter(presenter)
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

    private fun setupActivity() {
        (activity!! as MainActivity).apply {
            layoutBackToDefault()
            unblockPage()
            setHeader(resources.getString(R.string.discover_label), null)
            hideBackButton()
            lockDrawer()
            hideDrawerButton()
            hideBackButton()
            hideClear()
            hideCross()
            showHeaderNavbar()
            hideProgressBar()
            hideRecyclerView()
            setHeaderColor(null as Int?)
        }
    }

    private fun setupPage() {
        pageView.findViewById<FrameLayout>(R.id.discover_header_bottom_bar_filter_layout)
            .setOnClickListener { presenter?.moveToFilter() }
        pageView.findViewById<FrameLayout>(R.id.discover_header_bottom_bar_arrange_layout)
            .setOnClickListener { presenter?.getSortOptions(this.context) }
//        if (){
//            clearPage()
            reloadProducts()
//        }
    }

    override fun showHeaderAndNavbar() {
        (activity!! as MainActivity).showHeaderNavbar()
    }

    private fun reloadProducts(){
        presenter?.getProducts(this.context, page)

        recyclerView = pageView.findViewById(R.id.discover_recyclerview)

        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = viewManager as RecyclerView.LayoutManager?
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

    override fun resetPage() {
        presenter?.myDataset?.clear()
        viewAdapter.notifyDataSetChanged()
        presenter?.resetPage(this.context)
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
            presenter?.myDataset?.addAll(products as Iterable<MODELProduct>)
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

    override fun showSorts(sorts: MODELSorts) {
        val context = this.context
        if (context != null) {
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setTitle(resources.getString(R.string.sort))
            val sortsArray: Array<String?> = arrayOfNulls(sorts.sorts.size)
            for (i in sortsArray.indices)
                sortsArray[i] = sorts.sorts[i]?.getName()
            builder.setItems(sortsArray) { dialog, index ->
                dialog.dismiss()
                presenter?.setSort(sorts.sorts[index])
            }
            builder.show()
        }
    }

    //endregion
}