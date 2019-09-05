package ist.cabin.cabinCustomerExtraditions.fragments.addExtraditionCurrentItemsList

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerAddExtraditionCurrentItemsListContracts {

    interface View : BaseContracts.View {

    }

    interface Presenter : BaseContracts.Presenter {
        fun moveForward()
    }

    interface Interactor : BaseContracts.Interactor {
        fun getProductsList()
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        //TODO
    }

    interface Router : BaseContracts.Router {
        fun moveForward()
    }
}