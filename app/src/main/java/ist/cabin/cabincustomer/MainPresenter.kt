package ist.cabin.cabincustomer

import android.app.Activity
import android.os.Bundle

class MainPresenter(var view: MainContracts.View?) : MainContracts.Presenter, MainContracts.InteractorOutput {

    var interactor: MainContracts.Interactor? = MainInteractor(this)
    var router: MainContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = MainRouter(activity)
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

    override fun moveToProfileOptions() {
        router?.moveToProfileOptions()
    }

    override fun moveToExtraditions() {
        router?.moveToExtraditions()
    }

    //endregion

    //region InteractorOutput

    //endregion
}