package ist.cabin.cabincustomer.fragments.cart

import android.content.Context
import com.squareup.moshi.Moshi
import ist.cabin.cabinCustomerBase.models.backend.JSONColor
import ist.cabin.cabinCustomerBase.models.local.MODELProduct

class CabinCustomerCartInteractor(var output: CabinCustomerCartContracts.InteractorOutput?) :
    CabinCustomerCartContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun getCart() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateProduct(
        context: Context,
        amount: Int,
        color: JSONColor,
        productId: Int
    ) {
        //val responseClass = /*MODELProducts()*/
        val moshi: Moshi = Moshi.Builder()
            .add(/*JSONProductAdapter*/(Moshi.Builder().build()))
            .build()
        var products: MutableList<MODELProduct>? = null //TODO: CART PRODUCTS
        val data = null//TODO
//        NetworkManager.requestFactory(
//            context,
//            Constants.CART_UPDATE_URL,
//            null,
//            null,
//            data,
//            null,
//            null,
//            object : BaseContracts.ResponseCallbacks {
//                override fun onSuccess(value: Any?) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//
//                override fun onIssue(value: JSONIssue) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//
//                override fun onFailure(throwable: Throwable) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//
//                override fun onServerDown() {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//
//                override fun onError(value: String, url: String?) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//
//                override fun onException(exception: Exception) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//            })
    }

    //endregion
}