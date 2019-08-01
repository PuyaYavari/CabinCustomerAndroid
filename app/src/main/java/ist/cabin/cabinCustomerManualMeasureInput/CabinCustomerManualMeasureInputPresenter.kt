package ist.cabin.cabinCustomerManualMeasureInput

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup

class CabinCustomerManualMeasureInputPresenter(var view: CabinCustomerManualMeasureInputContracts.View?) :
    CabinCustomerManualMeasureInputContracts.Presenter, CabinCustomerManualMeasureInputContracts.InteractorOutput {

    var interactor: CabinCustomerManualMeasureInputContracts.Interactor? =
        CabinCustomerManualMeasureInputInteractor(this)
    var router: CabinCustomerManualMeasureInputContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerManualMeasureInputRouter(activity)

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

    override fun getMeasures(): MutableList<String> {
        val measures = interactor?.getMeasures()
        Log.d("From Interactor", measures.toString())
        return measures!!
    }

    override fun setupMeasuresList(measures: MutableList<String>) {
        var inputBox: View
        measures.forEach {
            inputBox = view!!.createTextInputBox(it) //TODO: shouldn't get persons height
            if (inputBox.parent != null) {
                (inputBox.parent as ViewGroup).removeView(inputBox)
                Log.d("textInputBox", "parent removed")
            }
            view!!.addToMeasuresList(inputBox)
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

    //endregion

    //region InteractorOutput

    //endregion
}