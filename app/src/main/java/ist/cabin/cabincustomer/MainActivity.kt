package ist.cabin.cabincustomer

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import ist.cabin.cabinCustomerBase.BaseActivity
import ist.cabin.cabinCustomerBase.GlobalData
import ist.cabin.cabinCustomerBase.models.local.MODELUser
import kotlinx.android.synthetic.main.cabin_customer_main.*


class MainActivity : BaseActivity(),
    MainContracts.View {
    private var currentNavController: LiveData<NavController>? = null

    var presenter: MainContracts.Presenter? = MainPresenter(this)

    private lateinit var t: ActionBarDrawerToggle
    private var mainTransitionContainer: MotionLayout? = null

    private var needLoginVisible = false

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActiveUser()
        setContentView(R.layout.cabin_customer_main)
        presenter?.onCreate(intent.extras)

        overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.slide_out_to_top)

        mainTransitionContainer = findViewById(R.id.main_layout)
        layoutBackToDefault()

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        } // Else, need to wait for onRestoreInstanceState

        val dl: DrawerLayout = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        t = ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close)

        dl.addDrawerListener(t)
        t.syncState()

        val nv: NavigationView = findViewById<View>(R.id.homepage_sidenav) as NavigationView
        nv.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.sidenav_profile_options -> presenter?.moveToProfileOptions()
                R.id.sidenav_body_measure -> presenter?.moveToMeasure()
                R.id.sidenav_extradition -> presenter?.moveToExtraditions()
                R.id.sidenav_help -> Toast.makeText(
                    this@MainActivity,
                    "Help",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.sidenav_exit -> presenter?.requestLogout(this.applicationContext)
                else -> true
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }

        findViewById<Button>(R.id.login_button).setOnClickListener { presenter?.moveToRegisteration() }//TODO: REMOVE AND MAKE A PROPER BUTTON FOR THIS

        findViewById<ImageButton>(R.id.main_back_button).setOnClickListener { onBackPressed() }

        if (GlobalData.loggedIn)
            hideNeedLogin()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
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
        if (GlobalData.loggedIn)
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


        var user: MODELUser? = MODELUser()
        if (session != null && userId != 0)
            user?.setData(userId, session, null, null)
        else
            user = null

        GlobalData.activeUser = user
        GlobalData.userEmail = sharedPref.getString("userEmail", "")

        if (GlobalData.loggedIn)
            hideNeedLogin()
    }

    private fun setupBottomNavigationBar() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navbar)

        val navGraphIds = listOf(
            R.navigation.customer_orders, R.navigation.customer_favorites,
            R.navigation.customer_home, R.navigation.customer_cart, R.navigation.customer_discover
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
        if (mainTransitionContainer != null) {
            mainTransitionContainer?.setTransition(
                R.id.main_layout_default,
                R.id.main_layout_not_logged_in
            )
            mainTransitionContainer?.transitionToEnd()
            needLoginVisible = true
        }
    }

    override fun hideNeedLogin() {
        if (mainTransitionContainer != null &&
                findViewById<ConstraintLayout>(R.id.not_logged_in_layout).visibility == View.VISIBLE) {
            mainTransitionContainer?.setTransition(
                R.id.main_layout_not_logged_in,
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

    override fun hideNavbar() {
        if (mainTransitionContainer != null &&
            findViewById<BottomNavigationView>(R.id.navbar).translationY == 0f
        ) {
            mainTransitionContainer?.setTransition(
                R.id.main_layout_default,
                R.id.main_layout_navbar_hidden
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

    override fun unlockDrawer() {
        findViewById<DrawerLayout>(R.id.drawer_layout).setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }
}