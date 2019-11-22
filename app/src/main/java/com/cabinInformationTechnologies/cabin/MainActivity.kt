package com.cabinInformationTechnologies.cabin

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.fragments.discover.CabinCustomerDiscoverContracts
import com.cabinInformationTechnologies.cabin.fragments.filter.CabinCustomerFilterContracts
import com.cabinInformationTechnologies.cabin.fragments.filterDetail.CabinCustomerFilterDetailContracts
import com.cabinInformationTechnologies.cabinCustomerBase.BaseActivity
import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts
import com.cabinInformationTechnologies.cabinCustomerBase.GlobalData
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.cabin_customer_main.*


class MainActivity : BaseActivity(), MainContracts.View {
    private var currentNavController: LiveData<NavController>? = null

    var presenter: MainContracts.Presenter? =
        MainPresenter(this)

    private var mainTransitionContainer: MotionLayout? = null

    private var needLoginVisible = false

    private var sizeSelected = false

    private var selectSizeOpen = false

    private lateinit var drawerLayout: DrawerLayout

    //region Lifecycle

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActiveUser()
        setContentView(R.layout.cabin_customer_main)
        drawerLayout = findViewById(R.id.drawer_layout)
        presenter?.onCreate(intent.extras)

        overridePendingTransition(
            R.anim.slide_in_from_bottom,
            R.anim.slide_out_to_top
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            findViewById<DrawerLayout>(R.id.drawer_layout).setScrimColor(
                resources.getColor(android.R.color.transparent, theme)
            )
        } else {
            @Suppress("DEPRECATION")
            findViewById<DrawerLayout>(R.id.drawer_layout).setScrimColor(
                resources.getColor(android.R.color.transparent)
            )
        }

