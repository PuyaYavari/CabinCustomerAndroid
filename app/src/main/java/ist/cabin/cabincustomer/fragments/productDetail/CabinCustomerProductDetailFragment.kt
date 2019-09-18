package ist.cabin.cabincustomer.fragments.productDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ist.cabin.cabinCustomerBase.BaseFragment
import ist.cabin.cabincustomer.R

class CabinCustomerProductDetailFragment : BaseFragment(),
    CabinCustomerProductDetailContracts.View {

    var presenter: CabinCustomerProductDetailContracts.Presenter? =
        CabinCustomerProductDetailPresenter(this)
    private lateinit var pageView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_product_detail, container, false)
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
        val colorsViewManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        val colorsDataset : List<List<String>> = listOf(listOf("#000000", "Siyah"), listOf("#FFFFFF", "Beyaz"),
            listOf("#000000", "Siyah"), listOf("#FFFFFF", "Beyaz"), listOf("#000000", "siyah")) //TODO: REMOVE
        val colorsAdapter = CabinCustomerProductColorsAdapter(this, colorsDataset)
        pageView.findViewById<RecyclerView>(R.id.product_detail_color_recycler_view).apply {
            setHasFixedSize(false)
            layoutManager = colorsViewManager
            adapter = colorsAdapter
        }

        val sizesViewManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        val sizesDataset : List<String> = listOf("XXS", "XS", "S", "M", "L", "XL", "XXL", "XXXL") //TODO: REMOVE
        val sizesAdapter = CabinCustomerProductSizesAdapter(this, sizesDataset)
        pageView.findViewById<RecyclerView>(R.id.product_detail_size_recycler_view).apply {
            setHasFixedSize(false)
            layoutManager = sizesViewManager
            adapter = sizesAdapter
        }
    }

    //TODO: Implement your View methods here

    //endregion
}