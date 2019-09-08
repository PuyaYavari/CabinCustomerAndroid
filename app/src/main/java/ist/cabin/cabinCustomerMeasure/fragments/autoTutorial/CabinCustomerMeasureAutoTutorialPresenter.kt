package ist.cabin.cabinCustomerMeasure.fragments.autoTutorial

import android.app.Activity
import android.os.Bundle

class CabinCustomerMeasureAutoTutorialPresenter(var view: CabinCustomerMeasureAutoTutorialContracts.View?) :
    CabinCustomerMeasureAutoTutorialContracts.Presenter,
    CabinCustomerMeasureAutoTutorialContracts.InteractorOutput {

    var interactor: CabinCustomerMeasureAutoTutorialContracts.Interactor? =
        CabinCustomerMeasureAutoTutorialInteractor(this)
    var router: CabinCustomerMeasureAutoTutorialContracts.Router? = null

    private var current = 0

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerMeasureAutoTutorialRouter(activity)

        bundle?.let {
            //you can delete this if there's no need to get extras from the intent
            //TODO: Do something
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

    override fun showNext() {
        when (current) {
            0 -> {
                view!!.forwardToOutfit()
                current += 1
            }
            1 -> {
                view!!.forwardToShoes()
                current += 1
            }
            else -> {
                view!!.toNextPage()
                router?.toNextPage()
            }
        }
    }

    override fun showPrevious() {
        when (current) {
            0 -> view!!.toPreviousPage()
            1 -> {
                view!!.backToHairstyle()
                current -= 1
            }
            2 -> {
                view!!.backToOutfit()
                current -= 1
            }
        }
    }

    //endregion

    //region InteractorOutput

    //TODO: Implement your InteractorOutput methods here

    //endregion
}