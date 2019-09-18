package ist.cabin.cabinCustomerManualMeasureInput_fragment

import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerBase.models.backend.JSONMeasure

object CabinCustomerManualMeasureInputContracts {

    interface View : BaseContracts.View {
        fun addToMeasuresList(inputBox: android.view.View)
        fun createTextInputBox(id: Int, name: String): android.view.View
    }

    interface Presenter : BaseContracts.Presenter {
        fun getMeasures(): List<JSONMeasure>?
        fun setupMeasuresList(JSONMeasures: List<JSONMeasure>?)
        fun calculateTextSize(text: String, defaultSize: Float): Float
        fun confirmPage()
        fun addMeasureToMap(id: Int, value: Float)
    }

    interface Interactor : BaseContracts.Interactor {
        fun getMeasures(): List<JSONMeasure>?
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        //TODO
    }

    interface Router : BaseContracts.Router {
        //TODO
    }

}