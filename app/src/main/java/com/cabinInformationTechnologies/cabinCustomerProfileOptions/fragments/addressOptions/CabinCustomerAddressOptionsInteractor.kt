package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions

import android.content.Context
import androidx.navigation.NavController
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.*
import com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.APIAddressAdapter
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAPIRemoveAddress
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTRemoveAddress
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddresses
import com.squareup.moshi.Moshi

class CabinCustomerAddressOptionsInteractor(var output: CabinCustomerAddressOptionsContracts.InteractorOutput?) :
    CabinCustomerAddressOptionsContracts.Interactor {

    private val informer: BaseContracts.Feedbacker by lazy {
        Informer()
    }

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun getAddresses(context: Context, navController: NavController) {
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
                            navController = navController,
                            title = context.resources.getString(R.string.error),
                            message = context.resources.getString(R.string.default_error_message)
                        ) { getAddresses(context, navController) }
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
                        navController = navController,
                        title = context.resources.getString(R.string.error),
                        message = value.message
                    ) { getAddresses(context, navController) }
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
                        navController = navController,
                        title = context.resources.getString(R.string.error),
                        message = value
                    ) { getAddresses(context, navController) }
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
                            navController = navController,
                            title = context.resources.getString(R.string.error),
                            message = context.resources.getString(R.string.default_error_message)
                        ) { getAddresses(context, navController) }
                    else
                        informer.feedback(
                            context = context,
                            navController = navController,
                            title = context.resources.getString(R.string.attention),
                            message = context.resources.getString(R.string.no_internet)
                        ) { getAddresses(context, navController) }
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
                        navController = navController,
                        title = context.resources.getString(R.string.error),
                        message = context.resources.getString(R.string.default_error_message)
                    ) { getAddresses(context, navController) }
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
                        navController = navController,
                        title = context.resources.getString(R.string.error),
                        message = context.resources.getString(R.string.default_error_message)
                    ) { getAddresses(context, navController) }
                }
            }
        )
    }

    override fun removeAddress(context: Context, address: MODELAddress) {
        var data: REQUESTAPIRemoveAddress? = null
        val id = address.id
        if (id != null)
            data = REQUESTAPIRemoveAddress(
                listOf(
                    REQUESTRemoveAddress(
                        id
                    )
                )
            )
        if (data != null)
            NetworkManager.requestFactory<Any?>(
                context,
                Constants.REMOVE_ADDRESS_URL,
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
                            "SUCCESS: address removed.",
                            null
                        )
                    }

                    override fun onIssue(value: JSONIssue) {
                        Logger.verbose(
                            context,
                            this::class.java.name,
                            "ISSUE: ${value.message}",
                            null
                        )
                        output?.undoRemove()
                        informer.feedback(context, context.resources.getString(R.string.default_error_message))
                    }

                    override fun onError(value: String, url: String?) {
                        Logger.warn(
                            context,
                            this::class.java.name,
                            "ERROR: Value: $value, URL: $url",
                            null
                        )
                        output?.undoRemove()
                        informer.feedback(context, value)
                    }

                    override fun onFailure(throwable: Throwable) {
                        Logger.verbose(
                            context,
                            this::class.java.name,
                            "FAILURE",
                            throwable
                        )
                        output?.undoRemove()
                        informer.feedback(context, context.resources.getString(R.string.default_error_message))
                    }

                    override fun onServerDown() {
                        Logger.warn(
                            context,
                            this::class.java.name,
                            "SERVER DOWN!!",
                            null
                        )
                        output?.undoRemove()
                        informer.feedback(context, context.resources.getString(R.string.default_error_message))
                    }

                    override fun onException(exception: Exception) {
                        Logger.error(
                            context,
                            this::class.java.name,
                            "EXCEPTION",
                            exception
                        )
                        output?.undoRemove()
                        informer.feedback(context, context.resources.getString(R.string.default_error_message))
                    }
                }
            )
    }
    //endregion
}