package ist.cabin.cabinCustomerHome

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerHomeContracts {

    interface View : BaseContracts.View {
        fun setupPage()
        fun showHome()
    }

    interface Presenter : BaseContracts.Presenter {
        fun seeHome()
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