package com.cabinInformationTechnologies.cabin.fragments.filterDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.MainActivity
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabin.fragments.filterDetail.adapters.*
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.*

class CabinCustomerFilterDetailFragment : com.cabinInformationTechnologies.cabinCustomerBase.BaseFragment(), CabinCustomerFilterDetailContracts.View {
    private val args: CabinCustomerFilterDetailFragmentArgs by navArgs()

    var presenter: CabinCustomerFilterDetailContracts.Presenter? =
        CabinCustomerFilterDetailPresenter(this)
    private lateinit var pageView: View

    private lateinit var recyclerView: RecyclerView

    private var filter: MODELFilter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_filter_detail, container, false)
        (activity!! as MainActivity).lockDrawer()
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
        recyclerView = pageView.findViewById(R.id.filter_detail_recycler_view)
        filter = (activity!! as MainActivity).getFilter()
        presenter?.setupPage(args.filterType, filter)
    }

    override fun setupCategoriesPage(dataset: MutableList<MODELFilterCategory>) {

    }

    override fun setupSexesPage(dataset: MutableList<MODELFilterSex>) {
        val viewManager = LinearLayoutManager(this.context)
        val viewAdapter = CabinCustomerFilterSexAdapter(this, dataset)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        pageView.findViewById<Button>(R.id.filter_detail_footer_confirm_button).setOnClickListener {
            filter?.sexes = viewAdapter.getDataset()
            activity?.onBackPressed()
        }
    }

    override fun setupSellersPage(dataset: MutableList<MODELFilterSeller>) {
        val viewManager = LinearLayoutManager(this.context)
        val viewAdapter = CabinCustomerFilterSellerAdapter(this, dataset)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        pageView.findViewById<Button>(R.id.filter_detail_footer_confirm_button).setOnClickListener {
            filter?.sellers = viewAdapter.getDataset()
            activity?.onBackPressed()
        }
    }

    override fun setupSizesPage(dataset: MutableList<MODELFilterSizeGroup>) {
        val viewManager = LinearLayoutManager(this.context)
        val viewAdapter = CabinCustomerFilterSizegroupAdapter(this, dataset)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        pageView.findViewById<Button>(R.id.filter_detail_footer_confirm_button).setOnClickListener {
            //TODO
        }
    }

    override fun setupColorsPage(dataset: MutableList<MODELFilterColor>) {
        val viewManager = GridLayoutManager(this.context, 4)
        val viewAdapter = CabinCustomerFilterColorsAdapter(this, dataset)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        pageView.findViewById<Button>(R.id.filter_detail_footer_confirm_button).setOnClickListener {
            filter?.colors = viewAdapter.getDataset()
            activity?.onBackPressed()
        }
    }

    override fun setupPricesPage(dataset: MutableList<MODELFilterPrice>) {
        val viewManager = LinearLayoutManager(this.context)
        val viewAdapter = CabinCustomerFilterPriceAdapter(this, dataset)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        pageView.findViewById<Button>(R.id.filter_detail_footer_confirm_button).setOnClickListener {
            filter?.filterPrices = viewAdapter.getDataset()
            activity?.onBackPressed()
        }
    }

    //endregion
}