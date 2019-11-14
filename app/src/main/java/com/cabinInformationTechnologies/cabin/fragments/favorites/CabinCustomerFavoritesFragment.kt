package com.cabinInformationTechnologies.cabin.fragments.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.MainActivity
import com.cabinInformationTechnologies.cabin.MainContracts
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.BaseFragment
import com.cabinInformationTechnologies.cabinCustomerBase.GlobalData
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize

class CabinCustomerFavoritesFragment : BaseFragment(), CabinCustomerFavoritesContracts.View {

    var presenter: CabinCustomerFavoritesContracts.Presenter? = CabinCustomerFavoritesPresenter(this)
    private lateinit var pageView: View
    private lateinit var recyclerView: RecyclerView
    private var myDataset: MutableList<MODELProduct> = mutableListOf()
    private lateinit var viewAdapter: CabinCustomerFavoritesAdapter
    private lateinit var viewManager: GridLayoutManager

    private var page = 1 //TODO: PAGING

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_favorites, container, false)
        return pageView
    }

    override fun onResume() {
        super.onResume()

        (activity!! as MainActivity).setHeader(resources.getString(R.string.favorites_label),null)
        (activity!! as MainActivity).hideBackButton()
        (activity!! as MainActivity).lockDrawer()
        (activity!! as MainActivity).hideBackButton()
        (activity!! as MainActivity).hideClear()
        (activity!! as MainActivity).hideCross()
        hideProgressBar()

        if (GlobalData.loggedIn) {
            setupPage()
            if ((activity!! as MainActivity).findViewById<ConstraintLayout>(R.id.blocker_layout)
                    .visibility == View.INVISIBLE) {
                (activity!! as MainActivity).layoutBackToDefault()
                (activity!! as MainActivity).showHeaderNavbar()
            } else
                (activity!! as MainActivity).unblockPage()
        } else
            (activity!! as MainActivity).showNeedLogin()

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
        recyclerView = pageView.findViewById(R.id.favorites_recycler_view)
        viewAdapter = CabinCustomerFavoritesAdapter(this, myDataset)
        viewManager = GridLayoutManager(this.context, 3)
        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        val context = this.context
        if (context != null)
            presenter?.getFavorites(context, page)
    }


    override fun showData(products: List<MODELProduct>) {
        hideNoInternet()
        hideProgressBar()
        viewAdapter.setData(products)
    }

    override fun removeFromFavorites(product: MODELProduct) {
        val context = this.context
        if (context != null)
            presenter?.removeFromFavorites(context, product)
    }

    override fun moveToProductDetail(product: MODELProduct) {
        presenter?.moveToProductDetail(product)
    }

    override fun undoRemove() {
        viewAdapter.undoLastRemove()
    }

    override fun addToCart(
        amount: Int,
        productId: Int,
        color: MODELColor,
        size: MODELSize
    ) {
        val context = this.context
        if (context != null)
            presenter?.addToCart(context, amount, productId, color, size)
    }

    override fun renewData() {
        page = 1
        val context = context
        if (context != null)
            presenter?.getFavorites(context,page)
    }

    override fun showSelectSize(
        product: MODELProduct,
        color: MODELColor,
        callback: MainContracts.SelectSizeCallback
    ) {
        (activity!! as MainActivity).showSelectSize(product, color, callback)
    }

    override fun showProgressBar() {
        (activity!! as MainActivity).showProgressBar()
    }

    override fun hideProgressBar() {
        (activity!! as MainActivity).hideProgressBar()
    }

    override fun getCurrentItemCount(): Int {
        return pageView.findViewById<RecyclerView>(R.id.favorites_recycler_view).adapter?.itemCount ?: 0
    }

    override fun feedback(message: String) {
        Toast.makeText(this.context, message, Toast.LENGTH_LONG).show()
    }

    override fun showNoInternet() {
        hideProgressBar()
        pageView.findViewById<ConstraintLayout>(R.id.favorites_no_internet_layout).visibility = View.VISIBLE
        pageView.findViewById<Button>(R.id.favorites_no_internet_button).setOnClickListener { setupPage() }
    }

    override fun hideNoInternet() {
        pageView.findViewById<ConstraintLayout>(R.id.favorites_no_internet_layout).visibility = View.INVISIBLE
    }

    //endregion
}