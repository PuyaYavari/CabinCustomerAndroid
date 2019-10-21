package ist.cabin.cabincustomer.fragments.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ist.cabin.cabinCustomerBase.BaseFragment
import ist.cabin.cabinCustomerBase.GlobalData
import ist.cabin.cabinCustomerBase.models.local.MODELCart
import ist.cabin.cabinCustomerBase.models.local.MODELColor
import ist.cabin.cabinCustomerBase.models.local.MODELProduct
import ist.cabin.cabincustomer.MainActivity
import ist.cabin.cabincustomer.R


class CabinCustomerCartFragment : BaseFragment(), CabinCustomerCartContracts.View {

    var presenter: CabinCustomerCartContracts.Presenter? = CabinCustomerCartPresenter(this)
    private lateinit var pageView: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: CabinCustomerCartAdapter
    private lateinit var viewManager: LinearLayoutManager

    private var totalPrice = 0

    override val myDataset: MutableList<MODELProduct> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_cart, container, false)
        return pageView
    }

    override fun onResume() {
        super.onResume()

        (activity!! as MainActivity).setHeader(resources.getString(R.string.cart_label),null)
        (activity!! as MainActivity).hideBackButton()
        (activity!! as MainActivity).lockDrawer()
        (activity!! as MainActivity).hideBackButton()
        hideProgressBar()

        if (GlobalData.loggedIn) {
            setupPage()
            if ((activity!! as MainActivity).findViewById<ConstraintLayout>(R.id.not_logged_in_layout)
                    .visibility == View.INVISIBLE) {
                (activity!! as MainActivity).layoutBackToDefault()
                (activity!! as MainActivity).showHeaderNavbar()
            } else
                (activity!! as MainActivity).hideNeedLogin()
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
        recyclerView = pageView.findViewById(R.id.cart_products_recyclerview)

        getCart()

        viewAdapter = CabinCustomerCartAdapter(this, myDataset)
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

        pageView.findViewById<Button>(R.id.cart_finish_trade_button).setOnClickListener { presenter?.moveToFinishTrade() }

        (activity!! as MainActivity).setHeader(
            header = resources.getString(R.string.cart_label),
            headerExtras = myDataset.size.toString()
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

    override fun updateProduct(product: MODELProduct) {
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
            headerExtras = myDataset.size.toString()
        )
    }

    override fun clearAll() {
        myDataset.clear()
        clearCargoPrices()
        viewAdapter.notifyDataSetChanged()

        pageView.findViewById<TextView>(R.id.cart_finish_trade_price_detail_middle_sum).text = "0"
        pageView.findViewById<TextView>(R.id.cart_finish_trade_price_detail_cargo_sum).text = "0"
        pageView.findViewById<TextView>(R.id.cabin_finish_trade_final_price).text = "0"
        totalPrice = 0
        (activity!! as MainActivity).setHeader(
            header = resources.getString(R.string.cart_label),
            headerExtras = null
        )
    }

    override fun addShippingPrice(sellerName: String, price: Int) {
        val cargoPriceView = layoutInflater.inflate(R.layout.cabin_customer_cart_cargo_price_layout, null)
        cargoPriceView.apply {
            findViewById<TextView>(R.id.cart_finish_trade_price_detail_first_cargo_label).text =
                sellerName
            findViewById<TextView>(R.id.cart_finish_trade_price_detail_first_cargo_sum).text =
                price.toString()
        }
        pageView.findViewById<LinearLayout>(R.id.cart_finish_trade_price_detail_cargo_label).addView(cargoPriceView)
    }

    override fun clearCargoPrices() {
        pageView.findViewById<LinearLayout>(R.id.cart_finish_trade_price_detail_cargo_label).removeAllViews()
    }

    override fun moveToProductDetail(product: MODELProduct, color: MODELColor) {
        presenter?.moveToProductDetail(product, color)
    }

    override fun showProgressBar() {
        (activity!! as MainActivity).showProgressBar()
    }

    override fun hideProgressBar() {
        (activity!! as MainActivity).hideProgressBar()
    }

    override fun feedback(message: String) {
        Toast.makeText(this.context, message, Toast.LENGTH_LONG).show()
    }

    //endregion
}