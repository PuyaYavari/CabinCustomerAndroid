package ist.cabin.cabinCustomerManualMeasureInput_fragment

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import ist.cabin.cabincustomer.models.Measure

class CabinCustomerManualMeasureInputPresenter(var view: CabinCustomerManualMeasureInputContracts.View?) :
    CabinCustomerManualMeasureInputContracts.Presenter,
    CabinCustomerManualMeasureInputContracts.InteractorOutput {

    var interactor: CabinCustomerManualMeasureInputContracts.Interactor? =
        CabinCustomerManualMeasureInputInteractor(this)
    var router: CabinCustomerManualMeasureInputContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router =
            CabinCustomerManualMeasureInputRouter(
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

    override fun getMeasures(): List<Measure>? {
        val measures = interactor?.getMeasures()
        Log.d("From Interactor", measures.toString())
        return measures!!
    }

    override fun setupMeasuresList(measures: List<Measure>?) {
        var inputBox: View
        measures?.forEach {
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