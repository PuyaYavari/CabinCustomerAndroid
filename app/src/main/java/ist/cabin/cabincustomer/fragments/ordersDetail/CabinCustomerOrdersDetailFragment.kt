package ist.cabin.cabincustomer.fragments.ordersDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ist.cabin.cabinCustomerBase.BaseFragment
import ist.cabin.cabincustomer.R
import ist.cabin.cabincustomer.fragments.ordersDetail.adapter.*


class CabinCustomerOrdersDetailFragment : BaseFragment(), CabinCustomerOrdersDetailContracts.View {
    val args: CabinCustomerOrdersDetailFragmentArgs by navArgs()
    private lateinit var recyclerView: RecyclerView

    private lateinit var pageView: View
    var presenter: CabinCustomerOrdersDetailContracts.Presenter? = CabinCustomerOrdersDetailPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_orders_detail, container, false)
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
        pageView.findViewById<ImageButton>(R.id.orders_detail_back_button).setOnClickListener { activity!!.onBackPressed() }
        recyclerView = pageView.findViewById(R.id.orders_detail_recyclerview)

        val pageTypeID = args.pageType
        presenter?.setupPropperPage(pageTypeID)
    }

    override fun setupPendingPage() {

        val buttikCount = 2 // TODO: Remove line
        val ordersCount = 4 // TODO: Remove line

        val myDataset: MutableList<CabinCustomerOrdersDetailContracts.Detailbox> = mutableListOf()

        for (i in 0..buttikCount) {
            for (j in 0..ordersCount) {
                myDataset.add(Orderbox())
            }
            myDataset.add(Footerbox())
        }

        val viewAdapter = CabinCustomerOrdersDetailAdapter(this, myDataset)
        val viewManager = LinearLayoutManager(this.context)

        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        pageView.findViewById<TextView>(R.id.orders_addressbar_order_type_label).text = resources.getText(R.string.pending_orders_label)
    }

    override fun setupShippingPage() {


        val buttikCount = 3 // TODO: Remove line
        val ordersCount = 4 // TODO: Remove line

        val myDataset: MutableList<CabinCustomerOrdersDetailContracts.Detailbox> = mutableListOf()

        for (i in 0..buttikCount) {
            myDataset.add(Cargobox())
            for (j in 0..ordersCount) {
                myDataset.add(Orderbox())
            }
            myDataset.add(Footerbox())
        }

        val viewAdapter = CabinCustomerOrdersDetailAdapter(this, myDataset)
        val viewManager = LinearLayoutManager(this.context)

        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        pageView.findViewById<TextView>(R.id.orders_addressbar_order_type_label).text = resources.getText(R.string.shipping_orders_label)
    }

    override fun setupSentPage() {


        val buttikCount = 4 // TODO: Remove line
        val ordersCount = 4 // TODO: Remove line

        val myDataset: MutableList<CabinCustomerOrdersDetailContracts.Detailbox> = mutableListOf()

        for (i in 0..buttikCount) {
            myDataset.add(Headerbox())
            for (j in 0..ordersCount) {
                myDataset.add(Orderbox())
            }
            myDataset.add(Footerbox())
        }

        val viewAdapter = CabinCustomerOrdersDetailAdapter(this, myDataset)
        val viewManager = LinearLayoutManager(this.context)

        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        pageView.findViewById<TextView>(R.id.orders_addressbar_order_type_label).text = resources.getText(R.string.sent_orders_label)
    }

    //endregion
}