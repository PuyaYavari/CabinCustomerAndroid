package ist.cabin.cabincustomer.fragments.favorites

import android.app.Activity
import android.content.Context
import android.os.Bundle
import ist.cabin.cabinCustomerBase.models.local.MODELColor
import ist.cabin.cabinCustomerBase.models.local.MODELProduct
import ist.cabin.cabinCustomerBase.models.local.MODELSize

class CabinCustomerFavoritesPresenter(var view: CabinCustomerFavoritesContracts.View?) :
    CabinCustomerFavoritesContracts.Presenter,
    CabinCustomerFavoritesContracts.InteractorOutput {

    var interactor: CabinCustomerFavoritesContracts.Interactor? =
        CabinCustomerFavoritesInteractor(this)
    var router: CabinCustomerFavoritesContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerFavoritesRouter(activity)

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

    override fun getFavorites(context: Context, page: Int) {
        interactor?.getFavorites(context, page)
    }

    override fun moveToProductDetail(product: MODELProduct) {
        router?.moveToProductDetail(product)
    }

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

    override fun setData(products: List<MODELProduct?>) {
        val myDataset: MutableList<MODELProduct> = mutableListOf()
        products.forEach {
            if (it != null)
                myDataset.add(it)
        }
        if (myDataset.isNotEmpty())
            view?.showData(myDataset)
    }

    //endregion
}