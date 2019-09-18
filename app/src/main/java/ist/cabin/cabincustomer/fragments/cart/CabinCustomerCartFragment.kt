package ist.cabin.cabincustomer.fragments.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ist.cabin.cabinCustomerBase.BaseFragment
import ist.cabin.cabincustomer.MainActivity
import ist.cabin.cabincustomer.R


class CabinCustomerCartFragment : BaseFragment(), CabinCustomerCartContracts.View {

    var presenter: CabinCustomerCartContracts.Presenter? = CabinCustomerCartPresenter(this)
    private lateinit var pageView: View
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_cart, container, false)
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
        pageView.findViewById<LinearLayout>(R.id.cart_finish_trade_price_layout).setOnClickListener {
            presenter?.togglePriceDetail()
        }

        pageView.findViewById<Button>(R.id.cart_finish_trade_button).setOnClickListener { presenter?.moveToFinishTrade() }

        recyclerView = pageView.findViewById(R.id.cart_products_recyclerview)

        val myDataset: List<CabinCustomerCartContracts.Product> =
            listOf(CartProduct("1", 2),
                CartProduct("2", 1),
                CartProduct("3", 5)
            )

        val viewAdapter = CabinCustomerCartAdapter(this, myDataset)
        val viewManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
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

    //endregion
}