package ist.cabin.cabincustomer.fragments.favorites

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ist.cabin.cabinCustomerBase.BaseFragment
import ist.cabin.cabinCustomerBase.GlobalData
import ist.cabin.cabinCustomerBase.models.local.MODELColor
import ist.cabin.cabinCustomerBase.models.local.MODELProduct
import ist.cabin.cabinCustomerBase.models.local.MODELSize
import ist.cabin.cabincustomer.MainActivity
import ist.cabin.cabincustomer.R

class CabinCustomerFavoritesFragment : BaseFragment(), CabinCustomerFavoritesContracts.View {

    var presenter: CabinCustomerFavoritesContracts.Presenter? = CabinCustomerFavoritesPresenter(this)
    private lateinit var pageView: View
    private lateinit var recyclerView: RecyclerView
    private var myDataset: MutableList<MODELProduct> = mutableListOf()
    private lateinit var viewAdapter: CabinCustomerFavoritesAdapter
    private lateinit var viewManager: GridLayoutManager

    private var page = 1 //TODO: PAGING

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_favorites, container, false)
        return pageView
    }

    override fun onResume() {
        super.onResume()

        (activity!! as MainActivity).setHeader(resources.getString(R.string.favorites_label),null)
        (activity!! as MainActivity).showNavbar()
        (activity!! as MainActivity).hideBackButton()
        (activity!! as MainActivity).lockDrawer()
        (activity!! as MainActivity).hideBackButton()

        if (GlobalData.loggedIn) {
            setupPage()
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
        recyclerView = pageView.findViewById(R.id.favorites_recycler_view)
        viewAdapter = CabinCustomerFavoritesAdapter(this, myDataset)
        viewManager = GridLayoutManager(this.context, 3)
        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        val context = this.context
        if (context != null)
            presenter?.getFavorites(context, page)
    }


    override fun showData(products: List<MODELProduct>) = viewAdapter.setData(products)

    override fun moveToProductDetail(product: MODELProduct) {
        presenter?.moveToProductDetail(product)
    }

    override fun addToCart(
        context: Context,
        amount: Int,
        productId: Int,
        color: MODELColor,
        size: MODELSize
    ) {
        presenter?.addToCart(context, amount, productId, color, size)
    }

    //endregion
}