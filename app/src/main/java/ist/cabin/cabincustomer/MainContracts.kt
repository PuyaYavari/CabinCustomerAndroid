package ist.cabin.cabincustomer

object MainContracts {
    interface View: ist.cabin.cabinCustomerBase.BaseContracts.View

    interface Presenter: ist.cabin.cabinCustomerBase.BaseContracts.Presenter {
        fun moveToProfileOptions()
        fun moveToMeasure()
        fun moveToExtraditions()
    }

    interface Interactor: ist.cabin.cabinCustomerBase.BaseContracts.Interactor

    interface InteractorOutput: ist.cabin.cabinCustomerBase.BaseContracts.InteractorOutput

    interface Router: ist.cabin.cabinCustomerBase.BaseContracts.Router {
        fun moveToProfileOptions()
        fun moveToMeasure()
        fun moveToExtraditions()
    }
}