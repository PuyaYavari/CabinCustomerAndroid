package ist.cabin.cabinCustomerExtraditions.fragments.extraditionsList

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerExtraditionsListContracts {

    interface View : BaseContracts.View {
        fun addExtraditionListener()
        fun setupNoExtraditionList()
        fun setupExtraditionsList()
        fun moveToExtraditionDetail()
        fun moveToExtraditionDetailDenied()
        fun moveToExtraditionDetailAccepted()
    }

    interface Presenter : BaseContracts.Presenter {
        fun addExtradition()
        fun setupPage()
        fun moveToExtraditionDetail()
        fun moveToExtraditionDetailDenied()
        fun moveToExtraditionDetailAccepted()
    }

    interface Interactor : BaseContracts.Interactor {
        fun getInitialData(): Unit?
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        //TODO
    }

    interface Router : BaseContracts.Router {
        fun moveToAddExtraditionPage()
        fun moveToExtraditionDetail()
        fun moveToExtraditionDetailDenied()
        fun moveToExtraditionDetailAccepted()
    }

    interface ExtraditionBox {
        fun getType(): Int
        fun getStatusID(): Int
    }

}