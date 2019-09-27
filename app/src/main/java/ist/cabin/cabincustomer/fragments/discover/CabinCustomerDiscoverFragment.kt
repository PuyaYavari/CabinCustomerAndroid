package ist.cabin.cabincustomer.fragments.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ist.cabin.cabinCustomerBase.BaseFragment
import ist.cabin.cabinCustomerBase.models.local.MODELProduct
import ist.cabin.cabincustomer.MainActivity
import ist.cabin.cabincustomer.R


class CabinCustomerDiscoverFragment : BaseFragment(), CabinCustomerDiscoverContracts.View {

    var presenter: CabinCustomerDiscoverContracts.Presenter? = CabinCustomerDiscoverPresenter(this)
    private lateinit var pageView: View
    private lateinit var recyclerView: RecyclerView
    private val myDataset: MutableList<MODELProduct> = mutableListOf()
    private lateinit var viewAdapter: CabinCustomerDiscoverAdapter
    private lateinit var viewManager: GridLayoutManager

    private val pageSize = 20
    private var page = 1

    private lateinit var discoverHeaderTransitionContainer: MotionLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_discover, container, false)
        discoverHeaderTransitionContainer = pageView.findViewById(R.id.discover_motion_layout)
        showHeaderAndNavbar()
        (activity!! as MainActivity).showNavbar()

        viewAdapter = CabinCustomerDiscoverAdapter(this, myDataset)
        viewManager = GridLayoutManager(this.context, 2)

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
//        if (){
//            clearPage()
            reloadProducts()
//        }
    }

    override fun showHeaderAndNavbar() {
        if (pageView.findViewById<ConstraintLayout>(R.id.discover_header).translationY == resources.getDimension(R.dimen.defaultHeaderHeightNegative)) {
            (activity!! as MainActivity).showNavbar()
            discoverHeaderTransitionContainer.setTransition(
                R.id.discoverHeaderHidden,
                R.id.discoverHeaderVisible
            )
            discoverHeaderTransitionContainer.transitionToEnd()
        }
    }

    private fun reloadProducts(){
        presenter?.getItemData(page,pageSize)

        recyclerView = pageView.findViewById(R.id.discover_recyclerview)

        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 30) {
                        hideHeaderAndNavbar()
                    } else if (dy < -30) {
                        showHeaderAndNavbar()
                    }
                }
            })
        }
    }

    private fun clearPage(){ //FIXME
        myDataset.clear()
        presenter?.resetPage()
        viewAdapter.notifyDataSetChanged()
    }

    override fun hideHeaderAndNavbar() {
        if (pageView.findViewById<ConstraintLayout>(R.id.discover_header).translationY == 0f) {
            (activity!! as MainActivity).hideNavbar()
            discoverHeaderTransitionContainer.setTransition(
                R.id.discoverHeaderVisible,
                R.id.discoverHeaderHidden
            )
            discoverHeaderTransitionContainer.transitionToEnd()
        }
    }

    override fun moveToProductDetail(product: MODELProduct) {
        hideHeaderAndNavbar()
        presenter?.moveToProductDetail(product)
    }

    override fun addData(products: List<MODELProduct>?) {
        if (products != null)
            myDataset.addAll(products as Iterable<MODELProduct>)
        viewAdapter.notifyDataSetChanged()
    }

    //endregion
}