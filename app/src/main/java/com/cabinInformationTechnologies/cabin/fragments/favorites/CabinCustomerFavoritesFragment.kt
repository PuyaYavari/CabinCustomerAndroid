package com.cabinInformationTechnologies.cabin.fragments.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.R

class CabinCustomerFavoritesFragment : com.cabinInformationTechnologies.cabinCustomerBase.BaseFragment(), CabinCustomerFavoritesContracts.View {

    var presenter: CabinCustomerFavoritesContracts.Presenter? = CabinCustomerFavoritesPresenter(this)
    private lateinit var pageView: View
    private lateinit var recyclerView: RecyclerView
    private var myDataset: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct> = mutableListOf()
    private lateinit var viewAdapter: CabinCustomerFavoritesAdapter
    private lateinit var viewManager: GridLayoutManager

    private var page = 1 //TODO: PAGING

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_favorites, container, false)
        return pageView
    }

    override fun onResume() {
        super.onResume()

        (activity!! as com.cabinInformationTechnologies.cabin.MainActivity).setHeader(resources.getString(R.string.favorites_label),null)
        (activity!! as com.cabinInformationTechnologies.cabin.MainActivity).hideBackButton()
        (activity!! as com.cabinInformationTechnologies.cabin.MainActivity).lockDrawer()
        (activity!! as com.cabinInformationTechnologies.cabin.MainActivity).hideBackButton()
        hideProgressBar()

        if (com.cabinInformationTechnologies.cabinCustomerBase.GlobalData.loggedIn) {
            setupPage()
            if ((activity!! as com.cabinInformationTechnologies.cabin.MainActivity).findViewById<ConstraintLayout>(R.id.not_logged_in_layout)
                    .visibility == View.INVISIBLE) {
                (activity!! as com.cabinInformationTechnologies.cabin.MainActivity).layoutBackToDefault()
                (activity!! as com.cabinInformationTechnologies.cabin.MainActivity).showHeaderNavbar()
            } else
                (activity!! as com.cabinInformationTechnologies.cabin.MainActivity).hideNeedLogin()
        } else
            (activity!! as com.cabinInformationTechnologies.cabin.MainActivity).showNeedLogin()

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


    override fun showData(products: List<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct>) = viewAdapter.setData(products)

    override fun removeFromFavorites(product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct) {
        val context = this.context
        if (context != null)
            presenter?.removeFromFavorites(context, product)
    }

    override fun moveToProductDetail(product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct) {
        presenter?.moveToProductDetail(product)
    }

    override fun undoRemove() {
        viewAdapter.undoLastRemove()
    }

    override fun addToCart(
        amount: Int,
        productId: Int,
        color: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor,
        size: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize
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
        product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct,
        color: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor,
        callback: com.cabinInformationTechnologies.cabin.MainContracts.SelectSizeCallback
    ) {
        (activity!! as com.cabinInformationTechnologies.cabin.MainActivity).showSelectSize(product, color, callback)
    }

    override fun showProgressBar() {
        (activity!! as com.cabinInformationTechnologies.cabin.MainActivity).showProgressBar()
    }

    override fun hideProgressBar() {
        (activity!! as com.cabinInformationTechnologies.cabin.MainActivity).hideProgressBar()
    }

    //endregion
}