package ist.cabin.cabinCustomerHome

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import ist.cabin.cabinCustomerHome.fragments.basket.CabinCustomerHomeBasketFragment
import ist.cabin.cabinCustomerHome.fragments.favorite.CabinCustomerHomeFavoriteFragment
import ist.cabin.cabinCustomerHome.fragments.main.CabinCustomerHomeMainFragment
import ist.cabin.cabinCustomerHome.fragments.profile.CabinCustomerHomeProfileFragment

class CabinCustomerHomeScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    private val NUM_PAGES = 5

    override fun getCount(): Int = NUM_PAGES

    override fun getItem(position: Int): Fragment {
        if (position == 0)
            return CabinCustomerHomeFavoriteFragment()
        else if (position == 1)
            return CabinCustomerHomeFavoriteFragment()
        else if (position == 2)
            return CabinCustomerHomeMainFragment()
        else if (position == 3)
            return CabinCustomerHomeBasketFragment()
        else
            return CabinCustomerHomeProfileFragment()
    }
    }