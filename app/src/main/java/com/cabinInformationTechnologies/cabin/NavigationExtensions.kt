/*
 * Copyright 2019, Cabin Bilişim Teknolojileri A.Ş
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cabinInformationTechnologies.cabin

import android.content.Intent
import android.os.Build
import android.util.SparseArray
import androidx.core.util.forEach
import androidx.core.util.set
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * Manages the various graphs needed for a [BottomNavigationView].
 *
 * This sample is a workaround until the Navigation Component supports multiple back stacks.
 */
fun BottomNavigationView.setupWithNavController(
    navGraphIds: List<Int>,
    fragmentManager: FragmentManager,
    containerId: Int,
    intent: Intent
): LiveData<NavController> {

    // Map of tags
    val graphIdToTagMap = SparseArray<String>()
    // Result. Mutable live data with the selected controlled
    val selectedNavController = MutableLiveData<NavController>()


    var ordersFragmentGraphId = 0
    var favoritesFragmentGraphId = 0
    var homeFragmentGraphId = 2
    var cartFragmentGraphId = 0
    var discoverFragmentGraphId = 2

    // First create a NavHostFragment for each NavGraph ID
    navGraphIds.forEachIndexed { index, navGraphId ->
        val fragmentTag = getFragmentTag(index)

        // Find or create the Navigation host fragment
        val navHostFragment = obtainNavHostFragment(
            fragmentManager,
            fragmentTag,
            navGraphId,
            containerId
        )

        // Obtain its id
        val graphId = navHostFragment.navController.graph.id

        // Update livedata with the selected graph
        when (index) {
            0 -> ordersFragmentGraphId = graphId
            1 -> favoritesFragmentGraphId = graphId
            2 -> homeFragmentGraphId = graphId
            3 -> cartFragmentGraphId = graphId
            4 -> discoverFragmentGraphId = graphId
        }

        // Save to the map

        // Attach or detach nav host fragment depending on whether it's the selected item.

        // Save to the map
        graphIdToTagMap[graphId] = fragmentTag

        // Attach or detach nav host fragment depending on whether it's the selected item.
        if (this.selectedItemId == graphId) {
            // Update livedata with the selected graph
            selectedNavController.value = navHostFragment.navController
            attachNavHostFragment(fragmentManager, navHostFragment, index == 2)
        } else {
            detachNavHostFragment(fragmentManager, navHostFragment)
        }
    }

    // Now connect selecting an item with swapping Fragments
    var selectedItemTag = graphIdToTagMap[this.selectedItemId]
    val ordersFragmentTag = graphIdToTagMap[ordersFragmentGraphId]
    val favoritesFragmentTag = graphIdToTagMap[favoritesFragmentGraphId]
    val homeFragmentTag = graphIdToTagMap[homeFragmentGraphId]
    val cartFragmentTag = graphIdToTagMap[cartFragmentGraphId]
    val discoverFragmentTag = graphIdToTagMap[discoverFragmentGraphId]
    var isOnFirstFragment = selectedItemTag == homeFragmentTag


    // Clears backstack
    fragmentManager.popBackStack()

    // Moves to home page
    val startingItemTag = graphIdToTagMap[homeFragmentGraphId]
    if (selectedItemTag != startingItemTag) {
        // Pop everything
        fragmentManager.popBackStack()
        val selectedFragment = fragmentManager.findFragmentByTag(startingItemTag)
                as NavHostFragment

        if (ordersFragmentTag != startingItemTag) {
            fragmentManager.beginTransaction()
                .attach(selectedFragment)
                .setPrimaryNavigationFragment(selectedFragment)
                .apply {
                    // Detach all other Fragments
                    graphIdToTagMap.forEach { _, fragmentTagIter ->
                        if (fragmentTagIter != startingItemTag) {
                            detach(fragmentManager.findFragmentByTag(ordersFragmentTag)!!)
                        }
                    }
                }
                .setCustomAnimations(
                    R.anim.nav_default_enter_anim,
                    R.anim.nav_default_exit_anim,
                    R.anim.nav_default_pop_enter_anim,
                    R.anim.nav_default_pop_exit_anim)
                .setReorderingAllowed(true)
                .commit()
        }
        selectedItemTag = startingItemTag
        isOnFirstFragment = true
        selectedNavController.value = selectedFragment.navController
    }

    // Moves bottom nav indicator to home page
    this.selectedItemId = homeFragmentGraphId

    // When a navigation item is selected
    setOnNavigationItemSelectedListener { item ->
        // Don't do anything if the state is state has already been saved.
        if (fragmentManager.isStateSaved) {
            false
        } else {
            val newlySelectedItemTag = graphIdToTagMap[item.itemId]
            if (selectedItemTag != newlySelectedItemTag) {
                // Pop everything above the home fragment (the "fixed start destination")
                fragmentManager.popBackStack(homeFragmentTag,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE)
                val selectedFragment = fragmentManager.findFragmentByTag(newlySelectedItemTag)
                        as NavHostFragment

                // Exclude the home fragment tag because it's always in the back stack.
                if (homeFragmentTag != newlySelectedItemTag) {
                    // Commit a transaction that cleans the back stack and adds the home fragment
                    // to it, creating the fixed started destination.
                    fragmentManager.beginTransaction()
                        .attach(selectedFragment)
                        .setPrimaryNavigationFragment(selectedFragment)
                        .apply {
                            // Detach all other Fragments
                            graphIdToTagMap.forEach { _, fragmentTagIter ->
                                if (fragmentTagIter != newlySelectedItemTag) {
                                    detach(fragmentManager.findFragmentByTag(homeFragmentTag)!!)
                                }
                            }
                        }
                        .addToBackStack(homeFragmentTag)
                        .setCustomAnimations(
                            R.anim.nav_default_enter_anim,
                            R.anim.nav_default_exit_anim,
                            R.anim.nav_default_pop_enter_anim,
                            R.anim.nav_default_pop_exit_anim)
                        .setReorderingAllowed(true)
                        .commit()
                }
                selectedItemTag = newlySelectedItemTag
                isOnFirstFragment = selectedItemTag == homeFragmentTag
                selectedNavController.value = selectedFragment.navController

                // Sets menu icons
                if(newlySelectedItemTag == ordersFragmentTag) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        menu.getItem(0).icon = resources.getDrawable(R.drawable.orders_selected_icon, this.context.theme)
                        menu.getItem(1).icon = resources.getDrawable(R.drawable.favorites_icon, this.context.theme)
                        menu.getItem(2).icon = resources.getDrawable(R.drawable.homepage_icon, this.context.theme)
                        menu.getItem(3).icon = resources.getDrawable(R.drawable.cart_icon, this.context.theme)
                        menu.getItem(4).icon = resources.getDrawable(R.drawable.discover_icon, this.context.theme)
                    } else {
                        menu.getItem(0).icon = resources.getDrawable(R.drawable.orders_selected_icon)
                        menu.getItem(1).icon = resources.getDrawable(R.drawable.favorites_icon)
                        menu.getItem(2).icon = resources.getDrawable(R.drawable.homepage_icon)
                        menu.getItem(3).icon = resources.getDrawable(R.drawable.cart_icon)
                        menu.getItem(4).icon = resources.getDrawable(R.drawable.discover_icon)
                    }
                } else if(newlySelectedItemTag == favoritesFragmentTag) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        menu.getItem(0).icon = resources.getDrawable(R.drawable.orders_icon, this.context.theme)
                        menu.getItem(1).icon = resources.getDrawable(R.drawable.favorites_selected_icon, this.context.theme)
                        menu.getItem(2).icon = resources.getDrawable(R.drawable.homepage_icon, this.context.theme)
                        menu.getItem(3).icon = resources.getDrawable(R.drawable.cart_icon, this.context.theme)
                        menu.getItem(4).icon = resources.getDrawable(R.drawable.discover_icon, this.context.theme)
                    } else {
                        menu.getItem(0).icon = resources.getDrawable(R.drawable.orders_icon)
                        menu.getItem(1).icon = resources.getDrawable(R.drawable.favorites_selected_icon)
                        menu.getItem(2).icon = resources.getDrawable(R.drawable.homepage_icon)
                        menu.getItem(3).icon = resources.getDrawable(R.drawable.cart_icon)
                        menu.getItem(4).icon = resources.getDrawable(R.drawable.discover_icon)
                    }
                } else if(newlySelectedItemTag == homeFragmentTag) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        menu.getItem(0).icon = resources.getDrawable(R.drawable.orders_icon, this.context.theme)
                        menu.getItem(1).icon = resources.getDrawable(R.drawable.favorites_icon, this.context.theme)
                        menu.getItem(2).icon = resources.getDrawable(R.drawable.homepage_icon, this.context.theme)
                        menu.getItem(3).icon = resources.getDrawable(R.drawable.cart_icon, this.context.theme)
                        menu.getItem(4).icon = resources.getDrawable(R.drawable.discover_icon, this.context.theme)
                    } else {
                        menu.getItem(0).icon = resources.getDrawable(R.drawable.orders_icon)
                        menu.getItem(1).icon = resources.getDrawable(R.drawable.favorites_icon)
                        menu.getItem(2).icon = resources.getDrawable(R.drawable.homepage_icon)
                        menu.getItem(3).icon = resources.getDrawable(R.drawable.cart_icon)
                        menu.getItem(4).icon = resources.getDrawable(R.drawable.discover_icon)
                    }
                } else if(newlySelectedItemTag == cartFragmentTag) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        menu.getItem(0).icon = resources.getDrawable(R.drawable.orders_icon, this.context.theme)
                        menu.getItem(1).icon = resources.getDrawable(R.drawable.favorites_icon, this.context.theme)
                        menu.getItem(2).icon = resources.getDrawable(R.drawable.homepage_icon, this.context.theme)
                        menu.getItem(3).icon = resources.getDrawable(R.drawable.cart_selected_icon, this.context.theme)
                        menu.getItem(4).icon = resources.getDrawable(R.drawable.discover_icon, this.context.theme)
                    } else {
                        menu.getItem(0).icon = resources.getDrawable(R.drawable.orders_icon)
                        menu.getItem(1).icon = resources.getDrawable(R.drawable.favorites_icon)
                        menu.getItem(2).icon = resources.getDrawable(R.drawable.homepage_icon)
                        menu.getItem(3).icon = resources.getDrawable(R.drawable.cart_selected_icon)
                        menu.getItem(4).icon = resources.getDrawable(R.drawable.discover_icon)
                    }
                } else if(newlySelectedItemTag == discoverFragmentTag) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        menu.getItem(0).icon = resources.getDrawable(R.drawable.orders_icon, this.context.theme)
                        menu.getItem(1).icon = resources.getDrawable(R.drawable.favorites_icon, this.context.theme)
                        menu.getItem(2).icon = resources.getDrawable(R.drawable.homepage_icon, this.context.theme)
                        menu.getItem(3).icon = resources.getDrawable(R.drawable.cart_icon, this.context.theme)
                        menu.getItem(4).icon = resources.getDrawable(R.drawable.discover_selected_icon, this.context.theme)
                    } else {
                        menu.getItem(0).icon = resources.getDrawable(R.drawable.orders_icon)
                        menu.getItem(1).icon = resources.getDrawable(R.drawable.favorites_icon)
                        menu.getItem(2).icon = resources.getDrawable(R.drawable.homepage_icon)
                        menu.getItem(3).icon = resources.getDrawable(R.drawable.cart_icon)
                        menu.getItem(4).icon = resources.getDrawable(R.drawable.discover_selected_icon)
                    }
                }

                true
            } else {
                false
            }
        }
    }

    // Optional: on item reselected, pop back stack to the destination of the graph
    setupItemReselected(graphIdToTagMap, fragmentManager)

    // Handle deep link
    setupDeepLinks(navGraphIds, fragmentManager, containerId, intent)

    // Finally, ensure that we update our BottomNavigationView when the back stack changes
    fragmentManager.addOnBackStackChangedListener {
        if (!isOnFirstFragment && !fragmentManager.isOnBackStack(homeFragmentTag)) {
            this.selectedItemId = homeFragmentGraphId
        }

        // Reset the graph if the currentDestination is not valid (happens when the back
        // stack is popped after using the back button).
        selectedNavController.value?.let { controller ->
            if (controller.currentDestination == null) {
                controller.navigate(controller.graph.id)
            }
        }
    }
    return selectedNavController
}

