package com.cabinInformationTechnologies.cabin

import android.content.Context
import com.cabinInformationTechnologies.cabin.fragments.filter.CabinCustomerFilterContracts
import com.cabinInformationTechnologies.cabin.fragments.filterDetail.CabinCustomerFilterDetailContracts
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.*

object MainContracts {
    interface View: com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {
        fun hideNavbarFromDefault()
        fun hideNavbarFromHidden()
        fun showNavbar()
        fun hideHeaderNavbar()
        fun showHeaderNavbar()
        fun showNeedLogin()
        fun showNoInternet()
        fun unblockPage()
        fun showBackButton()
        fun hideBackButton()
        fun showClear(fragment: CabinCustomerFilterContracts.FilterFragment)
        fun showClear(fragment: CabinCustomerFilterDetailContracts.FilterDetailFragment)
        fun hideClear()
        fun layoutBackToDefault()
        fun logout()
        fun setHeader(header: String, headerExtras: String?)
        fun lockDrawer()
        fun unlockDrawer()
        fun hideSelectSize()
        fun showSelectSize(product: MODELProduct, color: MODELColor, callback: SelectSizeCallback)
        fun setSelectedSize(size: MODELSize, callback: SelectSizeCallback)
        fun setFilter(filter: MODELFilter?)
        fun getFilter(): MODELFilter?
        fun showProgressBar()
        fun hideProgressBar()
    }

    interface Presenter: com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        var filter: MODELFilter?

        fun moveToProfileOptions()
        fun moveToMeasure()
        fun moveToExtraditions()
        fun requestLogout(context: Context)
        fun moveToRegistration()
        fun clearFilter(context: Context)
    }

    interface Interactor: com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor {
        fun logout(context: Context)
        fun clearFilter(context: Context)
    }

    interface InteractorOutput: com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput {
        fun logout()
        fun unableToLogout(message: String?)
    }

    interface Router: com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Router {
        fun moveToProfileOptions()
        fun moveToMeasure()
        fun moveToExtraditions()
        fun moveToRegistration()
    }

    interface SelectSizeCallback {
        fun selectSize(size: MODELSize)
        fun confirm()
    }

    interface NoInternetCallback {
        fun retry()
    }

    interface SetFilterCallback {
        fun setCategories(categories: MutableList<MODELFilterCategory>?)
        fun setSexes(sexes: MutableList<MODELFilterSex>?)
        fun setSellers(sellers: MutableList<MODELFilterSeller>?)
        fun setSizes(sizes: MutableList<MODELFilterSizeGroup>?)
        fun setColors(colors: MutableList<MODELFilterColor>?)
        fun setPrices(prices: MutableList<MODELFilterPrice>?)
    }
}