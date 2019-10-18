package ist.cabin.cabincustomer.fragments.filterDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ist.cabin.cabinCustomerBase.BaseFragment
import ist.cabin.cabinCustomerBase.models.local.*
import ist.cabin.cabincustomer.MainActivity
import ist.cabin.cabincustomer.R
import ist.cabin.cabincustomer.fragments.filterDetail.adapters.CabinCustomerFilterColorsAdapter

class CabinCustomerFilterDetailFragment : BaseFragment(), CabinCustomerFilterDetailContracts.View {
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
        presenter?.setupPage(args.filterType, (activity!! as MainActivity).getFilter())
    }

    override fun setupCategoriesPage(dataset: MutableList<MODELCategory>) {

    }

    override fun setupSexesPage(dataset: MutableList<MODELSex>) {

    }

    override fun setupSellersPage(dataset: MutableList<MODELSellerName>) {

    }

    override fun setupSizesPage(dataset: MutableList<MODELSizeNameGroup>) {

    }

    override fun setupColorsPage(dataset: MutableList<MODELRawColor>) {
        val viewManager = GridLayoutManager(this.context, 4)
        val viewAdapter = CabinCustomerFilterColorsAdapter(this, dataset)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun setupPricesPage(dataset: MutableList<MODELPriceInterval>) {

    }

    //endregion
}