private fun BottomNavigationView.setupDeepLinks(
    navGraphIds: List<Int>,
    fragmentManager: FragmentManager,
    containerId: Int,
    intent: Intent
) {
    navGraphIds.forEachIndexed { index, navGraphId ->
        val fragmentTag = getFragmentTag(index)

        // Find or create the Navigation host fragment
        val navHostFragment = obtainNavHostFragment(
            fragmentManager,
            fragmentTag,
            navGraphId,
            containerId
        )
        // Handle Intent
        if (navHostFragment.navController.handleDeepLink(intent)
            && selectedItemId != navHostFragment.navController.graph.id) {
            this.selectedItemId = navHostFragment.navController.graph.id
        }
    }
}

private fun BottomNavigationView.setupItemReselected(
    graphIdToTagMap: SparseArray<String>,
    fragmentManager: FragmentManager
) {
    setOnNavigationItemReselectedListener { item ->
        val newlySelectedItemTag = graphIdToTagMap[item.itemId]
        val selectedFragment = fragmentManager.findFragmentByTag(newlySelectedItemTag)
                as NavHostFragment
        val navController = selectedFragment.navController
        // Pop the back stack to the start destination of the current navController graph
        navController.popBackStack(
            navController.graph.startDestination, false
        )
    }
}

private fun detachNavHostFragment(
    fragmentManager: FragmentManager,
    navHostFragment: NavHostFragment
) {
    fragmentManager.beginTransaction()
        .detach(navHostFragment)
        .commitNow()
}

