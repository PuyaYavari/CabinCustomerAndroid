package ist.cabin.cabinCustomerExtraditions.fragments.addExtraditionCurrentItemsList


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ist.cabin.cabinCustomerBase.BaseFragment
import ist.cabin.cabinCustomerExtraditions.CabinCustomerExtraditionsActivity
import ist.cabin.cabincustomer.R


class CabinCustomerAddExtraditionCurrentItemsListFragment : BaseFragment(),
    CabinCustomerAddExtraditionCurrentItemsListContracts.View {

    var presenter: CabinCustomerAddExtraditionCurrentItemsListContracts.Presenter? =
        CabinCustomerAddExtraditionCurrentItemsListPresenter(this)
    private lateinit var pageView: View
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_extraditions_current_products_list, container, false)
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

    private fun onBackPressed(){
        activity!!.onBackPressed()
    }

    private fun setupPage() {
        (activity as CabinCustomerExtraditionsActivity)
            .showHeaderHelperText(this.resources.getString(
                R.string.extraditions_current_products_list_helper_text))
        (activity as CabinCustomerExtraditionsActivity).showBackArrow()
        (activity as CabinCustomerExtraditionsActivity).hideCross()

        recyclerView = pageView.findViewById(R.id.extraditions_current_products_list_recyclerview)

        val myDataset: List<String> = listOf("10","20","30","40","50","60","70","80","90","100","110","120") //TODO: REMOVE

        val viewAdapter = CabinCustomerAddExtraditionCurrentItemsListAdapter(this, myDataset)
        val viewManager = LinearLayoutManager(this.context)
        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        pageView.findViewById<Button>(R.id.extraditions_current_products_list_add_button).setOnClickListener {
            presenter?.moveForward()
        }
    }

    //endregion
}