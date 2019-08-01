package ist.cabin.cabinCustomerManualMeasureInput

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerManualMeasureInputContracts {

    interface View : BaseContracts.View {
        fun setupPage()
        fun addToMeasuresList(inputBox: android.view.View)
        fun createTextInputBox(name: String): android.view.View
    }

    interface Presenter : BaseContracts.Presenter {
        fun getMeasures(): MutableList<String>
        fun setupMeasuresList(measures: MutableList<String>)
        fun calculateTextSize(text: String, defaultSize: Float): Float
    }

    interface Interactor : BaseContracts.Interactor {
        fun getMeasures(): MutableList<String>
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        //TODO
    }

    interface Router : BaseContracts.Router {
        //TODO
    }

}