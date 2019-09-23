package ist.cabin.cabincustomer.fragments.productDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import ist.cabin.cabinCustomerBase.BaseFragment
import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.local.MODELColor
import ist.cabin.cabinCustomerBase.models.local.MODELSize
import ist.cabin.cabincustomer.R



class CabinCustomerProductDetailFragment : BaseFragment(),
    CabinCustomerProductDetailContracts.View {
    val args: CabinCustomerProductDetailFragmentArgs by navArgs()

    private var colorSizesDataset : MutableMap<Int ,MutableList<MODELSize>> = mutableMapOf()
    private val colorsViewManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
    private val sizesViewManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
    private lateinit var colorsAdapter : CabinCustomerProductColorsAdapter
    private lateinit var sizesAdapter : CabinCustomerProductSizesAdapter
    private val colorsDataset : MutableList<MODELColor> = mutableListOf()
    private var sizesDataset: MutableList<MODELSize> = mutableListOf()
    private lateinit var mPager: ViewPager
    private lateinit var selectedColor: MODELColor
    private var selectedSize: MODELSize? = null

    private val imagesList: MutableList<Int> = mutableListOf() //FIXME: DOWNLOAD IMAGES AND PUT HERE

    var presenter: CabinCustomerProductDetailContracts.Presenter? =
        CabinCustomerProductDetailPresenter(this)
    private lateinit var pageView: View

    @Throws(Exception::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_product_detail, container, false)
        mPager = pageView.findViewById(R.id.product_detail_product_image_pager) ?: throw (Exception("Couldn't find image pager."))
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
        val imagePagerParams = mPager.layoutParams
        var displayWidth = -1
        val displayMetrics = context?.resources?.displayMetrics
        if(displayMetrics != null) {
            displayWidth = displayMetrics.widthPixels
        }
        imagePagerParams.width = displayWidth
        imagePagerParams.height = displayWidth * 4/3
        mPager.layoutParams = imagePagerParams

        populateImagesList()
        mPager.adapter = CabinCustomerProductDetailImagePagerAdapter(imagesList, LayoutInflater.from(this.context))

        val product = args.product

        pageView.findViewById<TextView>(R.id.product_detail_seller_name).text = product.sellerName
        pageView.findViewById<TextView>(R.id.product_detail_product_name).text = product.productName
        pageView.findViewById<TextView>(R.id.product_detail_product_id).text = product.productID
        pageView.findViewById<TextView>(R.id.product_detail_cargo_duration_text).text = product.cargoDuration
        pageView.findViewById<TextView>(R.id.product_detail_cargo_price).text = product.cargoType

        //pageView.findViewById<LinearLayout>(R.id.peoduct_datail_product_explanation_layout).visibility = View.VISIBLE //FIXME: MUST CHECK FOR EXPLANATION AND WRITE IF ANY EXISTS

        pageView.findViewById<Button>(R.id.product_detail_add_to_cart_button).setOnClickListener {
            if (selectedSize != null) {
                try {
                    addToCart(1, product.id, selectedColor, selectedSize!!)
                } catch (exception: Exception) {
                    Logger.error(this::class.java.name, "SelectedSize is null!!", exception)
                }
            } else {
                //TODO: SHOW MESSAGE OR SOMETHING
            }
        }

        val colors = product.colors
        var firstColorID = -1
        colors.forEach {modelColor ->
            val colorSizes: MutableList<MODELSize> = mutableListOf()
            modelColor.sizes.forEach{ modelSize ->
                colorSizes.add(modelSize)
            }

            if (firstColorID == -1) {
                firstColorID = modelColor.id
                setSelectedColor(modelColor)
            }

            colorsDataset.add(modelColor)
            colorSizesDataset[modelColor.id] = colorSizes
        }
        colorsAdapter = CabinCustomerProductColorsAdapter(this, colorsDataset)
        pageView.findViewById<RecyclerView>(R.id.product_detail_color_recycler_view).apply {
            setHasFixedSize(false)
            layoutManager = colorsViewManager
            adapter = colorsAdapter
        }

        showMeasuresOfColor(firstColorID)
        sizesAdapter = CabinCustomerProductSizesAdapter(this, sizesDataset)
        pageView.findViewById<RecyclerView>(R.id.product_detail_size_recycler_view).apply {
            setHasFixedSize(false)
            layoutManager = sizesViewManager
            adapter = sizesAdapter
        }
    }

    override fun showMeasuresOfColor(id: Int) {
        try {
            sizesDataset = colorSizesDataset[id]!!
            sizesAdapter.setDataset(sizesDataset)
        } catch (exception: Exception) {
            exception.suppressed

            //TODO: HANDLE CASE
        }
    }

    override fun populateImagesList() { //FIXME: DOWNLOAD IMAGES AND PUT HERE
        for (i in 0..5)
            imagesList.add(R.drawable.sample_product)
    }

    override fun addToCart(amount: Int, productId: Int, color: MODELColor, size: MODELSize) {
        val context = this.context
        if (context != null)
            presenter?.addToCart(context,amount,productId,color,size)
    }

    override fun setSelectedColor(color: MODELColor) {
        selectedColor = color
        Logger.info(null, "$color selected", null)
    }

    override fun setSelectedSize(size: MODELSize?) {
        selectedSize = size
        Logger.info(null, "$size selected", null)
    }

    //endregion
}
