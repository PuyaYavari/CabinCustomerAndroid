package com.cabinInformationTechnologies.cabin.fragments.cart

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.MainActivity
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.GlobalData
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELCart


class CabinCustomerCartFragment : com.cabinInformationTechnologies.cabinCustomerBase.BaseFragment(),
    CabinCustomerCartContracts.View, CabinCustomerCartContracts.ViewForInteractor {

    var presenter: CabinCustomerCartContracts.Presenter? = CabinCustomerCartPresenter(this)
    private lateinit var pageView: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: CabinCustomerCartAdapter
    private lateinit var viewManager: LinearLayoutManager

    private var totalPrice: Double = 0.0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_cart, container, false)
        return pageView
    }

    override fun onResume() {
        super.onResume()

        setupActivity()

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
        (activity!! as MainActivity).setHeader(resources.getString(R.string.cart_label),null)
        (activity!! as MainActivity).hideBackButton()
        (activity!! as MainActivity).lockDrawer()
        (activity!! as MainActivity).hideBackButton()
        (activity!! as MainActivity).hideClear()
        (activity!! as MainActivity).hideCross()
        (activity!! as MainActivity).hideDrawerButton()
        (activity!! as MainActivity).hideProgressBar()
        (activity!! as MainActivity).hideRecyclerView()
        (activity!! as MainActivity).setHeaderColor(null as Int?)
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
    }

    private fun setupPage() {
        recyclerView = pageView.findViewById(R.id.cart_products_recyclerview)

        presenter?.myDataset?.clear()
        totalPrice = 0.0

        getCart()

        val dataset = presenter?.myDataset
        if (dataset != null)
            viewAdapter = CabinCustomerCartAdapter(this, dataset)
        viewManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
            addItemDecoration(
                DividerItemDecoration(
                    activity!!,
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        pageView.findViewById<TextView>(R.id.cabin_finish_trade_final_price).text = totalPrice.toString()

        pageView.findViewById<LinearLayout>(R.id.cart_finish_trade_price_layout).setOnClickListener {
            presenter?.togglePriceDetail()
        }

        pageView.findViewById<Button>(R.id.cart_finish_trade_button).setOnClickListener {
            (activity as MainActivity).moveToFinishTrade()
        }

        (activity!! as MainActivity).setHeader(
            header = resources.getString(R.string.cart_label),
            headerExtras = presenter?.myDataset?.size.toString()
        )
    }

    private fun getCart() {
        val context = this.context
        if (context != null)
            presenter?.getCart(context)
    }

    override fun showPriceDetail() {
        pageView.findViewById<ConstraintLayout>(R.id.cart_finish_trade_price_detail_layout).apply {
            animate().translationY(-height.toFloat())
                .setListener(null)
        }

        pageView.findViewById<ImageView>(R.id.cabin_finish_trade_price_more_triangle).apply {
            animate().rotation(0f)
                .setListener(null)
        }
    }

    override fun hidePriceDetail() {
        pageView.findViewById<ConstraintLayout>(R.id.cart_finish_trade_price_detail_layout).apply {
            animate().translationY(0f)
                .setListener(null)
        }

        pageView.findViewById<ImageView>(R.id.cabin_finish_trade_price_more_triangle).apply {
            animate().rotation(180f)
                .setListener(null)
        }
    }

    override fun updateProduct(product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct) {
        val context = this.context
        if (context != null)
            presenter?.updateProduct(context, product)
    }

    override fun setData(cart: MODELCart) {
        viewAdapter.notifyDataSetChanged()

        pageView.findViewById<TextView>(R.id.cart_finish_trade_price_detail_middle_sum).text =
            cart.getSubtotal().toString()
        pageView.findViewById<TextView>(R.id.cart_finish_trade_price_detail_cargo_sum).text =
            cart.getShippingPrice().toString()
        pageView.findViewById<TextView>(R.id.cabin_finish_trade_final_price).text =
            cart.getTotal().toString()
        totalPrice = cart.getTotal()

        (activity!! as MainActivity).setHeader(
            header = resources.getString(R.string.cart_label),
            headerExtras = presenter?.myDataset?.size.toString()
        )
    }

    override fun clearAll() {
        presenter?.myDataset?.clear()
        clearCargoPrices()
        viewAdapter.notifyDataSetChanged()

        pageView.findViewById<TextView>(R.id.cart_finish_trade_price_detail_middle_sum).text = "0.0"
        pageView.findViewById<TextView>(R.id.cart_finish_trade_price_detail_cargo_sum).text = "0.0"
        pageView.findViewById<TextView>(R.id.cabin_finish_trade_final_price).text = "0.0"
        totalPrice = 0.0
        (activity!! as MainActivity).setHeader(
            header = resources.getString(R.string.cart_label),
            headerExtras = null
        )
    }

    @SuppressLint("InflateParams")
    override fun addShippingPrice(sellerName: String, price: Double) {
        val cargoPriceView = layoutInflater.inflate(R.layout.cabin_customer_cart_cargo_price_layout, null)
        cargoPriceView.apply {
            findViewById<TextView>(R.id.cart_finish_trade_price_detail_first_cargo_label).text =
                sellerName
            findViewById<TextView>(R.id.cart_finish_trade_price_detail_first_cargo_sum).text =
                price.toString()
        }
        pageView.findViewById<LinearLayout>(R.id.cart_finish_trade_price_detail_cargo_layout).addView(cargoPriceView)
    }

    override fun clearCargoPrices() {
        pageView.findViewById<LinearLayout>(R.id.cart_finish_trade_price_detail_cargo_layout).removeAllViews()
    }

    override fun moveToProductDetail(product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct, color: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor) {
        presenter?.moveToProductDetail(product, color)
    }

    override fun showProgressBar() {
        (activity!! as MainActivity).showProgressBar()
    }

    override fun hideProgressBar() {
        (activity!! as MainActivity).hideProgressBar()
    }

    override fun getCurrentItemCount(): Int {
        return pageView.findViewById<RecyclerView>(R.id.cart_products_recyclerview).adapter?.itemCount ?: 0
    }

    override fun feedback(message: String) {
        Toast.makeText(this.context, message, Toast.LENGTH_LONG).show()
    }

    override fun showNoInternet() {
        hideProgressBar()
        pageView.findViewById<ConstraintLayout>(R.id.cart_no_internet_layout).visibility = View.VISIBLE
        pageView.findViewById<Button>(R.id.cart_no_internet_button).setOnClickListener { setupPage() }
    }

    override fun hideNoInternet() {
        pageView.findViewById<ConstraintLayout>(R.id.cart_no_internet_layout).visibility = View.INVISIBLE
    }

    override fun getFragmentContext(): Context? {
        return this.context
    }

    override fun setActivityCart(cart: MODELCart) {
        (activity as MainActivity).presenter?.cart = cart
    }

    //endregion
}