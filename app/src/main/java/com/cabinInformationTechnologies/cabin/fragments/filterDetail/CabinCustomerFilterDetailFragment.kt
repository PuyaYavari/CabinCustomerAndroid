package com.cabinInformationTechnologies.cabin.fragments.filterDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.MainActivity
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabin.fragments.filterDetail.adapters.*
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.*

class CabinCustomerFilterDetailFragment :
    com.cabinInformationTechnologies.cabinCustomerBase.BaseFragment(),
    CabinCustomerFilterDetailContracts.View, CabinCustomerFilterDetailContracts.FilterDetailFragment {
    private val args: CabinCustomerFilterDetailFragmentArgs by navArgs()

    var presenter: CabinCustomerFilterDetailContracts.Presenter? =
        CabinCustomerFilterDetailPresenter(this)
    private lateinit var pageView: View

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_filter_detail, container, false)
        (activity!! as MainActivity).lockDrawer()
        (activity!! as MainActivity).showClear(this)
        (activity!! as MainActivity).hideCross()
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
        presenter?.filter = (activity!! as MainActivity).getFilter()
        presenter?.setupPage(args.filterType)
    }

    override fun setupCategoriesPage(dataset: MutableList<MODELFilterCategory>) {
        val viewManager = LinearLayoutManager(this.context)
        val viewAdapter = CabinCustomerFilterCategoriesAdapter(this, dataset)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        pageView.findViewById<Button>(R.id.filter_detail_footer_confirm_button).setOnClickListener {
            presenter?.filter?.filterCategories = viewAdapter.getDataset()
            activity?.findNavController(R.id.nav_host_fragment)?.popBackStack()
        }
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
            presenter?.filter?.sexes = viewAdapter.getDataset()
            activity?.findNavController(R.id.nav_host_fragment)?.popBackStack()
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
            presenter?.filter?.sellers = viewAdapter.getDataset()
            activity?.findNavController(R.id.nav_host_fragment)?.popBackStack()
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
            presenter?.filter?.filterSizes = viewAdapter.getDataset()
            activity?.findNavController(R.id.nav_host_fragment)?.popBackStack()
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
            presenter?.filter?.colors = viewAdapter.getDataset()
            activity?.findNavController(R.id.nav_host_fragment)?.popBackStack()
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
            presenter?.filter?.filterPrices = viewAdapter.getDataset()
            activity?.findNavController(R.id.nav_host_fragment)?.popBackStack()
        }
    }

    override fun clearFilter() {
        presenter?.clearFilter(args.filterType)
    }

    override fun changeCategoriesDataset(dataset: MutableList<MODELFilterCategory>) {
        (recyclerView.adapter as CabinCustomerFilterCategoriesAdapter).setDataset(dataset)
    }

    override fun changeSexesDataset(dataset: MutableList<MODELFilterSex>) {
        (recyclerView.adapter as CabinCustomerFilterSexAdapter).setDataset(dataset)
    }

    override fun changeSellersDataset(dataset: MutableList<MODELFilterSeller>) {
        (recyclerView.adapter as CabinCustomerFilterSellerAdapter).setDataset(dataset)
    }

    override fun changeSizesDataset(dataset: MutableList<MODELFilterSizeGroup>) {
        (recyclerView.adapter as CabinCustomerFilterSizegroupAdapter).setDataset(dataset)
    }

    override fun changeColorsDataset(dataset: MutableList<MODELFilterColor>) {
        (recyclerView.adapter as CabinCustomerFilterColorsAdapter).setDataset(dataset)
    }

    override fun changePricesDataset(dataset: MutableList<MODELFilterPrice>) {
        (recyclerView.adapter as CabinCustomerFilterPriceAdapter).setDataset(dataset)
    }

    //endregion
}