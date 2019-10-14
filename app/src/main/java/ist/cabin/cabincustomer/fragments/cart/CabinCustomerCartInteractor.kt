package ist.cabin.cabincustomer.fragments.cart

import android.content.Context
import com.squareup.moshi.Moshi
import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerBase.Constants
import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.NetworkManager
import ist.cabin.cabinCustomerBase.models.adapters.APICartAdapter
import ist.cabin.cabinCustomerBase.models.backend.*
import ist.cabin.cabinCustomerBase.models.local.MODELCart
import ist.cabin.cabinCustomerBase.models.local.MODELCarts
import ist.cabin.cabinCustomerBase.models.local.MODELProduct

class CabinCustomerCartInteractor(var output: CabinCustomerCartContracts.InteractorOutput?) :
    CabinCustomerCartContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun getCart(context: Context) {
        val carts: MODELCarts = MODELCarts()
        NetworkManager.requestFactory<APICart>(
            context,
            Constants.CART_LIST_ALL_URL,
            null,
            null,
            null,
            carts,
            //null,
            APICartAdapter(
                Moshi.Builder().build(),
                object : CabinCustomerCartContracts.CartCallback {
                    override fun updateCart(cart: MODELCart) {
                        output?.setCart(cart)
                    }
                }
            ),
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    Logger.info(this::class.java.name, "Success ${value.toString()}", null)
                    val cart = carts.getCarts()[0]
                    output?.setCart(cart)
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.info(this::class.java.name, "Issue ${value.message}", null)
                }

                override fun onError(value: String, url: String?) {
                    Logger.info(this::class.java.name, "Error $value", null)
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.info(this::class.java.name, "Failure ${throwable.message}", null)
                }

                override fun onServerDown() {
                    Logger.info(this::class.java.name, "Server Down", null)
                }

                override fun onException(exception: Exception) {
                    Logger.error(this::class.java.name, "Exception", exception)
                }

            }
        )
    }

    override fun updateProduct(context: Context, product: MODELProduct) {
        val carts: MODELCarts = MODELCarts()
        var data: REQUESTAPIProduct? = null
        val amount = product.getAmount()
        if (amount != null) {
            data = REQUESTAPIProduct(
                listOf(
                    REQUESTProduct(
                        product.getId(),
                        amount,
                        listOf(
                            REQUESTColor(
                                product.getColors()[0].id,
                                listOf(
                                    REQUESTSize(
                                        product.getColors()[0].sizes[0].id
                                    )
                                )
                            )
                        )
                    )
                )
            )
        }
        NetworkManager.requestFactory<APICart>(
            context,
            Constants.CART_UPDATE_URL,
            null,
            null,
            data,
            carts,
            //null,
            APICartAdapter(Moshi.Builder().build(), null),
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    Logger.info(this::class.java.name, "Success ${value.toString()}", null)
                    var cart: MODELCart? = null
                    if (carts.getCarts().isNotEmpty())
                         cart = carts.getCarts()[0]
                    output?.setCart(cart)
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.info(this::class.java.name, "Issue ${value.message}", null)
                }

                override fun onError(value: String, url: String?) {
                    Logger.info(this::class.java.name, "Error $value", null)
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.info(this::class.java.name, "Failure ${throwable.message}", null)
                }

                override fun onServerDown() {
                    Logger.info(this::class.java.name, "Server Down", null)
                }

                override fun onException(exception: Exception) {
                    Logger.error(this::class.java.name, "Exception", exception)
                }
            })
    }

    //endregion
}