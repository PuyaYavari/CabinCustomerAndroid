package com.cabinInformationTechnologies.cabin.fragments.productDetail

import android.graphics.Outline
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.ToggleButton
import androidx.cardview.widget.CardView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.cabinInformationTechnologies.cabin.MainActivity
import com.cabinInformationTechnologies.cabin.MainContracts
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELCart
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize


class CabinCustomerProductDetailFragment : com.cabinInformationTechnologies.cabinCustomerBase.BaseFragment(),
    CabinCustomerProductDetailContracts.View {
    private val args: CabinCustomerProductDetailFragmentArgs by navArgs()

    private val colorsViewManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
    private val sizesViewManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
    private lateinit var colorsAdapter : CabinCustomerProductColorsAdapter
    private lateinit var sizesAdapter : CabinCustomerProductSizesAdapter


    private lateinit var mPager: ViewPager
    private lateinit var indicatorAnimationContainer: MotionLayout

    private var previousPage = 0

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

        setupActivity()
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

    private fun setupActivity() {
        (activity!! as MainActivity).hideHeaderNavbar()
        (activity!! as MainActivity).lockDrawer()
        (activity!! as MainActivity).hideClear()
        (activity!! as MainActivity).hideCross()
        (activity!! as MainActivity).hideProgressBar()
        (activity!! as MainActivity).hideDrawerButton()
        (activity!! as MainActivity).hideRecyclerView()
        (activity!! as MainActivity).setHeaderColor(null as Int?)
    }

    private fun setupPage() {
        presenter?.requestProduct(this.context, args.product.getId(), findNavController())
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
        val context = this.context
        if (context != null)
            Logger.info(
                context,
                this::class.java.name,
                "imageListSize: ${imagesList.size}",
                null)
        setupImagesIndicator()

        mPager.adapter = CabinCustomerProductDetailImagePagerAdapter(imagesList, LayoutInflater.from(this.context))

        presenter?.setInitialColor(args.initialColor)
        presenter?.setProduct(args.product)

        presenter?.setCart((activity as MainActivity).presenter?.cart)

        pageView.findViewById<ToggleButton>(R.id.product_detail_favourite_button).outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View?, outline: Outline?) {
                if (view != null &&  outline != null)
                    outline.setOval(-4,0,view.width+4,view.height+8)
            }
        }
    }

    private fun setupImagesIndicator(){
        indicatorAnimationContainer = pageView.findViewById(R.id.product_detail_indicator_motion_layout)
        when (imagesList.size) {
            5 -> {
                pageView.findViewById<View>(R.id.image1_indicator).visibility = View.VISIBLE
                pageView.findViewById<View>(R.id.image2_indicator).visibility = View.VISIBLE
                pageView.findViewById<View>(R.id.image3_indicator).visibility = View.VISIBLE
                pageView.findViewById<View>(R.id.image4_indicator).visibility = View.VISIBLE
                pageView.findViewById<View>(R.id.image5_indicator).visibility = View.VISIBLE
            }
            4 -> {
                pageView.findViewById<View>(R.id.image1_indicator).visibility = View.VISIBLE
                pageView.findViewById<View>(R.id.image2_indicator).visibility = View.VISIBLE
                pageView.findViewById<View>(R.id.image3_indicator).visibility = View.VISIBLE
                pageView.findViewById<View>(R.id.image4_indicator).visibility = View.VISIBLE
                pageView.findViewById<View>(R.id.image5_indicator).visibility = View.GONE
            }
            3 -> {
                pageView.findViewById<View>(R.id.image1_indicator).visibility = View.VISIBLE
                pageView.findViewById<View>(R.id.image2_indicator).visibility = View.VISIBLE
                pageView.findViewById<View>(R.id.image3_indicator).visibility = View.VISIBLE
                pageView.findViewById<View>(R.id.image4_indicator).visibility = View.GONE
                pageView.findViewById<View>(R.id.image5_indicator).visibility = View.GONE
            }
            2 -> {
                pageView.findViewById<View>(R.id.image1_indicator).visibility = View.VISIBLE
                pageView.findViewById<View>(R.id.image2_indicator).visibility = View.VISIBLE
                pageView.findViewById<View>(R.id.image3_indicator).visibility = View.GONE
                pageView.findViewById<View>(R.id.image4_indicator).visibility = View.GONE
                pageView.findViewById<View>(R.id.image5_indicator).visibility = View.GONE
            }
            1 -> {
                pageView.findViewById<View>(R.id.image1_indicator).visibility = View.VISIBLE
                pageView.findViewById<View>(R.id.image2_indicator).visibility = View.GONE
                pageView.findViewById<View>(R.id.image3_indicator).visibility = View.GONE
                pageView.findViewById<View>(R.id.image4_indicator).visibility = View.GONE
                pageView.findViewById<View>(R.id.image5_indicator).visibility = View.GONE
            }
            else -> {
                pageView.findViewById<View>(R.id.image1_indicator).visibility = View.GONE
                pageView.findViewById<View>(R.id.image2_indicator).visibility = View.GONE
                pageView.findViewById<View>(R.id.image3_indicator).visibility = View.GONE
                pageView.findViewById<View>(R.id.image4_indicator).visibility = View.GONE
                pageView.findViewById<View>(R.id.image5_indicator).visibility = View.GONE
                val context = this.context
                if (context != null)
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "No Image!!",
                        null)
                //TODO: SHOW PLACEHOLDER
            }
        }
        mPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageSelected(position: Int) {
                if(previousPage < position) {
                    when (imagesList.size) {
                        5 -> when (position) {
                            1 -> {
                                indicatorAnimationContainer
                                    .setTransition(R.id.product_detail_0p_indicator, R.id.product_detail_024p_indicator)
                                indicatorAnimationContainer.transitionToEnd()
                            }
                            2 -> {
                                indicatorAnimationContainer
                                    .setTransition(R.id.product_detail_024p_indicator, R.id.product_detail_05p_indicator)
                                indicatorAnimationContainer.transitionToEnd()
                            }
                            3 -> {
                                indicatorAnimationContainer
                                    .setTransition(R.id.product_detail_05p_indicator, R.id.product_detail_075p_indicator)
                                indicatorAnimationContainer.transitionToEnd()
                            }
                            4 -> {
                                indicatorAnimationContainer
                                    .setTransition(R.id.product_detail_075p_indicator, R.id.product_detail_1p_indicator)
                                indicatorAnimationContainer.transitionToEnd()
                            }
                        }
                        4 -> when (position) {
                            1 -> {
                                indicatorAnimationContainer
                                    .setTransition(R.id.product_detail_0p_indicator, R.id.product_detail_033p_indicator)
                                indicatorAnimationContainer.transitionToEnd()
                            }
                            2 -> {
                                indicatorAnimationContainer
                                    .setTransition(R.id.product_detail_033p_indicator, R.id.product_detail_066p_indicator)
                                indicatorAnimationContainer.transitionToEnd()
                            }
                            3 -> {
                                indicatorAnimationContainer
                                    .setTransition(R.id.product_detail_066p_indicator, R.id.product_detail_1p_indicator)
                                indicatorAnimationContainer.transitionToEnd()
                            }
                        }
                        3 -> when (position) {
                            1 -> {
                                indicatorAnimationContainer
                                    .setTransition(R.id.product_detail_0p_indicator, R.id.product_detail_05p_indicator)
                                indicatorAnimationContainer.transitionToEnd()
                            }
                            2 -> {
                                indicatorAnimationContainer
                                    .setTransition(R.id.product_detail_05p_indicator, R.id.product_detail_1p_indicator)
                                indicatorAnimationContainer.transitionToEnd()
                            }
                        }
                        2 -> when (position) {
                            1 -> {
                                indicatorAnimationContainer
                                    .setTransition(R.id.product_detail_0p_indicator, R.id.product_detail_1p_indicator)
                                indicatorAnimationContainer.transitionToEnd()
                            }
                        }
                    }
                } else if (previousPage > position) {
                    when (imagesList.size) {
                        5 -> when (position) {
                            0 -> {
                                indicatorAnimationContainer
                                    .setTransition(
                                        R.id.product_detail_024p_indicator,
                                        R.id.product_detail_0p_indicator
                                    )
                                indicatorAnimationContainer.transitionToEnd()
                            }
                            1 -> {
                                indicatorAnimationContainer
                                    .setTransition(
                                        R.id.product_detail_05p_indicator,
                                        R.id.product_detail_024p_indicator
                                    )
                                indicatorAnimationContainer.transitionToEnd()
                            }
                            2 -> {
                                indicatorAnimationContainer
                                    .setTransition(
                                        R.id.product_detail_075p_indicator,
                                        R.id.product_detail_05p_indicator
                                    )
                                indicatorAnimationContainer.transitionToEnd()
                            }
                            3 -> {
                                indicatorAnimationContainer
                                    .setTransition(
                                        R.id.product_detail_1p_indicator,
                                        R.id.product_detail_075p_indicator
                                    )
                                indicatorAnimationContainer.transitionToEnd()
                            }
                        }
                        4 -> when (position) {
                            0 -> {
                                indicatorAnimationContainer
                                    .setTransition(
                                        R.id.product_detail_033p_indicator,
                                        R.id.product_detail_0p_indicator
                                    )
                                indicatorAnimationContainer.transitionToEnd()
                            }
                            1 -> {
                                indicatorAnimationContainer
                                    .setTransition(
                                        R.id.product_detail_066p_indicator,
                                        R.id.product_detail_033p_indicator
                                    )
                                indicatorAnimationContainer.transitionToEnd()
                            }
                            2 -> {
                                indicatorAnimationContainer
                                    .setTransition(
                                        R.id.product_detail_1p_indicator,
                                        R.id.product_detail_066p_indicator
                                    )
                                indicatorAnimationContainer.transitionToEnd()
                            }
                        }
                        3 -> when (position) {
                            0 -> {
                                indicatorAnimationContainer
                                    .setTransition(
                                        R.id.product_detail_05p_indicator,
                                        R.id.product_detail_0p_indicator
                                    )
                                indicatorAnimationContainer.transitionToEnd()
                            }
                            1 -> {
                                indicatorAnimationContainer
                                    .setTransition(
                                        R.id.product_detail_1p_indicator,
                                        R.id.product_detail_05p_indicator
                                    )
                                indicatorAnimationContainer.transitionToEnd()
                            }
                        }
                        2 -> when (position) {
                            0 -> {
                                indicatorAnimationContainer
                                    .setTransition(
                                        R.id.product_detail_1p_indicator,
                                        R.id.product_detail_0p_indicator
                                    )
                                indicatorAnimationContainer.transitionToEnd()
                            }
                        }
                    }
                }
                previousPage = position
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

    override fun setupProductDetail(product: MODELProduct) {
        pageView.findViewById<TextView>(R.id.product_detail_seller_name).text = product.getSellerName()
        pageView.findViewById<TextView>(R.id.product_detail_product_name).text = product.getProductName()
        pageView.findViewById<TextView>(R.id.product_detail_product_id).text = product.getProductId()
        pageView.findViewById<TextView>(R.id.product_detail_cargo_duration_text).text = product.getCargoDuration()
        pageView.findViewById<TextView>(R.id.product_detail_cargo_price).text = product.getCargoType()

        //pageView.findViewById<LinearLayout>(R.id.peoduct_datail_product_explanation_layout).visibility = View.VISIBLE //FIXME: MUST CHECK FOR EXPLANATION AND WRITE IF ANY EXISTS



        //TODO: IF DISCOUNT ENABLE product_detail_before_cart_price_layout
        //TODO: IF IN CART DISCOUNT ENABLE product_detail_in_cart_discount product_detail_before_cart_price_layout
    }

    override fun setupPrice(price: Double, priceUnit: String) {
        pageView.findViewById<TextView>(R.id.product_detail_before_discount_price).text = ""
        pageView.findViewById<TextView>(R.id.product_detail_before_discount_price_unit).text = ""
        pageView.findViewById<LinearLayout>(R.id.product_detail_before_discount_price_layout).visibility = View.GONE

        pageView.findViewById<LinearLayout>(R.id.product_detail_price_layout).visibility = View.VISIBLE
        pageView.findViewById<TextView>(R.id.product_detail_price).text = price.toString()
        pageView.findViewById<TextView>(R.id.product_detail_price_unit).text = priceUnit
    }

    override fun setupPrice(price: Double, discountedPrice: Double, priceUnit: String) {
        pageView.findViewById<TextView>(R.id.product_detail_before_discount_price).text = price.toString()
        pageView.findViewById<TextView>(R.id.product_detail_before_discount_price_unit).text = priceUnit
        pageView.findViewById<LinearLayout>(R.id.product_detail_before_discount_price_layout).visibility = View.VISIBLE

        pageView.findViewById<TextView>(R.id.product_detail_price).text = discountedPrice.toString()
        pageView.findViewById<TextView>(R.id.product_detail_price_unit).text = priceUnit
        pageView.findViewById<LinearLayout>(R.id.product_detail_price_layout).visibility = View.VISIBLE
    }

    override fun showSizesOfColor(id: Int) {
        try {
            val sizes = presenter?.getSizesOfColor(id)
            if (sizes != null)
                sizesAdapter.setDataset(sizes)
        } catch (exception: Exception) {
            exception.suppressed

            //TODO: HANDLE CASE
        }
    }

    override fun populateImagesList() { //FIXME: DOWNLOAD IMAGES AND PUT HERE
        imagesList.clear()
        for (i in 0..4)
            imagesList.add(R.drawable.sample_product)
    }

    override fun addToCart(
        productId: Int,
        amount: Int,
        colorId: Int,
        sizeId: Int
    ){
        val context = this.context
        if (context != null)
            presenter?.addToCart(context,
                    productId,
                    amount,
                    colorId,
                    sizeId
                )
    }

    override fun setSelectedColor(color: MODELColor) {
        presenter?.setSelectedColor(color)
        setFavoriteButtonTo(color)
        val context = this.context
        if (context != null)
            Logger.info(
                context,
                null,
                "$color selected",
                null)
    }

    override fun setSelectedSize(size: MODELSize?) {
        presenter?.setSelectedSize(size)
        val context = this.context
        if (context != null)
            Logger.info(
                context,
                null,
                "$size selected",
                null)
    }

    override fun setupColors(colorsDataset : MutableList<MODELColor>) {
        colorsAdapter = CabinCustomerProductColorsAdapter(this, colorsDataset)
        pageView.findViewById<RecyclerView>(R.id.product_detail_color_recycler_view).apply {
            setHasFixedSize(false)
            layoutManager = colorsViewManager
            adapter = colorsAdapter
        }
    }

    override fun setupSizes(sizesDataset: MutableList<MODELSize>, firstColorID: Int) {
        sizesAdapter = CabinCustomerProductSizesAdapter(this, sizesDataset)
        pageView.findViewById<RecyclerView>(R.id.product_detail_size_recycler_view).apply {
            setHasFixedSize(false)
            layoutManager = sizesViewManager
            adapter = sizesAdapter
        }
        showSizesOfColor(firstColorID)
    }


    override fun showDefaultMessage() {
        pageView.findViewById<CardView>(R.id.product_detail_add_to_cart_button_progress_bar)
            .visibility = View.GONE
        pageView.findViewById<CardView>(R.id.product_detail_info_popup).apply {
            visibility = View.VISIBLE
            pageView.findViewById<TextView>(R.id.product_detail_info_popup_text).text =
                resources.getText(R.string.product_added_to_cart)
            Handler().postDelayed({
                visibility = View.INVISIBLE
            }, 3000)
        }
    }

    override fun setFavoriteButtonTo(color: MODELColor) {
        presenter?.setupFavoriteButton(color.favourite)
        val context = this.context
        if (context != null)
            pageView.findViewById<ToggleButton>(R.id.product_detail_favourite_button)
                .setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked)
                        presenter?.addToFavorite(context, args.product, color)
                    else
                        presenter?.removeFromFavorite(context, args.product, color)
                }
    }

    override fun checkFavorite() {
        pageView.findViewById<ToggleButton>(R.id.product_detail_favourite_button).apply {
            setOnCheckedChangeListener(null)
            isChecked = true
        }
    }

    override fun uncheckFavorite() {
        pageView.findViewById<ToggleButton>(R.id.product_detail_favourite_button).apply {
            setOnCheckedChangeListener(null)
            isChecked = false
        }
    }

    override fun setTickOnColor(color: MODELColor) {
        colorsAdapter.setTickOnColor(color)
    }

    override fun showSelectSizeFor(
        product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct,
        color: MODELColor,
        callback: MainContracts.SelectSizeCallback
    ) {
        (activity!! as MainActivity).showSelectSize(product, color, callback)
    }

    override fun indicateSelectedSize(size: MODELSize) {
        sizesAdapter.indicateSelectedSize(size)
    }

    override fun showProgressBar() {
        (activity!! as MainActivity).showProgressBar()
    }

    override fun hideProgressBar() {
        (activity!! as MainActivity).hideProgressBar()
    }

    override fun directToRegistration() {
        (activity!! as MainActivity).presenter?.moveToRegistration()
    }

    override fun showAddToCartButton() {
        pageView.findViewById<CardView>(R.id.product_detail_add_to_cart_button_progress_bar).visibility = View.GONE
        pageView.findViewById<CardView>(R.id.product_detail_add_to_cart_counter).visibility = View.GONE
        pageView.findViewById<Button>(R.id.product_detail_add_to_cart_button).apply {
            setOnClickListener {
                presenter?.addToCartButtonListener(this.context)
            }
            visibility = View.VISIBLE
        }
    }

    override fun showButtonProgressBar() {
        pageView.findViewById<CardView>(R.id.product_detail_add_to_cart_button_progress_bar).visibility = View.VISIBLE
        pageView.findViewById<CardView>(R.id.product_detail_add_to_cart_counter).visibility = View.GONE
        pageView.findViewById<Button>(R.id.product_detail_add_to_cart_button).apply {
            setOnClickListener { }
            visibility = View.VISIBLE
        }
    }

    override fun showCounter(amount: Int) {
        val context = this.context
        pageView.findViewById<CardView>(R.id.product_detail_add_to_cart_button_progress_bar).visibility = View.GONE
        pageView.findViewById<CardView>(R.id.product_detail_add_to_cart_counter).visibility = View.VISIBLE
        pageView.findViewById<Button>(R.id.product_detail_add_to_cart_counter_dec_button).setOnClickListener {
            if (context != null)
                presenter?.decreaseAmount(context)
        }
        pageView.findViewById<TextView>(R.id.product_detail_add_to_cart_counter_count).text = amount.toString()
        pageView.findViewById<Button>(R.id.product_detail_add_to_cart_counter_inc_button).setOnClickListener {
            if (context != null)
                presenter?.increaseAmount(context)
        }
        pageView.findViewById<Button>(R.id.product_detail_add_to_cart_button).apply {
            setOnClickListener { }
            visibility = View.INVISIBLE
        }
    }

    override fun setActivityCart(cart: MODELCart) {
        (activity as MainActivity).presenter?.cart = cart
    }

    override fun setAmount(amount: Int) {
        pageView.findViewById<TextView>(R.id.product_detail_add_to_cart_counter_count).text = amount.toString()
    }

    override fun closePage() {
        findNavController().popBackStack()
    }

    //endregion
}
