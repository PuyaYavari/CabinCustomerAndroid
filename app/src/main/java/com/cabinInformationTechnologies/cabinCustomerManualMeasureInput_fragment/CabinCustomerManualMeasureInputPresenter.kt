package com.cabinInformationTechnologies.cabinCustomerManualMeasureInput_fragment

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup

class CabinCustomerManualMeasureInputPresenter(var view: com.cabinInformationTechnologies.cabinCustomerManualMeasureInput_fragment.CabinCustomerManualMeasureInputContracts.View?) :
    com.cabinInformationTechnologies.cabinCustomerManualMeasureInput_fragment.CabinCustomerManualMeasureInputContracts.Presenter,
    com.cabinInformationTechnologies.cabinCustomerManualMeasureInput_fragment.CabinCustomerManualMeasureInputContracts.InteractorOutput {

    var interactor: com.cabinInformationTechnologies.cabinCustomerManualMeasureInput_fragment.CabinCustomerManualMeasureInputContracts.Interactor? =
        com.cabinInformationTechnologies.cabinCustomerManualMeasureInput_fragment.CabinCustomerManualMeasureInputInteractor(
            this
        )
    var router: com.cabinInformationTechnologies.cabinCustomerManualMeasureInput_fragment.CabinCustomerManualMeasureInputContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router =
            com.cabinInformationTechnologies.cabinCustomerManualMeasureInput_fragment.CabinCustomerManualMeasureInputRouter(
                activity
            )

        bundle?.let {
            //you can delete this if there's no need to get extras from the intent
        }
    }

    override fun onDestroy() {
        view = null
        interactor?.unregister()
        interactor = null
        router?.unregister()
        router = null
    }

    //endregion

    //region Presenter

    override fun getMeasures(): List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONMeasure>? {
        val measures = interactor?.getMeasures()
        Log.d("From Interactor", measures.toString())
        return measures!!
    }

    override fun setupMeasuresList(JSONMeasures: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONMeasure>?) {
        var inputBox: View
        JSONMeasures?.forEach {
            if (it.name != null) {
                inputBox = view!!.createTextInputBox(it.id, it.name.toString()) //TODO: shouldn't get persons height
                if (inputBox.parent != null) {
                    (inputBox.parent as ViewGroup).removeView(inputBox)
                    Log.d("textInputBox", "parent removed")
                }
                view!!.addToMeasuresList(inputBox)
            } else {
                Log.e("error", "measure " + it.id.toString() + " has no name.")
            }
        }
    }

    override fun calculateTextSize(text: String, defaultSize: Float): Float {
        var textSize = 0f
        when (text.length < 9) {
            true -> textSize = defaultSize
            false -> textSize = defaultSize - (text.length - 9) * 0.4f
        }
        return textSize
    }

    override fun confirmPage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addMeasureToMap(id: Int, value: Float) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //endregion

    //region InteractorOutput

    //endregion
}