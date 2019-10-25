package com.cabinInformationTechnologies.cabin

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.cabin_customer_main.*


class MainActivity : com.cabinInformationTechnologies.cabinCustomerBase.BaseActivity(),
    com.cabinInformationTechnologies.cabin.MainContracts.View {
    private var currentNavController: LiveData<NavController>? = null

    var presenter: com.cabinInformationTechnologies.cabin.MainContracts.Presenter? =
        com.cabinInformationTechnologies.cabin.MainPresenter(this)

    private var mainTransitionContainer: MotionLayout? = null

    private var needLoginVisible = false

    private var sizeSelected = false

    private var selectSizeOpen = false

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActiveUser()
        setContentView(com.cabinInformationTechnologies.cabin.R.layout.cabin_customer_main)
        presenter?.onCreate(intent.extras)

        overridePendingTransition(
            com.cabinInformationTechnologies.cabin.R.anim.slide_in_from_bottom,
            com.cabinInformationTechnologies.cabin.R.anim.slide_out_to_top
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            findViewById<DrawerLayout>(com.cabinInformationTechnologies.cabin.R.id.drawer_layout).setScrimColor(
                resources.getColor(android.R.color.transparent, theme)
            )
        } else {
            findViewById<DrawerLayout>(com.cabinInformationTechnologies.cabin.R.id.drawer_layout).setScrimColor(
                resources.getColor(android.R.color.transparent)
            )
        }

        mainTransitionContainer = findViewById(com.cabinInformationTechnologies.cabin.R.id.main_layout)
        layoutBackToDefault()

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        } // Else, need to wait for onRestoreInstanceState

        findViewById<LinearLayout>(com.cabinInformationTechnologies.cabin.R.id.sidebar_profile_options_layout).setOnClickListener { presenter?.moveToProfileOptions() }
        findViewById<LinearLayout>(com.cabinInformationTechnologies.cabin.R.id.sidebar_measure_options_layout).setOnClickListener { presenter?.moveToMeasure() }
        findViewById<LinearLayout>(com.cabinInformationTechnologies.cabin.R.id.sidebar_extraditions_layout).setOnClickListener { presenter?.moveToExtraditions() }
        findViewById<LinearLayout>(com.cabinInformationTechnologies.cabin.R.id.sidebar_help_layout).setOnClickListener {
            Toast.makeText(
                this@MainActivity,
                "Help",
                Toast.LENGTH_SHORT
            ).show()
        }
        findViewById<LinearLayout>(com.cabinInformationTechnologies.cabin.R.id.sidebar_exit_layout).setOnClickListener { presenter?.requestLogout(this.applicationContext) }

        findViewById<Button>(com.cabinInformationTechnologies.cabin.R.id.login_button).setOnClickListener { presenter?.moveToRegistration() }//TODO: REMOVE AND MAKE A PROPER BUTTON FOR THIS

        findViewById<ImageButton>(com.cabinInformationTechnologies.cabin.R.id.main_back_button).setOnClickListener { onBackPressed() }

        if (com.cabinInformationTechnologies.cabinCustomerBase.GlobalData.loggedIn)
            hideNeedLogin()
    }

    override fun onBackPressed() {
        when {
            drawer_layout.isDrawerOpen(GravityCompat.START) -> drawer_layout.closeDrawer(GravityCompat.START)
            selectSizeOpen -> hideSelectSize()
            else -> super.onBackPressed()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar()
    }

    override fun onResume() {
        super.onResume()
        if (com.cabinInformationTechnologies.cabinCustomerBase.GlobalData.loggedIn)
            hideNeedLogin()
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


        var user: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELUser? =
            com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELUser()
        if (session != null && userId != 0)
            user?.setData(userId, session, null, null)
        else
            user = null

        com.cabinInformationTechnologies.cabinCustomerBase.GlobalData.activeUser = user
        com.cabinInformationTechnologies.cabinCustomerBase.GlobalData.userEmail = sharedPref.getString("userEmail", "")

        if (com.cabinInformationTechnologies.cabinCustomerBase.GlobalData.loggedIn)
            hideNeedLogin()
    }

    private fun setupBottomNavigationBar() {
        val bottomNavigationView = findViewById<BottomNavigationView>(com.cabinInformationTechnologies.cabin.R.id.navbar)

        val navGraphIds = listOf(
            com.cabinInformationTechnologies.cabin.R.navigation.customer_orders,
            com.cabinInformationTechnologies.cabin.R.navigation.customer_favorites,
            com.cabinInformationTechnologies.cabin.R.navigation.customer_home,
            com.cabinInformationTechnologies.cabin.R.navigation.customer_cart,
            com.cabinInformationTechnologies.cabin.R.navigation.customer_discover
        )

        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = com.cabinInformationTechnologies.cabin.R.id.nav_host_fragment,
            intent = intent
        )

        currentNavController = controller
    }

    private fun setHeaderText(text: String?) {
        findViewById<TextView>(com.cabinInformationTechnologies.cabin.R.id.main_header_label).text = text
    }

    override fun showNeedLogin() {
        if (mainTransitionContainer != null &&
                findViewById<ConstraintLayout>(com.cabinInformationTechnologies.cabin.R.id.not_logged_in_layout).visibility != View.VISIBLE) {
            mainTransitionContainer?.setTransition(
                com.cabinInformationTechnologies.cabin.R.id.main_layout_default,
                com.cabinInformationTechnologies.cabin.R.id.main_layout_not_logged_in
            )
            mainTransitionContainer?.transitionToEnd()
            needLoginVisible = true
        }
    }

    override fun hideNeedLogin() {
        if (mainTransitionContainer != null &&
                findViewById<ConstraintLayout>(com.cabinInformationTechnologies.cabin.R.id.not_logged_in_layout).visibility == View.VISIBLE) {
            mainTransitionContainer?.setTransition(
                com.cabinInformationTechnologies.cabin.R.id.main_layout_not_logged_in,
                com.cabinInformationTechnologies.cabin.R.id.main_layout_default
            )
            mainTransitionContainer?.transitionToEnd()
            needLoginVisible = false
        }
    }

    override fun layoutBackToDefault() {
        if (mainTransitionContainer != null) {
            mainTransitionContainer?.setTransition(
                com.cabinInformationTechnologies.cabin.R.id.main_layout_default,
                com.cabinInformationTechnologies.cabin.R.id.main_layout_default
            )
            mainTransitionContainer?.transitionToEnd()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    override fun showNavbar() {
        val hiddenNavbarTranslation = resources.getDimension(com.cabinInformationTechnologies.cabin.R.dimen.navbarHeight)
        if (mainTransitionContainer != null &&
            findViewById<BottomNavigationView>(com.cabinInformationTechnologies.cabin.R.id.navbar).translationY == hiddenNavbarTranslation
        ) {
            mainTransitionContainer?.setTransition(
                com.cabinInformationTechnologies.cabin.R.id.main_layout_navbar_hidden,
                com.cabinInformationTechnologies.cabin.R.id.main_layout_default
            )
            mainTransitionContainer?.transitionToEnd()
        }
    }

    override fun hideNavbarFromDefault() {
        val hiddenNavbarTranslation = resources.getDimension(com.cabinInformationTechnologies.cabin.R.dimen.navbarHeight)
        if (mainTransitionContainer != null) {
            mainTransitionContainer?.setTransition(
                com.cabinInformationTechnologies.cabin.R.id.main_layout_default,
                com.cabinInformationTechnologies.cabin.R.id.main_layout_navbar_hidden
            )
            mainTransitionContainer?.transitionToEnd()
        }
    }

    override fun hideNavbarFromHidden() {
        val hiddenNavbarTranslation = resources.getDimension(com.cabinInformationTechnologies.cabin.R.dimen.navbarHeight)
        if (mainTransitionContainer != null &&
            findViewById<BottomNavigationView>(com.cabinInformationTechnologies.cabin.R.id.navbar).translationY == hiddenNavbarTranslation
        ) {
            mainTransitionContainer?.setTransition(
                com.cabinInformationTechnologies.cabin.R.id.main_layout_navbar_header_hidden,
                com.cabinInformationTechnologies.cabin.R.id.main_layout_navbar_hidden
            )
            mainTransitionContainer?.transitionToEnd()
        }
    }

    override fun showHeaderNavbar() {
        val hiddenNavbarTranslation = resources.getDimension(com.cabinInformationTechnologies.cabin.R.dimen.navbarHeight)
        if (mainTransitionContainer != null &&
            findViewById<BottomNavigationView>(com.cabinInformationTechnologies.cabin.R.id.navbar).translationY == hiddenNavbarTranslation
        ) {
            if (findViewById<ConstraintLayout>(com.cabinInformationTechnologies.cabin.R.id.main_header).translationY != 0f) {
                mainTransitionContainer?.setTransition(
                    com.cabinInformationTechnologies.cabin.R.id.main_layout_navbar_header_hidden,
                    com.cabinInformationTechnologies.cabin.R.id.main_layout_default
                )
            } else {
                mainTransitionContainer?.setTransition(
                    com.cabinInformationTechnologies.cabin.R.id.main_layout_navbar_hidden,
                    com.cabinInformationTechnologies.cabin.R.id.main_layout_default
                )
            }
            mainTransitionContainer?.transitionToEnd()
        }
    }

    override fun hideHeaderNavbar() {
        if (mainTransitionContainer != null &&
            findViewById<BottomNavigationView>(com.cabinInformationTechnologies.cabin.R.id.navbar).translationY == 0f
        ) {
            mainTransitionContainer?.setTransition(
                com.cabinInformationTechnologies.cabin.R.id.main_layout_default,
                com.cabinInformationTechnologies.cabin.R.id.main_layout_navbar_header_hidden
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
        com.cabinInformationTechnologies.cabinCustomerBase.GlobalData.activeUser = null
    }

    override fun setHeader(header: String, headerExtras: String?) {
        if (headerExtras == null)
            setHeaderText(header)
        else
            setHeaderText("$header($headerExtras)")
    }

    override fun showBackButton() {
        findViewById<ImageButton>(com.cabinInformationTechnologies.cabin.R.id.main_back_button).apply {
            visibility = View.VISIBLE
            isEnabled = true
            isClickable = true
        }
    }

    override fun hideBackButton() {
        findViewById<ImageButton>(com.cabinInformationTechnologies.cabin.R.id.main_back_button).apply {
            visibility = View.INVISIBLE
            isEnabled = false
            isClickable = false
        }
    }

    override fun lockDrawer() {
        findViewById<DrawerLayout>(com.cabinInformationTechnologies.cabin.R.id.drawer_layout).setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    override fun unlockDrawer() {
        findViewById<DrawerLayout>(com.cabinInformationTechnologies.cabin.R.id.drawer_layout).setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    override fun setSelectedSize(size: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize, callback: com.cabinInformationTechnologies.cabin.MainContracts.SelectSizeCallback) {
        callback.selectSize(size)
        sizeSelected = true
    }

    override fun showSelectSize(
        product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct,
        color: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor,
        callback: com.cabinInformationTechnologies.cabin.MainContracts.SelectSizeCallback
    ) {
        //findViewById<ImageView>(R.id.select_size_product_image) TODO: SET IMAGE
        findViewById<TextView>(com.cabinInformationTechnologies.cabin.R.id.select_size_seller_name).text = product.getSellerName()
        findViewById<TextView>(com.cabinInformationTechnologies.cabin.R.id.select_size_product_name).text = product.getProductName()
        findViewById<TextView>(com.cabinInformationTechnologies.cabin.R.id.select_size_product_id).text = product.getProductId()
        findViewById<TextView>(com.cabinInformationTechnologies.cabin.R.id.select_size_price).text = product.getPrice().toString()
        //TODO: DISCOUNTS

        Log.i(null, "Color has ${color.sizes.size} sizes.")

        val sizesAdapter = com.cabinInformationTechnologies.cabin.SizesAdapter(this, color.sizes, callback)
        findViewById<RecyclerView>(com.cabinInformationTechnologies.cabin.R.id.select_size_recycler_view).apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = sizesAdapter
        }

        findViewById<Button>(com.cabinInformationTechnologies.cabin.R.id.select_size_add_to_cart_button).setOnClickListener {
            if (sizeSelected) {
                callback.confirm()
                hideSelectSize()
            }
        }

        findViewById<ImageView>(com.cabinInformationTechnologies.cabin.R.id.background_shadow).setOnClickListener { hideSelectSize() }

        if (findViewById<BottomNavigationView>(com.cabinInformationTechnologies.cabin.R.id.navbar).translationY != 0f) {
            mainTransitionContainer?.setTransition(
                com.cabinInformationTechnologies.cabin.R.id.main_layout_navbar_header_hidden,
                com.cabinInformationTechnologies.cabin.R.id.main_layout_navbar_header_hidden_select_size
            )
        } else {
            mainTransitionContainer?.setTransition(
                com.cabinInformationTechnologies.cabin.R.id.main_layout_default,
                com.cabinInformationTechnologies.cabin.R.id.main_layout_default_select_size
            )
        }
        mainTransitionContainer?.transitionToEnd()

        selectSizeOpen = true
    }

    override fun hideSelectSize() {

        if (findViewById<BottomNavigationView>(com.cabinInformationTechnologies.cabin.R.id.navbar).translationY != 0f) {
            mainTransitionContainer?.setTransition(
                com.cabinInformationTechnologies.cabin.R.id.main_layout_navbar_header_hidden_select_size,
                com.cabinInformationTechnologies.cabin.R.id.main_layout_navbar_header_hidden
            )
        } else {
            mainTransitionContainer?.setTransition(
                com.cabinInformationTechnologies.cabin.R.id.main_layout_default_select_size,
                com.cabinInformationTechnologies.cabin.R.id.main_layout_default
            )
        }
        mainTransitionContainer?.transitionToEnd()

        sizeSelected = false
        selectSizeOpen = false
    }

    override fun setFilter(filter: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilter?) {
        presenter?.filter = filter
    }

    override fun getFilter(): com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilter? {
        return presenter?.filter
    }

    override fun showProgressBar() {
        findViewById<ConstraintLayout>(com.cabinInformationTechnologies.cabin.R.id.main_progressbar_layout).visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        findViewById<ConstraintLayout>(com.cabinInformationTechnologies.cabin.R.id.main_progressbar_layout).visibility = View.INVISIBLE
    }
}