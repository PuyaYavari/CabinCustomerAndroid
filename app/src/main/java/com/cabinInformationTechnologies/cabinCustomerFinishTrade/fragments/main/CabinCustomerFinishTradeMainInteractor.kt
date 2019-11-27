package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.main

import android.content.Context
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.*
import com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.APICartAdapter
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIOrderId
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTMakeOrder
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTWITHID
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELCarts
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELOrderID
import com.squareup.moshi.Moshi

class CabinCustomerFinishTradeMainInteractor(var output: CabinCustomerFinishTradeMainContracts.InteractorOutput?) :
    CabinCustomerFinishTradeMainContracts.Interactor {

    private val informer: BaseContracts.Feedbacker by lazy {
        Informer()
    }

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun getCart(context: Context) {
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
                null
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
                            positiveText = context.resources.getString(R.string.retry),
                            positiveFunction = { getCart(context) },
                            negativeText = context.resources.getString(R.string.okay),
                            negativeFunction = { output?.closeActivity() }
                        )
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
                        positiveText = context.resources.getString(R.string.retry),
                        positiveFunction = { getCart(context) },
                        negativeText = context.resources.getString(R.string.okay),
                        negativeFunction = { output?.closeActivity() }
                    )
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "ERROR: Value: $value, URL: $url",
                        null
                    )
                    informer.feedback(
                        context = context,
                        title = context.resources.getString(R.string.error),
                        message = context.resources.getString(R.string.default_error_message),
                        positiveText = context.resources.getString(R.string.retry),
                        positiveFunction = { getCart(context) },
                        negativeText = context.resources.getString(R.string.okay),
                        negativeFunction = { output?.closeActivity() }
                    )
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "FAILURE",
                        throwable
                    )
                    if (NetworkManager.isNetworkConnected(context))
                        informer.feedback(
                            context = context,
                            title = context.resources.getString(R.string.error),
                            message = context.resources.getString(R.string.default_error_message),
                            positiveText = context.resources.getString(R.string.retry),
                            positiveFunction = { getCart(context) },
                            negativeText = context.resources.getString(R.string.okay),
                            negativeFunction = { output?.closeActivity() }
                        )
                    else
                        informer.feedback(
                            context = context,
                            title = context.resources.getString(R.string.attention),
                            message = context.resources.getString(R.string.no_internet),
                            positiveText = context.resources.getString(R.string.retry),
                            positiveFunction = { getCart(context) },
                            negativeText = context.resources.getString(R.string.okay),
                            negativeFunction = { output?.closeActivity() }
                        )
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
                        positiveText = context.resources.getString(R.string.retry),
                        positiveFunction = { getCart(context) },
                        negativeText = context.resources.getString(R.string.okay),
                        negativeFunction = { output?.closeActivity() }
                    )
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
                        positiveText = context.resources.getString(R.string.retry),
                        positiveFunction = { getCart(context) },
                        negativeText = context.resources.getString(R.string.okay),
                        negativeFunction = { output?.closeActivity() }
                    )
                }

            }
        )
    }

    override fun sendAddresses(context: Context, delivery: MODELAddress, invoice: MODELAddress?) {
        val responseObject = MODELOrderID()
        var data: REQUESTMakeOrder? = null
        val deliveryId = delivery.id
        val invoiceId = invoice?.id
        if (deliveryId != null && invoiceId != null)
            data = REQUESTMakeOrder(
                listOf(
                    REQUESTWITHID(
                        deliveryId
                    ),
                    REQUESTWITHID(
                        invoiceId
                    )
                )
            )
        else if (deliveryId != null)
            data = REQUESTMakeOrder(
                listOf(
                    REQUESTWITHID(
                        deliveryId
                    )
                )
            )

        if (data != null) {
            NetworkManager.requestFactory<APIOrderId>(
                context,
                Constants.CART_MAKE_ORDER_URL,
                null,
                null,
                data,
                responseObject,
                null,
                object : BaseContracts.ResponseCallbacks {
                    override fun onSuccess(value: Any?) {
                        if (value == true) {
                            Logger.verbose(
                                context,
                                this::class.java.name,
                                "SUCCESS: order created and ID received.",
                                null
                            )
                            output?.setActivityOrderId(responseObject.getId())
                            output?.pageForward(0)
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
                                positiveText = context.resources.getString(R.string.retry),
                                positiveFunction = { sendAddresses(context, delivery, invoice) },
                                negativeText = context.resources.getString(R.string.okay),
                                negativeFunction = { output?.closeActivity() }
                            )
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
                            positiveText = context.resources.getString(R.string.retry),
                            positiveFunction = { sendAddresses(context, delivery, invoice) },
                            negativeText = context.resources.getString(R.string.okay),
                            negativeFunction = { output?.closeActivity() }
                        )
                    }

                    override fun onError(value: String, url: String?) {
                        Logger.warn(
                            context,
                            this::class.java.name,
                            "ERROR: Value: $value, URL: $url",
                            null
                        )
                        informer.feedback(
                            context = context,
                            title = context.resources.getString(R.string.error),
                            message = context.resources.getString(R.string.default_error_message),
                            positiveText = context.resources.getString(R.string.retry),
                            positiveFunction = { sendAddresses(context, delivery, invoice) },
                            negativeText = context.resources.getString(R.string.okay),
                            negativeFunction = { output?.closeActivity() }
                        )
                    }

                    override fun onFailure(throwable: Throwable) {
                        Logger.verbose(
                            context,
                            this::class.java.name,
                            "FAILURE",
                            throwable
                        )
                        if (NetworkManager.isNetworkConnected(context))
                            informer.feedback(
                                context = context,
                                title = context.resources.getString(R.string.error),
                                message = context.resources.getString(R.string.default_error_message),
                                positiveText = context.resources.getString(R.string.retry),
                                positiveFunction = { sendAddresses(context, delivery, invoice) },
                                negativeText = context.resources.getString(R.string.okay),
                                negativeFunction = { output?.closeActivity() }
                            )
                        else
                            informer.feedback(
                                context = context,
                                title = context.resources.getString(R.string.attention),
                                message = context.resources.getString(R.string.no_internet),
                                positiveText = context.resources.getString(R.string.retry),
                                positiveFunction = { sendAddresses(context, delivery, invoice) },
                                negativeText = context.resources.getString(R.string.okay),
                                negativeFunction = { output?.closeActivity() }
                            )
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
                            positiveText = context.resources.getString(R.string.retry),
                            positiveFunction = { sendAddresses(context, delivery, invoice) },
                            negativeText = context.resources.getString(R.string.okay),
                            negativeFunction = { output?.closeActivity() }
                        )
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
                            positiveText = context.resources.getString(R.string.retry),
                            positiveFunction = { sendAddresses(context, delivery, invoice) },
                            negativeText = context.resources.getString(R.string.okay),
                            negativeFunction = { output?.closeActivity() }
                        )
                    }
                }
            )
        }
    }
    //endregion
}