package ist.cabin.cabinCustomerProfileOptions.fragments.mainMenu

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerProfileOptionsMainMenuContracts {

    interface View : BaseContracts.View

    interface Presenter : BaseContracts.Presenter {
        fun moveToPersonalDataPage()
        fun moveToAddressOptionsPage()
        fun moveToChangePasswordPage()
        fun moveToNotificationChoicesPage()
    }

    interface Interactor : BaseContracts.Interactor

    interface InteractorOutput : BaseContracts.InteractorOutput

    interface Router : BaseContracts.Router {
        fun moveToPersonalDataPage()
        fun moveToAddressOptionsPage()
        fun moveToChangePasswordPage()
        fun moveToNotificationChoicesPage()
    }

}