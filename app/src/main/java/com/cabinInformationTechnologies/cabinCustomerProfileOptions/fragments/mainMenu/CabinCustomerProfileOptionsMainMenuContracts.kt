package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.mainMenu

object CabinCustomerProfileOptionsMainMenuContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View

    interface Presenter : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        fun moveToPersonalDataPage()
        fun moveToAddressOptionsPage()
        fun moveToChangePasswordPage()
        fun moveToNotificationChoicesPage()
    }

    interface Interactor : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor

    interface InteractorOutput : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput

    interface Router : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Router {
        fun moveToPersonalDataPage()
        fun moveToAddressOptionsPage()
        fun moveToChangePasswordPage()
        fun moveToNotificationChoicesPage()
    }

}