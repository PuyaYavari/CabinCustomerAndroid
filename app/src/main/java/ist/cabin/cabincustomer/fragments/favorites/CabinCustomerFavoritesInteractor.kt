package ist.cabin.cabincustomer.fragments.favorites

import android.content.Context
import android.util.Log
import com.squareup.moshi.Moshi
import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerBase.Constants
import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.NetworkManager
import ist.cabin.cabinCustomerBase.models.adapters.APIProductAdapter
import ist.cabin.cabinCustomerBase.models.adapters.JSONProductAdapter
import ist.cabin.cabinCustomerBase.models.backend.*
import ist.cabin.cabinCustomerBase.models.local.MODELColor
import ist.cabin.cabinCustomerBase.models.local.MODELProduct
import ist.cabin.cabinCustomerBase.models.local.MODELProducts
import ist.cabin.cabinCustomerBase.models.local.MODELSize

class CabinCustomerFavoritesInteractor(var output: CabinCustomerFavoritesContracts.InteractorOutput?) :
    CabinCustomerFavoritesContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun getFavorites(context: Context, page: Int) {
        val responseObject = MODELProducts()
        NetworkManager.requestFactory(
            context,
            Constants.LIST_FAVORITES_URL,
            page,
            Constants.FAVORITE_PAGE_SIZE,
            null,
            responseObject,
            APIProductAdapter(Moshi.Builder().add(JSONProductAdapter(Moshi.Builder().build())).build()),
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if (value == true)
                        output?.setData(responseObject.products)
                    else
                        Logger.warn(
                            this::class.java.name,
                            "Value not mapped properly!\nValue: $value",
                            null
                        )
                }

                override fun onIssue(value: JSONIssue) {
                    //TODO: SHOW FEEDBACK
                    Logger.warn(
                        this::class.java.name,
                        "Issue: ${value.message}",
                        null
                    )
                }

                override fun onError(value: String, url: String?) {
                    //TODO: SHOW FEEDBACK
                    Logger.warn(
                        this::class.java.name,
                        "Error: $value",
                        null
                    )
                }

                override fun onFailure(throwable: Throwable) {
                    //TODO: SHOW FEEDBACK
                    Logger.error(
                        this::class.java.name,
                        "Failure",
                        throwable
                    )
                }

                override fun onServerDown() {
                    //TODO: SHOW FEEDBACK
                    Logger.warn(
                        this::class.java.name,
                        "Server Down",
                        null
                    )
                }

                override fun onException(exception: Exception) {
                    //TODO: SHOW FEEDBACK
                    Logger.error(
                        this::class.java.name,
                        "Exception",
                        exception
                    )
                }

            }
        )
    }

    override fun removeFromFavorites(context: Context, product: MODELProduct) {
        val data = REQUESTAPIProduct (
            listOf(
                REQUESTProduct(
                    product.getId(),
                    null,
                    listOf(
                        REQUESTColor(
                            product.getColors()[0].id,
                            null
                        )
                    )
                )
            )
        )
        NetworkManager.requestFactory<Any?>(
            context,
            Constants.DISCOVER_REMOVE_FROM_FAVORITE_URL,
            null,
            null,
            data,
            null,
            null,
            object : BaseContracts.ResponseCallbacks{
                override fun onSuccess(value: Any?) {
                    Logger.info(this::class.java.name, "SUCCESS, Value: $value", null)
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.warn(this::class.java.name, "ISSUE, Value: $value", null)
                    //TODO: SHOW ISSUE
                    output?.undoRemove()
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(this::class.java.name, "Error, Value: $value", null)
                    //TODO: SHOW ERROR AND URL
                    output?.undoRemove()
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.error(this::class.java.name, "FAILURE", throwable)
                    //TODO: SHOW DEFAULT FAILURE ERROR
                    output?.undoRemove()
                }

                override fun onServerDown() {
                    Logger.warn(this::class.java.name, "SERVER DOWN", null)
                    output?.undoRemove()
                }

                override fun onException(exception: Exception) {
                    Logger.error(this::class.java.name, "EXCEPTION", exception)
                    //TODO: HANDLE
                    output?.undoRemove()
                }
            }
        )
    }

    override fun addToCart(
        context: Context,
        amount: Int,
        productId: Int,
        color: MODELColor,
        size: MODELSize
    ) {
        val data =  REQUESTAPIProduct(
            listOf(
                REQUESTProduct(
                    productId,
                    amount,
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