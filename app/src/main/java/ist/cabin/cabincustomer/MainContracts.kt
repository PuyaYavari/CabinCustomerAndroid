package ist.cabin.cabincustomer

import android.content.Context

object MainContracts {
    interface View: ist.cabin.cabinCustomerBase.BaseContracts.View {
        fun hideNavbar()
        fun showNavbar()
        fun showNeedLogin()
        fun hideNeedLogin()
        fun showBackButton()
        fun hideBackButton()
        fun layoutBackToDefault()
        fun logout()
        fun setHeader(header: String, headerExtras: String?)
        fun lockDrawer()
        fun unlockDrawer()
    }

    interface Presenter: ist.cabin.cabinCustomerBase.BaseContracts.Presenter {
        fun moveToProfileOptions()
        fun moveToMeasure()
        fun moveToExtraditions()
        fun requestLogout(context: Context)
        fun moveToRegisteration()
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
        fun moveToRegisteration()
    }
}