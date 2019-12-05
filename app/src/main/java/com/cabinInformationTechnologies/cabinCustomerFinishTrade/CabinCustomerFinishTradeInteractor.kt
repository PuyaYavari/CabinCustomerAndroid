package com.cabinInformationTechnologies.cabinCustomerFinishTrade

import android.content.Context
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.*
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAPIWITHORDERID
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTWITHID

class CabinCustomerFinishTradeInteractor(var output: CabinCustomerFinishTradeContracts.InteractorOutput?) :
    CabinCustomerFinishTradeContracts.Interactor {

    private val informer: BaseContracts.Feedbacker by lazy {
        Informer()
    }

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun activateOrder(context: Context, orderId: Int) {
        val data = REQUESTAPIWITHORDERID(
            listOf(
                REQUESTWITHID(
                    orderId
                )
            )
        )
        NetworkManager.requestFactory<Any?>(
            context,
            Constants.CART_ACTIVATE_ORDER_URL,
            null,
            null,
            data,
            null,
            null,
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "SUCCESS: order activated.",
                        null
                    )
                    output?.success()
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
                    ) { activateOrder(context, orderId) }
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
                        message = value,
                        neutralText = context.resources.getString(R.string.okay)
                    ) { activateOrder(context, orderId) }
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
                            neutralText = context.resources.getString(R.string.okay)
                        ) { activateOrder(context, orderId) }
                    else
                        informer.feedback(
                            context = context,
                            title = context.resources.getString(R.string.attention),
                            message = context.resources.getString(R.string.no_internet),
                            neutralText = context.resources.getString(R.string.okay)
                        ) { activateOrder(context, orderId) }
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
                    ) { activateOrder(context, orderId) }
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
                    ) { activateOrder(context, orderId) }
                }
            }
        )
    }

    //endregion
}