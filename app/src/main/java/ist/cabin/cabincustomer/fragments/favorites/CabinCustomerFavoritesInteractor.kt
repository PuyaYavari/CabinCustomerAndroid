package ist.cabin.cabincustomer.fragments.favorites

import android.content.Context
import android.util.Log
import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerBase.Constants
import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.NetworkManager
import ist.cabin.cabinCustomerBase.models.backend.*
import ist.cabin.cabinCustomerBase.models.local.MODELColor
import ist.cabin.cabinCustomerBase.models.local.MODELSize

class CabinCustomerFavoritesInteractor(var output: CabinCustomerFavoritesContracts.InteractorOutput?) :
    CabinCustomerFavoritesContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun addToCart(
        context: Context,
        amount: Int,
        productId: Int,
        color: MODELColor,
        size: MODELSize
    ) {
        val data =  REQUESTAPIAddToCart(
            listOf(
                REQUESTProduct(
                    amount,
                    productId,
                    listOf(
                        REQUESTColor(
                            productId,
                            listOf(REQUESTSize(size.id))
                        )
                    )
                )
            )
        )
        NetworkManager.requestFactory<APIProduct>(
            context,
            Constants.DISCOVER_ADD_TO_CART_URL,
            null,
            null,
            data,
            null,
            null,
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    Logger.info(this::class.java.name, "SUCCESS, Value: $value", null)
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.info(this::class.java.name, "ISSUE, Value: $value", null)
                    //TODO: SHOW ISSUE
                }

                override fun onError(value: String, url: String?) {
                    Log.e("Discover ERROR", value)
                    if (url != null)
                        Log.d("Login onError url", url)
                    //TODO: SHOW ERROR AND URL
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.info(this::class.java.name, "FAILURE, ${throwable.message}", null)
                    //TODO: SHOW DEFAULT FAILURE ERROR
                }

                override fun onServerDown() {
                    Logger.info(this::class.java.name, "SERVER DOWN", null)
                    //TODO: SHOW DEFAULT FAILURE ERROR
                }

                override fun onException(exception: Exception) {
                    Logger.info(this::class.java.name, "EXCEPTION", exception)
                    //TODO: HANDLE
                }
            }
        )
    }

    //endregion
}