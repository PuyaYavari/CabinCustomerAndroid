package ist.cabin.cabinCustomerProfileOptions.fragments.mainMenu

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerProfileOptionsMainMenuContracts {

    interface View : BaseContracts.View {
        //TODO
    }

    interface Presenter : BaseContracts.Presenter {
        fun moveToPersonalDataPage()
        fun moveToAddressOptionsPage()
        fun moveToChangePasswordPage()
        fun moveToNotificationChoicesPage()
    }

    interface Interactor : BaseContracts.Interactor {
        //TODO
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        //TODO
    }

    interface Router : BaseContracts.Router {
        fun moveToPersonalDataPage()
        fun moveToAddressOptionsPage()
        fun moveToChangePasswordPage()
        fun moveToNotificationChoicesPage()
    }

}