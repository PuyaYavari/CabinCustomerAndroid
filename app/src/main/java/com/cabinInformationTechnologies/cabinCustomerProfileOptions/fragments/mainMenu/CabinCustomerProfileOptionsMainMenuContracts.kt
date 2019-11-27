package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.mainMenu

import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts

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