private fun attachNavHostFragment(
    fragmentManager: FragmentManager,
    navHostFragment: NavHostFragment,
    isPrimaryNavFragment: Boolean
) {
    fragmentManager.beginTransaction()
        .attach(navHostFragment)
        .apply {
            if (isPrimaryNavFragment) {
                setPrimaryNavigationFragment(navHostFragment)
            }
        }
        .commitNow()

}

private fun obtainNavHostFragment(
    fragmentManager: FragmentManager,
    fragmentTag: String,
    navGraphId: Int,
    containerId: Int
): NavHostFragment {
    // If the Nav Host fragment exists, return it
    val existingFragment = fragmentManager.findFragmentByTag(fragmentTag) as NavHostFragment?
    existingFragment?.let { return it }

    // Otherwise, create it and return it.
    val navHostFragment = NavHostFragment.create(navGraphId)
    fragmentManager.beginTransaction()
        .add(containerId, navHostFragment, fragmentTag)
        .commitNow()
    return navHostFragment
}

private fun FragmentManager.isOnBackStack(backStackName: String): Boolean {
    val backStackCount = backStackEntryCount
    for (index in 0 until backStackCount) {
        if (getBackStackEntryAt(index).name == backStackName) {
            return true
        }
    }
    return false
}

private fun getFragmentTag(index: Int) = "bottomNavigation#$index"
