package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.address

import android.content.Context
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.*
import com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.APIAddressAdapter
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddresses
import com.squareup.moshi.Moshi

class CabinCustomerFinishTradeAddressInteractor(var output: CabinCustomerFinishTradeAddressContracts.InteractorOutput?) :
    CabinCustomerFinishTradeAddressContracts.Interactor {

    private val informer: BaseContracts.Feedbacker by lazy {
        Informer()
    }

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun getAddresses(context: Context) {
        val responseObject = MODELAddresses()
        NetworkManager.requestFactory(
            context,
           Constants.LIST_ADDRESSES_URL,
            null,
            null,
            null,
            responseObject,
            APIAddressAdapter(
                context,
                Moshi.Builder().build()
            ),
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if (value == true) {
                        Logger.verbose(
                            context,
                            this::class.java.name,
                            "SUCCESS: addresses received.",
                            null
                        )
                        output?.setAddresses(responseObject)
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
                            positiveFunction = { getAddresses(context) },
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
                        positiveFunction = { getAddresses(context) },
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
                        positiveFunction = { getAddresses(context) },
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
                            positiveFunction = { getAddresses(context) },
                            negativeText = context.resources.getString(R.string.okay),
                            negativeFunction = { output?.closeActivity() }
                        )
                    else
                        informer.feedback(
                            context = context,
                            title = context.resources.getString(R.string.attention),
                            message = context.resources.getString(R.string.no_internet),
                            positiveText = context.resources.getString(R.string.retry),
                            positiveFunction = { getAddresses(context) },
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
                        positiveFunction = { getAddresses(context) },
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
                        positiveFunction = { getAddresses(context) },
                        negativeText = context.resources.getString(R.string.okay),
                        negativeFunction = { output?.closeActivity() }
                    )
                }
            }
        )
    }
    //endregion
}