package ist.cabin.cabincustomer.fragments.productDetail

import android.app.Activity
import android.content.Context
import android.os.Bundle
import ist.cabin.cabinCustomerBase.models.local.MODELColor
import ist.cabin.cabinCustomerBase.models.local.MODELSize

class CabinCustomerProductDetailPresenter(var view: CabinCustomerProductDetailContracts.View?) :
    CabinCustomerProductDetailContracts.Presenter,
    CabinCustomerProductDetailContracts.InteractorOutput {

    var interactor: CabinCustomerProductDetailContracts.Interactor? =
        CabinCustomerProductDetailInteractor(this)
    var router: CabinCustomerProductDetailContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerProductDetailRouter(activity)

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

    override fun addToCart(
        context: Context,
        amount: Int,
        productId: Int,
        color: MODELColor,
        size: MODELSize
    ) {
        interactor?.addToCart(context, amount, productId, color, size)
    }

    //endregion

    //region InteractorOutput

    //TODO: Implement your InteractorOutput methods here

    //endregion
}