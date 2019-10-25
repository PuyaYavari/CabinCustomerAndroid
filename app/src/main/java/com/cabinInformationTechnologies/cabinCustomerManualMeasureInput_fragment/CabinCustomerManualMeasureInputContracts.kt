package com.cabinInformationTechnologies.cabinCustomerManualMeasureInput_fragment

object CabinCustomerManualMeasureInputContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {
        fun addToMeasuresList(inputBox: android.view.View)
        fun createTextInputBox(id: Int, name: String): android.view.View
    }

    interface Presenter : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        fun getMeasures(): List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONMeasure>?
        fun setupMeasuresList(JSONMeasures: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONMeasure>?)
        fun calculateTextSize(text: String, defaultSize: Float): Float
        fun confirmPage()
        fun addMeasureToMap(id: Int, value: Float)
    }

    interface Interactor : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor {
        fun getMeasures(): List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONMeasure>?
    }

    interface InteractorOutput : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput {
        //TODO
    }

    interface Router : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Router {
        //TODO
    }

}