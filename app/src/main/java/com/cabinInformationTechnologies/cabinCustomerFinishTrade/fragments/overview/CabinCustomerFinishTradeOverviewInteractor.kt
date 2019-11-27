package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.overview

import android.content.Context
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.*
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIAgreements
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAPIWITHORDERID
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTWITHID
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAgreements

class CabinCustomerFinishTradeOverviewInteractor(var output: CabinCustomerFinishTradeOverviewContracts.InteractorOutput?) :
    CabinCustomerFinishTradeOverviewContracts.Interactor {

    private val informer: BaseContracts.Feedbacker by lazy {
        Informer()
    }

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun listAgreements(context: Context, orderId: Int) {
        val responseObject = MODELAgreements()
        val data = REQUESTAPIWITHORDERID (
            listOf(
                REQUESTWITHID(
                    orderId
                )
            )
        )
        NetworkManager.requestFactory<APIAgreements>(
            context,
            Constants.CART_LIST_ORDER_AGREEMENT_URL,
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
                            "SUCCESS: agreements received.",
                            null
                        )
                        output?.setAgreements(responseObject)
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
                            positiveFunction = { listAgreements(context, orderId) },
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
                        positiveFunction = { listAgreements(context, orderId) },
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
                        positiveFunction = { listAgreements(context, orderId) },
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
                            positiveFunction = { listAgreements(context, orderId) },
                            negativeText = context.resources.getString(R.string.okay),
                            negativeFunction = { output?.closeActivity() }
                        )
                    else
                        informer.feedback(
                            context = context,
                            title = context.resources.getString(R.string.attention),
                            message = context.resources.getString(R.string.no_internet),
                            positiveText = context.resources.getString(R.string.retry),
                            positiveFunction = { listAgreements(context, orderId) },
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
                        positiveFunction = { listAgreements(context, orderId) },
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
                        positiveFunction = { listAgreements(context, orderId) },
                        negativeText = context.resources.getString(R.string.okay),
                        negativeFunction = { output?.closeActivity() }
                    )
                }
            }
        )
    }

    //endregion
}