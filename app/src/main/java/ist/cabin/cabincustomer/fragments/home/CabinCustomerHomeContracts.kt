package ist.cabin.cabincustomer.fragments.home

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerHomeContracts {

    interface View : BaseContracts.View

    interface Presenter : BaseContracts.Presenter {
        fun moveToRegistration()
    }

    interface Interactor : BaseContracts.Interactor

    interface InteractorOutput : BaseContracts.InteractorOutput

    interface Router : BaseContracts.Router {
        fun moveToRegistration()
    }

}