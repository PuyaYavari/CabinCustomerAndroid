package ist.cabin.cabinCustomerOrders.fragments.pending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ist.cabin.cabinCustomerBase.BaseFragment
import ist.cabin.cabinCustomerOrders.CabinCustomerOrdersAdapter
import ist.cabin.cabincustomer.R

class CabinCustomerOrdersPendingFragment : BaseFragment(), CabinCustomerOrdersPendingFragmentContracts.View {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    var presenter: CabinCustomerOrdersPendingFragmentContracts.Presenter? =
        CabinCustomerOrdersPendingFragmentPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.cabin_customer_orders_main, container, false)

        val myDataset = arrayOf("5", "3", "12", "5", "3", "12", "5", "3", "12") //TODO: remove
        viewManager = LinearLayoutManager(this.context)
        viewAdapter = CabinCustomerOrdersAdapter(myDataset)

        recyclerView = view.findViewById<RecyclerView>(R.id.main_orders_recyclerview).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        return view
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

    //TODO: Implement your View methods here

    //endregion
}