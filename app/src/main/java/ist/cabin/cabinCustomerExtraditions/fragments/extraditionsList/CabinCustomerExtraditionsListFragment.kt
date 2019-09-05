package ist.cabin.cabinCustomerExtraditions.fragments.extraditionsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ist.cabin.cabinCustomerBase.BaseFragment
import ist.cabin.cabinCustomerExtraditions.CabinCustomerExtraditionsActivity
import ist.cabin.cabinCustomerExtraditions.fragments.extraditionsList.adapter.CabinCustomerExtraditionsListAdapter
import ist.cabin.cabinCustomerExtraditions.fragments.extraditionsList.adapter.ExtraditionBox
import ist.cabin.cabinCustomerExtraditions.fragments.extraditionsList.adapter.ExtraditionStatusIDs
import ist.cabin.cabinCustomerExtraditions.fragments.extraditionsList.adapter.NoExtraditionBox
import ist.cabin.cabincustomer.R

class CabinCustomerExtraditionsListFragment : BaseFragment(),
    CabinCustomerExtraditionsListContracts.View {

    var presenter: CabinCustomerExtraditionsListContracts.Presenter? =
        CabinCustomerExtraditionsListPresenter(this)
    private lateinit var pageView: View
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_extraditions_list, container, false)
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
        (activity as CabinCustomerExtraditionsActivity).hideHeaderHelperText()
        (activity as CabinCustomerExtraditionsActivity).showBackArrow()
        (activity as CabinCustomerExtraditionsActivity).hideCross()

        pageView.findViewById<Button>(R.id.extraditions_list_footer_add_button)
            .setOnClickListener { presenter?.addExtradition() }

        recyclerView = pageView.findViewById(R.id.extraditions_list_recyclerview)
        presenter?.setupPage()
    }

    override fun addExtraditionListener() {
        presenter?.addExtradition()
    }

    override fun setupNoExtraditionList() {
        pageView.findViewById<Button>(R.id.extraditions_list_footer_add_button).apply {
            isClickable = false
            isEnabled = false
            isFocusable = false
        }
        pageView.findViewById<ConstraintLayout>(R.id.extraditions_list_footer_layout).visibility = View.GONE

        val myDataset: List<CabinCustomerExtraditionsListContracts.ExtraditionBox> = listOf(NoExtraditionBox())

        val viewAdapter = CabinCustomerExtraditionsListAdapter(this, myDataset)
        val viewManager = LinearLayoutManager(this.context)

        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun setupExtraditionsList() { //TODO: BASED ON DATA FROM INTERACTOR
        pageView.findViewById<Button>(R.id.extraditions_list_footer_add_button).apply {
            isClickable = true
            isEnabled = true
            isFocusable = true
        }
        pageView.findViewById<ConstraintLayout>(R.id.extraditions_list_footer_layout).visibility = View.VISIBLE

        val myDataset: MutableList<CabinCustomerExtraditionsListContracts.ExtraditionBox> = mutableListOf()

        myDataset.add(ExtraditionBox(ExtraditionStatusIDs.ONGOING))
        myDataset.add(ExtraditionBox(ExtraditionStatusIDs.ACCEPTED))
        myDataset.add(ExtraditionBox(ExtraditionStatusIDs.DENIED))

        val viewAdapter = CabinCustomerExtraditionsListAdapter(this, myDataset)
        val viewManager = LinearLayoutManager(this.context)

        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun moveToExtraditionDetail() {
        presenter?.moveToExtraditionDetail()
    }

    override fun moveToExtraditionDetailAccepted() {
        presenter?.moveToExtraditionDetailAccepted()
    }

    override fun moveToExtraditionDetailDenied() {
        presenter?.moveToExtraditionDetailDenied()
    }

    //endregion
}