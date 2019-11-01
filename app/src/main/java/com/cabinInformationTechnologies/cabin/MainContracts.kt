package com.cabinInformationTechnologies.cabin

import android.content.Context
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
        fun layoutBackToDefault()
        fun logout()
        fun setHeader(header: String, headerExtras: String?)
        fun lockDrawer()
        fun unlockDrawer()
        fun hideSelectSize()
        fun showSelectSize(product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct, color: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor, callback: com.cabinInformationTechnologies.cabin.MainContracts.SelectSizeCallback)
        fun setSelectedSize(size: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize, callback: com.cabinInformationTechnologies.cabin.MainContracts.SelectSizeCallback)
        fun setFilter(filter: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilter?)
        fun getFilter(): com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilter?
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