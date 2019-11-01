package com.cabinInformationTechnologies.cabin.fragments.cart

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts
import com.cabinInformationTechnologies.cabinCustomerBase.Constants
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager
import com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.APICartAdapter
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.*
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELCart
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELCarts
import com.squareup.moshi.Moshi

class CabinCustomerCartInteractor(val context: Context?,
                                  var output: CabinCustomerCartContracts.InteractorOutput?) :
    CabinCustomerCartContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    private fun removeItemFromCart(sellerId: Int?, productId: Int?, colorId: Int?) {
        try {
            val carts = MODELCarts()
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
                    listOf( REQUESTSeller(
                            sellerId,
                            null
                        )
                    )
                )
            }
            if (context != null) {
                NetworkManager.requestFactory(
                    context,
                    Constants.CART_REMOVE_ALL_URL,
                    null,
                    null,
                    data,
                    carts,
                    APICartAdapter(
                        context,
                        Moshi.Builder().build(),
                        null
                    ),
                    object :
                        BaseContracts.ResponseCallbacks {
                        override fun onSuccess(value: Any?) {
                            Logger.info(
                                context,
                                this::class.java.name,
                                "Product removed from cart.",
                                null
                            )
                        }

                        override fun onIssue(value: JSONIssue) {
                            Logger.warn(
                                context,
                                this::class.java.name,
                                "Product not removed from cart.\n" +
                                        "ISSUE: ${value.message}",
                                null
                            )
                        }

                        override fun onError(value: String, url: String?) {
                            Logger.warn(
                                context,
                                this::class.java.name,
                                "Product not removed from cart.\n" +
                                        "ERROR: $value",
                                null
                            )
                        }

                        override fun onFailure(throwable: Throwable) {
                            Logger.error(
                                context,
                                this::class.java.name,
                                "FAILURE: Product not removed from cart.",
                                throwable
                            )
                        }

                        override fun onServerDown() {
                            Logger.failure(
                                context,
                                this::class.java.name,
                                "SERVER DOWN!!",
                                null
                            )
                        }

                        override fun onException(exception: Exception) {
                            Logger.error(
                                context,
                                this::class.java.name,
                                "EXCEPTION: Product not removed from cart.",
                                exception
                            )
                        }

                    }
                )
                output?.setCart(carts.getCarts()[0])
            }
        } catch (exception: Exception) {
            if (context != null)
                Logger.failure(
                    context,
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
        val carts = MODELCarts()
        NetworkManager.requestFactory(
            context,
            Constants.CART_LIST_ALL_URL,
            null,
            null,
            null,
            carts,
            //null,
            APICartAdapter(
                context,
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
                    Logger.info(
                        context,
                        this::class.java.name,
                        "Success ${value.toString()}",
                        null)
                    val cart = carts.getCarts()[0]
                    output?.setCart(cart)
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.info(
                        context,
                        this::class.java.name,
                        "Issue ${value.message}",
                        null)
                    output?.feedback(value.message)
                    output?.noInternet(NetworkManager.isNetworkConnected(context))
                }

                override fun onError(value: String, url: String?) {
                    Logger.info(
                        context,
                        this::class.java.name,
                        "Error $value",
                        null)
                    output?.feedback(value)
                    output?.noInternet(NetworkManager.isNetworkConnected(context))
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.info(
                        context,
                        this::class.java.name,
                        "Failure ${throwable.message}",
                        null)
                    output?.feedback(null)
                    output?.noInternet(NetworkManager.isNetworkConnected(context))
                }

                override fun onServerDown() {
                    Logger.info(
                        context,
                        this::class.java.name,
                        "Server Down",
                        null)
                }

                override fun onException(exception: Exception) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "Exception",
                        exception)
                    output?.feedback(null)
                    output?.noInternet(NetworkManager.isNetworkConnected(context))
                }

            }
        )
    }

    override fun updateProduct(context: Context, product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct) {
        val carts = MODELCarts()
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
        NetworkManager.requestFactory(
            context,
            Constants.CART_UPDATE_URL,
            null,
            null,
            data,
            carts,
            //null,
            APICartAdapter(
                context,
                Moshi.Builder().build(),
                null
            ),
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    Logger.info(
                        context,
                        this::class.java.name,
                        "Success ${value.toString()}",
                        null)
                    var cart: MODELCart? = null
                    if (carts.getCarts().isNotEmpty())
                         cart = carts.getCarts()[0]
                    output?.setCart(cart)
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.info(
                        context,
                        this::class.java.name,
                        "Issue ${value.message}",
                        null)
                }

                override fun onError(value: String, url: String?) {
                    Logger.info(
                        context,
                        this::class.java.name,
                        "Error $value",
                        null)
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.info(
                        context,
                        this::class.java.name,
                        "Failure ${throwable.message}",
                        null)
                }

                override fun onServerDown() {
                    Logger.info(
                        context,
                        this::class.java.name,
                        "Server Down",
                        null)
                }

                override fun onException(exception: Exception) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "Exception",
                        exception)
                }
            }
        )
    }

    //endregion
}