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

    private fun removeItemFromCart(sellerId: Int?, productId: Int?, colorId: Int?) {
        try {
            val carts: MODELCarts = MODELCarts()
            var data: REQUESTAPISeller? = null
            if (sellerId != null && productId != null && colorId != null) {
                data = REQUESTAPISeller(
                    listOf(
                        REQUESTSeller(
                            sellerId,
                            listOf(
                                REQUESTProduct(
                                    productId,
                                    null,
                                    listOf(
                                        REQUESTColor(
                                            colorId,
                                            null
                                        )
                                    )
                                )
                            )
                        )
                    )
                )
            } else if (sellerId != null && productId != null) {
                data = REQUESTAPISeller(
                    listOf(
                        REQUESTSeller(
                            sellerId,
                            listOf(
                                REQUESTProduct(
                                    productId,
                                    null,
                                    null
                                )
                            )
                        )
                    )
                )
            } else if (sellerId != null) {
                data = REQUESTAPISeller(
                    listOf(
                        REQUESTSeller(
                            sellerId,
                            null
                        )
                    )
                )
            }
            NetworkManager.requestFactory(
                null,
                Constants.CART_REMOVE_ALL_URL,
                null,
                null,
                data,
                carts,
                APICartAdapter(Moshi.Builder().build(), null),
                object : BaseContracts.ResponseCallbacks {
                    override fun onSuccess(value: Any?) {
                        Logger.info(
                            this::class.java.name,
                            "Product removed from cart.",
                            null
                        )
                    }

                    override fun onIssue(value: JSONIssue) {
                        Logger.warn(
                            this::class.java.name,
                            "Product not removed from cart.\n" +
                                    "ISSUE: ${value.message}",
                            null
                        )
                    }

                    override fun onError(value: String, url: String?) {
                        Logger.warn(
                            this::class.java.name,
                            "Product not removed from cart.\n" +
                                    "ERROR: $value",
                            null
                        )
                    }

                    override fun onFailure(throwable: Throwable) {
                        Logger.error(
                            this::class.java.name,
                            "FAILURE: Product not removed from cart.",
                            throwable
                        )
                    }

                    override fun onServerDown() {
                        Logger.failure(
                            this::class.java.name,
                            "SERVER DOWN!!",
                            null
                        )
                    }

                    override fun onException(exception: Exception) {
                        Logger.error(
                            this::class.java.name,
                            "EXCEPTION: Product not removed from cart.",
                            exception
                        )
                    }

                }
            )
            output?.setCart(carts.getCarts()[0])
        } catch (exception: Exception) {
            Logger.failure(
                this::class.java.name,
                "Error while sending request to remove problematic product from cart.",
                exception
            )
        }
    }

    override fun getCart(context: Context) {
        var sellerId: Int? = null
        var productId: Int? = null
        var colorId: Int? = null
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
                    override fun setSellerId(id: Int?) {
                        sellerId = id
                    }

                    override fun setProductId(id: Int?) {
                        productId = id
                    }

                    override fun setColorId(id: Int?) {
                        colorId = id
                    }

                    override fun feedback(message: String) {
                        output?.feedback(message)
                    }

                    override fun removeItems() {
                        removeItemFromCart(sellerId, productId, colorId)
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
            }
        )
    }

    //endregion
}