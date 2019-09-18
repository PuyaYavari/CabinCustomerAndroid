package ist.cabin.cabincustomer

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import ist.cabin.cabinCustomerBase.BaseActivity
import kotlinx.android.synthetic.main.cabin_customer_main.*


class MainActivity : BaseActivity(),
    MainContracts.View {
    private var currentNavController: LiveData<NavController>? = null

    var presenter: MainContracts.Presenter? = MainPresenter(this)

    private lateinit var t: ActionBarDrawerToggle
    private lateinit var mainTransitionContainer: MotionLayout

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cabin_customer_main)
        presenter?.onCreate(intent.extras)

        overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.slide_out_to_top)

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
                R.id.sidenav_help -> Toast.makeText(this@MainActivity, "Help", Toast.LENGTH_SHORT).show()
                R.id.sidenav_exit -> Toast.makeText(this@MainActivity, "Exit", Toast.LENGTH_SHORT).show()
                else -> true
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }

        mainTransitionContainer = findViewById(R.id.main_layout)
    }

    override fun onBackPressed() {
        if(drawer_layout.isDrawerOpen(GravityCompat.START)) {
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

    private fun setupBottomNavigationBar() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navbar)

        val navGraphIds = listOf(R.navigation.customer_orders, R.navigation.customer_favorites,
            R.navigation.customer_home, R.navigation.customer_cart, R.navigation.customer_discover)

        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_fragment,
            intent = intent
        )



        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    override fun showNavbar() {
        if (findViewById<BottomNavigationView>(R.id.navbar).translationY != 0f) {
            mainTransitionContainer.setTransition(
                R.id.main_layout_navbar_hidden,
                R.id.main_layout_default
            )
            mainTransitionContainer.transitionToEnd()
        }
    }

    override fun hideNavbar() {
        if (findViewById<BottomNavigationView>(R.id.navbar).translationY == 0f) {
            mainTransitionContainer.setTransition(
                R.id.main_layout_default,
                R.id.main_layout_navbar_hidden
            )
            mainTransitionContainer.transitionToEnd()
        }
    }
}