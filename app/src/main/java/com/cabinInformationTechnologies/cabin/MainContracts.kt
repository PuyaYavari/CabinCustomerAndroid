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
        fun hideCross()
        fun showCross()
        fun showCrossOfFilter(filter: MODELFilter?)
        fun setFilterTo(filter: MODELFilter?)
        fun unsetFilterButton()
        fun setupFilterButton()
        fun showDrawerButton()
        fun hideDrawerButton()
    }

    interface Presenter: com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        var filter: MODELFilter?
        var cart: MODELCart

        fun moveToProfileOptions()
        fun moveToMeasure()
        fun moveToExtraditions()
        fun requestLogout(context: Context)
        fun moveToRegistration()
        fun clearFilter(context: Context)
        fun updateFilterTo(context: Context, filter: MODELFilter?)
        fun updateCart(context: Context)
    }

    interface Interactor: com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor {
        fun logout(context: Context)
        fun clearFilter(context: Context)
        fun updateFilterTo(context: Context, filter: MODELFilter?)
        fun getCart(context: Context)
    }

    interface InteractorOutput: com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput {
        var cart: MODELCart

        fun logout()
        fun unableToLogout(message: String?)
        fun refreshFilter(filter: MODELFilter)
        fun updateFilterFailedFeedback(context: Context, message: String?, filter: MODELFilter?)
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
}