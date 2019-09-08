package ist.cabin.cabinCustomerMeasure.fragments.selectMethod

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerMeasureSelectMethodContracts {

    interface View : BaseContracts.View {
        //TODO
    }

    interface Presenter : BaseContracts.Presenter {
        fun moveToAutoMeasureTurorial()
    }

    interface Interactor : BaseContracts.Interactor {
        //TODO
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        //TODO
    }

    interface Router : BaseContracts.Router {
        fun moveToAutoMeasureTurorial()
    }

}