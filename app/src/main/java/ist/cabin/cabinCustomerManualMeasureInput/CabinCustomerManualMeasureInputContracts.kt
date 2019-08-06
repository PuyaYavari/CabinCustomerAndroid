package ist.cabin.cabinCustomerManualMeasureInput

import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabincustomer.models.Measure

object CabinCustomerManualMeasureInputContracts {

    interface View : BaseContracts.View {
        fun setupPage()
        fun addToMeasuresList(inputBox: android.view.View)
        fun createTextInputBox(id: Int, name: String): android.view.View
    }

    interface Presenter : BaseContracts.Presenter {
        fun getMeasures(): List<Measure>?
        fun setupMeasuresList(measures: List<Measure>?)
        fun calculateTextSize(text: String, defaultSize: Float): Float
        fun confirmPage()
        fun addMeasureToMap(id: Int, value: Float)
    }

    interface Interactor : BaseContracts.Interactor {
        fun getMeasures(): List<Measure>?
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        //TODO
    }

    interface Router : BaseContracts.Router {
        //TODO
    }

}