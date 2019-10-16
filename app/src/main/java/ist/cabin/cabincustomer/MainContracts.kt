package ist.cabin.cabincustomer

import android.content.Context
import android.os.Parcelable
import ist.cabin.cabinCustomerBase.models.local.MODELColor
import ist.cabin.cabinCustomerBase.models.local.MODELProduct
import ist.cabin.cabinCustomerBase.models.local.MODELSize

object MainContracts {
    interface View: ist.cabin.cabinCustomerBase.BaseContracts.View {
        fun hideNavbarFromDefault()
        fun hideNavbarFromHidden()
        fun showNavbar()
        fun hideHeaderNavbar()
        fun showHeaderNavbar()
        fun showNeedLogin()
        fun hideNeedLogin()
        fun showBackButton()
        fun hideBackButton()
        fun layoutBackToDefault()
        fun logout()
        fun setHeader(header: String, headerExtras: String?)
        fun lockDrawer()
        fun unlockDrawer()
        fun hideSelectSize()
        fun showSelectSize(product: MODELProduct, color: MODELColor, callback: SelectSizeCallback)
        fun setSelectedSize(size: MODELSize, callback: SelectSizeCallback)
    }

    interface Presenter: ist.cabin.cabinCustomerBase.BaseContracts.Presenter {
        fun moveToProfileOptions()
        fun moveToMeasure()
        fun moveToExtraditions()
        fun requestLogout(context: Context)
        fun moveToRegistration()
    }

    interface Interactor: ist.cabin.cabinCustomerBase.BaseContracts.Interactor {
        fun logout(context: Context)
    }

    interface InteractorOutput: ist.cabin.cabinCustomerBase.BaseContracts.InteractorOutput {
        fun logout()
    }

    interface Router: ist.cabin.cabinCustomerBase.BaseContracts.Router {
        fun moveToProfileOptions()
        fun moveToMeasure()
        fun moveToExtraditions()
        fun moveToRegistration()
    }

    interface SelectSizeCallback {
        fun selectSize(size: MODELSize)
        fun confirm()
    }

    interface FilterDetailGenerator : Parcelable {
        fun getLayoutManager()
        fun getDataset()
    }
}