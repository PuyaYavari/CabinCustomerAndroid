package com.cabinInformationTechnologies.cabinCustomerRegistration

import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELUser

object CabinCustomerRegistrationContracts {

    interface View : BaseContracts.View {
        fun setActiveUser(user: MODELUser?)
    }

    interface Presenter : BaseContracts.Presenter {
        //TODO
    }

    interface Interactor : BaseContracts.Interactor {
        //TODO
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        //TODO
    }

    interface Router : BaseContracts.Router {
        //TODO
    }

}