        mainTransitionContainer = findViewById(R.id.main_layout)
        layoutBackToDefault()

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        } // Else, need to wait for onRestoreInstanceState

        findViewById<LinearLayout>(R.id.sidebar_profile_options_layout).setOnClickListener { presenter?.moveToProfileOptions() }
        findViewById<LinearLayout>(R.id.sidebar_measure_options_layout).setOnClickListener { presenter?.moveToMeasure() }
        findViewById<LinearLayout>(R.id.sidebar_extraditions_layout).setOnClickListener { presenter?.moveToExtraditions() }
        findViewById<LinearLayout>(R.id.sidebar_help_layout).setOnClickListener {
            Toast.makeText(
                this@MainActivity,
                "Help",
                Toast.LENGTH_SHORT
            ).show()
        }
        findViewById<LinearLayout>(R.id.sidebar_exit_layout).setOnClickListener {
            lockDrawer()
            presenter?.requestLogout(this.applicationContext)
        }

        findViewById<ImageButton>(R.id.main_back_button).setOnClickListener { onBackPressed() }

        if (GlobalData.loggedIn)
            unblockPage()

        presenter?.updateCart(this.applicationContext)
        presenter?.clearFilter(this.applicationContext)
    }

    override fun onBackPressed() {
        when {
            drawer_layout.isDrawerOpen(GravityCompat.START) -> drawer_layout.closeDrawer(GravityCompat.START)
            selectSizeOpen -> hideSelectSize()
            else -> super.onBackPressed()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        super.onRestoreInstanceState(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar()
    }

    override fun onResume() {
        super.onResume()
        if (GlobalData.loggedIn)
            unblockPage()
        presenter?.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter?.onPause()
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }

    private fun getActiveUser() {
        val sharedPref = this.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        val userId = sharedPref.getInt("userId", 0)
        val session = sharedPref.getString("userSession", "")


        var user: MODELUser? = MODELUser()
        if (session != null && userId != 0)
            user?.setData(userId, session, null, null)
        else
            user = null

        GlobalData.activeUser = user
        GlobalData.userEmail = sharedPref.getString("userEmail", "")

        if (GlobalData.loggedIn)
            unblockPage()
    }

    private fun setupBottomNavigationBar() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navbar)

        val navGraphIds = listOf(
            R.navigation.customer_orders,
            R.navigation.customer_favorites,
            R.navigation.customer_home,
            R.navigation.customer_cart,
            R.navigation.customer_discover
        )

        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_fragment,
            intent = intent
        )

        currentNavController = controller
    }

    private fun setHeaderText(text: String?) {
        findViewById<TextView>(R.id.main_header_label).text = text
    }

    override fun showNeedLogin() {
        findViewById<TextView>(R.id.blocker_info_text).text = resources.getText(R.string.login_required)
        findViewById<Button>(R.id.unblock_button).text = resources.getText(R.string.sign_in)
        findViewById<Button>(R.id.unblock_button).setOnClickListener { presenter?.moveToRegistration() }
        if (mainTransitionContainer != null &&
                findViewById<ConstraintLayout>(R.id.blocker_layout).visibility != View.VISIBLE) {
            mainTransitionContainer?.setTransition(
                R.id.main_layout_default,
                R.id.main_layout_blocked
            )
            mainTransitionContainer?.transitionToEnd()
            needLoginVisible = true
        }
    }

    override fun showNoInternet() {
        findViewById<TextView>(R.id.blocker_info_text).text = resources.getText(R.string.no_internet)
        findViewById<Button>(R.id.unblock_button).text = resources.getText(R.string.retry)
        findViewById<Button>(R.id.unblock_button).setOnClickListener {
            //TODO
        }
        if (mainTransitionContainer != null &&
            findViewById<ConstraintLayout>(R.id.blocker_layout).visibility != View.VISIBLE) {
            mainTransitionContainer?.setTransition(
                R.id.main_layout_default,
                R.id.main_layout_blocked
            )
            mainTransitionContainer?.transitionToEnd()
            needLoginVisible = true
        }
    }

    override fun unblockPage() {
        if (mainTransitionContainer != null &&
                findViewById<ConstraintLayout>(R.id.blocker_layout).visibility == View.VISIBLE) {
            mainTransitionContainer?.setTransition(
                R.id.main_layout_blocked,
                R.id.main_layout_default
            )
            mainTransitionContainer?.transitionToEnd()
            needLoginVisible = false
        }
    }

    override fun layoutBackToDefault() {
        if (mainTransitionContainer != null) {
            mainTransitionContainer?.setTransition(
                R.id.main_layout_default,
                R.id.main_layout_default
            )
            mainTransitionContainer?.transitionToEnd()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    override fun showNavbar() {
        val hiddenNavbarTranslation = resources.getDimension(R.dimen.navbarHeight)
        if (mainTransitionContainer != null &&
            findViewById<BottomNavigationView>(R.id.navbar).translationY == hiddenNavbarTranslation
        ) {
            mainTransitionContainer?.setTransition(
                R.id.main_layout_navbar_hidden,
                R.id.main_layout_default
            )
            mainTransitionContainer?.transitionToEnd()
        }
    }

    override fun hideNavbarFromDefault() {
        if (mainTransitionContainer != null) {
            mainTransitionContainer?.setTransition(
                R.id.main_layout_default,
                R.id.main_layout_navbar_hidden
            )
            mainTransitionContainer?.transitionToEnd()
        }
    }

    override fun hideNavbarFromHidden() {
        val hiddenNavbarTranslation = resources.getDimension(R.dimen.navbarHeight)
        if (mainTransitionContainer != null &&
            findViewById<BottomNavigationView>(R.id.navbar).translationY == hiddenNavbarTranslation
        ) {
            mainTransitionContainer?.setTransition(
                R.id.main_layout_navbar_header_hidden,
                R.id.main_layout_navbar_hidden
            )
            mainTransitionContainer?.transitionToEnd()
        }
    }

    override fun showHeaderNavbar() {
        val hiddenNavbarTranslation = resources.getDimension(R.dimen.navbarHeight)
        if (mainTransitionContainer != null &&
            findViewById<BottomNavigationView>(R.id.navbar).translationY == hiddenNavbarTranslation
        ) {
            if (findViewById<ConstraintLayout>(R.id.main_header).translationY != 0f) {
                mainTransitionContainer?.setTransition(
                    R.id.main_layout_navbar_header_hidden,
                    R.id.main_layout_default
                )
            } else {
                mainTransitionContainer?.setTransition(
                    R.id.main_layout_navbar_hidden,
                    R.id.main_layout_default
                )
            }
            mainTransitionContainer?.transitionToEnd()
        }
    }

    override fun showClear(fragment: CabinCustomerFilterContracts.FilterFragment) {
        findViewById<TextView>(R.id.main_clear_button).apply {
            visibility = View.VISIBLE
            setOnClickListener { fragment.clearFilter() }
        }
    }

    override fun showClear(fragment: CabinCustomerFilterDetailContracts.FilterDetailFragment) {
        findViewById<TextView>(R.id.main_clear_button).apply {
            visibility = View.VISIBLE
            setOnClickListener { fragment.clearFilter() }
        }
    }

    override fun hideClear() {
        findViewById<TextView>(R.id.main_clear_button).visibility = View.GONE
    }

    override fun hideHeaderNavbar() {
        if (mainTransitionContainer != null &&
            findViewById<BottomNavigationView>(R.id.navbar).translationY == 0f
        ) {
            mainTransitionContainer?.setTransition(
                R.id.main_layout_default,
                R.id.main_layout_navbar_header_hidden
            )
            mainTransitionContainer?.transitionToEnd()
        }
    }

    override fun logout() {
        val sharedPref = this.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.remove("userId")
        editor.remove("userSession")
        editor.remove("userEmail")
        editor.apply()
        GlobalData.activeUser = null
    }

    override fun setHeader(header: String, headerExtras: String?) {
        if (headerExtras == null)
            setHeaderText(header)
        else
            setHeaderText("$header($headerExtras)")
    }

    override fun showBackButton() {
        findViewById<ImageButton>(R.id.main_back_button).apply {
            visibility = View.VISIBLE
            isEnabled = true
            isClickable = true
        }
    }

    override fun hideBackButton() {
        findViewById<ImageButton>(R.id.main_back_button).apply {
            visibility = View.INVISIBLE
            isEnabled = false
            isClickable = false
        }
    }

    override fun lockDrawer() {
        findViewById<DrawerLayout>(R.id.drawer_layout).setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    @SuppressLint("SetTextI18n")
    override fun unlockDrawer() {
        findViewById<DrawerLayout>(R.id.drawer_layout).setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)

        findViewById<TextView>(R.id.sidebar_user_info).text = "user: ${GlobalData.userEmail}"

        try {
            val pInfo = this.applicationContext.packageManager.getPackageInfo(packageName, 0)
            findViewById<TextView>(R.id.sidebar_version_info).text = "app version: ${pInfo.versionName}"
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
    }

    override fun setSelectedSize(size: MODELSize, callback: MainContracts.SelectSizeCallback) {
        callback.selectSize(size)
        sizeSelected = true
    }

    override fun showSelectSize(
        product: MODELProduct,
        color: MODELColor,
        callback: MainContracts.SelectSizeCallback
    ) {
        //findViewById<ImageView>(R.id.select_size_product_image) TODO: SET IMAGE
        findViewById<TextView>(R.id.select_size_seller_name).text = product.getSellerName()
        findViewById<TextView>(R.id.select_size_product_name).text = product.getProductName()
        findViewById<TextView>(R.id.select_size_product_id).text = product.getProductId()
        findViewById<TextView>(R.id.select_size_price).text = product.getPrice().toString()
        //TODO: DISCOUNTS

        Log.i(null, "Color has ${color.sizes.size} sizes.")

        val sizesAdapter = SizesAdapter(this, color.sizes, callback)
        findViewById<RecyclerView>(R.id.select_size_recycler_view).apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = sizesAdapter
        }

        findViewById<Button>(R.id.select_size_add_to_cart_button).setOnClickListener {
            if (sizeSelected) {
                callback.confirm()
                hideSelectSize()
            }
        }

        findViewById<ImageView>(R.id.background_shadow).setOnClickListener { hideSelectSize() }

        if (findViewById<BottomNavigationView>(R.id.navbar).translationY != 0f) {
            mainTransitionContainer?.setTransition(
                R.id.main_layout_navbar_header_hidden,
                R.id.main_layout_navbar_header_hidden_select_size
            )
        } else {
            mainTransitionContainer?.setTransition(
                R.id.main_layout_default,
                R.id.main_layout_default_select_size
            )
        }
        mainTransitionContainer?.transitionToEnd()

        selectSizeOpen = true
    }

    override fun hideSelectSize() {

        if (findViewById<BottomNavigationView>(R.id.navbar).translationY != 0f) {
            mainTransitionContainer?.setTransition(
                R.id.main_layout_navbar_header_hidden_select_size,
                R.id.main_layout_navbar_header_hidden
            )
        } else {
            mainTransitionContainer?.setTransition(
                R.id.main_layout_default_select_size,
                R.id.main_layout_default
            )
        }
        mainTransitionContainer?.transitionToEnd()

        sizeSelected = false
        selectSizeOpen = false
    }

    override fun setFilter(filter: MODELFilter?) {
        presenter?.filter = filter
    }

    override fun getFilter(): MODELFilter? {
        return presenter?.filter
    }

    override fun showProgressBar() {
        findViewById<ConstraintLayout>(R.id.main_progressbar_layout).visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        findViewById<ConstraintLayout>(R.id.main_progressbar_layout).visibility = View.INVISIBLE
    }

    override fun hideCross() {
        findViewById<ImageButton>(R.id.main_cross_button).apply {
            setOnClickListener {  }
            visibility = View.INVISIBLE
        }
    }

    override fun showCross() {
        findViewById<ImageButton>(R.id.main_cross_button).apply {
            setOnClickListener { findNavController(R.id.nav_host_fragment).popBackStack() }
            visibility = View.VISIBLE
        }
    }

    override fun showCrossOfFilter(filter: MODELFilter?) {
        findViewById<ImageButton>(R.id.main_cross_button).apply {
            setOnClickListener {
                findNavController(R.id.nav_host_fragment).popBackStack()
                presenter?.updateFilterTo(this.context, filter)
            }
            visibility = View.VISIBLE
        }
    }

    override fun setFilterTo(filter: MODELFilter?) {
        presenter?.updateFilterTo(this.baseContext, filter)
    }

    override fun unsetFilterButton() {
        try {
            (supportFragmentManager.findFragmentById(R.id.cabinCustomerDiscoverFragment)
                    as CabinCustomerDiscoverContracts.View).unsetFilterButton()
        } catch (exception: Exception) {
            (Logger as BaseContracts.Logger).info(
                this.baseContext,
                this::class.java.name,
                "Error while trying to unset filter button!",
                exception
            )
        }
    }

    override fun setupFilterButton() {
        try {
            (supportFragmentManager.findFragmentById(R.id.cabinCustomerDiscoverFragment)
                    as CabinCustomerDiscoverContracts.View).setFilterButton()
        } catch (exception: Exception) {
            (Logger as BaseContracts.Logger).warn(
                this.baseContext,
                this::class.java.name,
                "Error while trying to set filter button!",
                exception
            )
        }
    }

    override fun showDrawerButton() {
        findViewById<ImageButton>(R.id.main_drawer_button).apply {
            setOnClickListener {
                if (GlobalData.loggedIn)
                    drawerLayout.openDrawer(Gravity.LEFT)
                else
                    presenter?.moveToRegistration()
            }
            visibility = View.VISIBLE
        }
    }

    override fun hideDrawerButton() {
        findViewById<ImageButton>(R.id.main_drawer_button).apply {
            setOnClickListener {  }
            visibility = View.INVISIBLE
        }
    }
}