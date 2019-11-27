package com.cabinInformationTechnologies.cabin.fragments.cart

import android.content.Context
import android.widget.Toast
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.*
import com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.APICartAdapter
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.*
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELCart
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELCarts
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct
import com.squareup.moshi.Moshi

class CabinCustomerCartInteractor(val view: CabinCustomerCartContracts.ViewForInteractor,
                                  var output: CabinCustomerCartContracts.InteractorOutput?) :
    CabinCustomerCartContracts.Interactor {

    private val informer: BaseContracts.Feedbacker by lazy {
        Informer()
    }

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
            val context = view.getFragmentContext()
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
                            Logger.verbose(
                                context,
                                this::class.java.name,
                                "SUCCESS: product removed from cart.",
                                null
                            )
                            output?.setCart(carts.getCarts()[0])
                        }

                        override fun onIssue(value: JSONIssue) {
                            Logger.verbose(
                                context,
                                this::class.java.name,
                                "ISSUE: ${value.message}",
                                null
                            )
                            informer.feedback(context, value.message)
                        }

                        override fun onError(value: String, url: String?) {
                            Logger.warn(
                                context,
                                this::class.java.name,
                                "Error, Value: $value, URL: $url",
                                null
                            )
                            informer.feedback(context, context.resources.getString(R.string.default_error_message))
                        }

                        override fun onFailure(throwable: Throwable) {
                            Logger.verbose(
                                context,
                                this::class.java.name,
                                "FAILURE",
                                throwable
                            )
                            informer.feedback(context, context.resources.getString(R.string.default_error_message))
                        }

                        override fun onServerDown() {
                            Logger.warn(
                                context,
                                this::class.java.name,
                                "SERVER DOWN!!",
                                null
                            )
                            informer.feedback(context, context.resources.getString(R.string.default_error_message))
                        }

                        override fun onException(exception: Exception) {
                            Logger.error(
                                context,
                                this::class.java.name,
                                "EXCEPTION",
                                exception
                            )
                            informer.feedback(context, context.resources.getString(R.string.default_error_message))
                        }
                    }
                )
            }
        } catch (exception: Exception) {
            val context = view.getFragmentContext()
            if (context != null) {
                Logger.failure(
                    context,
                    this::class.java.name,
                    "Error while sending request to remove problematic product from cart.",
                    exception
                )
                informer.feedback(context, context.resources.getString(R.string.default_error_message))
            }
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
                        informer.feedback(context, message, Toast.LENGTH_LONG)
                    }

                    override fun removeItems() {
                        removeItemFromCart(sellerId, productId, colorId)
                    }
                }
            ),
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if (value == true) {
                        Logger.verbose(
                            context,
                            this::class.java.name,
                            "SUCCESS: cart received.",
                            null
                        )
                        val cart = carts.getCarts()[0]
                        output?.setCart(cart)
                    } else {
                        Logger.warn(
                            context,
                            this::class.java.name,
                            "AMBIGUOUS RESPONSE: ${value.toString()}",
                            null
                        )
                        informer.feedback(
                            context = context,
                            title = context.resources.getString(R.string.error),
                            message = context.resources.getString(R.string.default_error_message),
                            neutralText = context.resources.getString(R.string.okay)
                        ) { getCart(context) }
                    }
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "ISSUE: ${value.message}",
                        null
                    )
                    informer.feedback(
                        context = context,
                        title = context.resources.getString(R.string.error),
                        message = value.message,
                        neutralText = context.resources.getString(R.string.okay)
                    ) { getCart(context) }
                    output?.noInternet(NetworkManager.isNetworkConnected(context))
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Error, Value: $value, URL: $url",
                        null
                    )
                    informer.feedback(
                        context = context,
                        title = context.resources.getString(R.string.error),
                        message = context.resources.getString(R.string.default_error_message),
                        neutralText = context.resources.getString(R.string.okay)
                    ) { getCart(context) }
                    output?.noInternet(NetworkManager.isNetworkConnected(context))
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "FAILURE",
                        throwable
                    )
                    informer.feedback(
                        context = context,
                        title = context.resources.getString(R.string.error),
                        message = context.resources.getString(R.string.default_error_message),
                        neutralText = context.resources.getString(R.string.okay)
                    ) { getCart(context) }
                    output?.noInternet(NetworkManager.isNetworkConnected(context))
                }

                override fun onServerDown() {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "SERVER DOWN!!",
                        null
                    )
                    informer.feedback(
                        context = context,
                        title = context.resources.getString(R.string.error),
                        message = context.resources.getString(R.string.default_error_message),
                        neutralText = context.resources.getString(R.string.okay)
                    ) { getCart(context) }
                    output?.noInternet(NetworkManager.isNetworkConnected(context))
                }

                override fun onException(exception: Exception) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "EXCEPTION",
                        exception
                    )
                    informer.feedback(
                        context = context,
                        title = context.resources.getString(R.string.error),
                        message = context.resources.getString(R.string.default_error_message),
                        neutralText = context.resources.getString(R.string.okay)
                    ) { getCart(context) }
                    output?.noInternet(NetworkManager.isNetworkConnected(context))
                }

            }
        )
    }

    override fun updateProduct(context: Context, product: MODELProduct) {
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
            APICartAdapter(
                context,
                Moshi.Builder().build(),
                null
            ),
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if (value == true) {
                        Logger.verbose(
                            context,
                            this::class.java.name,
                            "SUCCESS: product updated.",
                            null
                        )
                        var cart: MODELCart? = null
                        if (carts.getCarts().isNotEmpty())
                            cart = carts.getCarts()[0]
                        output?.setCart(cart)
                    } else {
                        Logger.warn(
                            context,
                            this::class.java.name,
                            "AMBIGUOUS RESPONSE: ${value.toString()}",
                            null
                        )
                        informer.feedback(context, context.resources.getString(R.string.default_error_message))
                    }
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "ISSUE: ${value.message}",
                        null
                    )
                    informer.feedback(context, value.message)
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Error, Value: $value, URL: $url",
                        null
                    )
                    informer.feedback(context, context.resources.getString(R.string.default_error_message))
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "FAILURE",
                        throwable
                    )
                    informer.feedback(context, context.resources.getString(R.string.default_error_message))
                }

                override fun onServerDown() {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "SERVER DOWN!!",
                        null
                    )
                    informer.feedback(context, context.resources.getString(R.string.default_error_message))
                }

                override fun onException(exception: Exception) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "EXCEPTION",
                        exception
                    )
                    informer.feedback(context, context.resources.getString(R.string.default_error_message))
                }
            }
        )
    }

    //endregion
}