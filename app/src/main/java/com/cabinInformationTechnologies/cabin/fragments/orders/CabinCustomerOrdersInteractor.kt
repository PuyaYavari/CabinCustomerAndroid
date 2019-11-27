package com.cabinInformationTechnologies.cabin.fragments.orders

import android.content.Context
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.*
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIOrders
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELOrders

class CabinCustomerOrdersInteractor(var output: CabinCustomerOrdersContracts.InteractorOutput?) :
    CabinCustomerOrdersContracts.Interactor {

    private val informer: BaseContracts.Feedbacker by lazy {
        Informer()
    }

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun getFirstPage(context: Context) {
        val responseObject = MODELOrders()
        NetworkManager.requestFactory<APIOrders>(
            context,
            Constants.LIST_ORDERS_URL,
            1,
            Constants.ORDERS_PAGE_SIZE,
            null,
            responseObject,
            null,
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if (value == true) {
                        Logger.verbose(
                            context,
                            this::class.java.name,
                            "SUCCESS: orders received.",
                            null
                        )
                        output?.setFirstPage(responseObject)
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
                        ) { getFirstPage(context) }
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
                    ) { getFirstPage(context) }
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
                        neutralText = context.resources.getString(R.string.okay)
                    ) { getFirstPage(context) }
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "FAILURE",
                        throwable
                    )
                    if (!NetworkManager.isNetworkConnected(context))
                        informer.feedback(
                            context = context,
                            title = context.resources.getString(R.string.error),
                            message = context.resources.getString(R.string.no_internet),
                            neutralText = context.resources.getString(R.string.okay)
                        ) { getFirstPage(context) }
                    else
                        informer.feedback(
                            context = context,
                            title = context.resources.getString(R.string.error),
                            message = context.resources.getString(R.string.default_error_message),
                            neutralText = context.resources.getString(R.string.okay)
                        ) { getFirstPage(context) }
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
                    ) { getFirstPage(context) }
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
                    ) { getFirstPage(context) }
                }

            }
        )
    }

    override fun refresh(context: Context, refreshLayout: SwipeRefreshLayout?, adapter: CabinCustomerOrdersAdapter) {
        val responseObject = MODELOrders()
        NetworkManager.requestFactory<APIOrders>(
            context,
            Constants.LIST_ORDERS_URL,
            1,
            Constants.ORDERS_PAGE_SIZE,
            null,
            responseObject,
            null,
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if (value == true) {
                        Logger.verbose(
                            context,
                            this::class.java.name,
                            "SUCCESS: orders received.",
                            null
                        )
                        output?.refresh(responseObject)
                        adapter.notifyDataSetChanged()
                    } else {
                        Logger.warn(
                            context,
                            this::class.java.name,
                            "AMBIGUOUS RESPONSE: ${value.toString()}",
                            null
                        )
                        informer.feedback(context, context.resources.getString(R.string.default_error_message))
                    }
                    refreshLayout?.isRefreshing = false
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "ISSUE: ${value.message}",
                        null
                    )
                    informer.feedback(context, value.message)
                    refreshLayout?.isRefreshing = false
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "ERROR: Value: $value, URL: $url",
                        null
                    )
                    informer.feedback(context, context.resources.getString(R.string.default_error_message))
                    refreshLayout?.isRefreshing = false
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "FAILURE",
                        throwable
                    )
                    informer.feedback(context, context.resources.getString(R.string.default_error_message))
                    refreshLayout?.isRefreshing = false
                }

                override fun onServerDown() {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "SERVER DOWN!!",
                        null
                    )
                    informer.feedback(context, context.resources.getString(R.string.default_error_message))
                    refreshLayout?.isRefreshing = false
                }

                override fun onException(exception: Exception) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "EXCEPTION",
                        exception
                    )
                    informer.feedback(context, context.resources.getString(R.string.default_error_message))
                    refreshLayout?.isRefreshing = false
                }
            }
        )
    }

    override fun getNewPageIn(context: Context, page: Int, adapter: CabinCustomerOrdersAdapter) {
        val responseObject = MODELOrders()
        NetworkManager.requestFactory<APIOrders>(
            context,
            Constants.LIST_ORDERS_URL,
            page,
            Constants.ORDERS_PAGE_SIZE,
            null,
            responseObject,
            null,
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if (value == true) {
                        Logger.verbose(
                            context,
                            this::class.java.name,
                            "SUCCESS: orders received.",
                            null
                        )
                        output?.setOrdersIn(responseObject, adapter)
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
                        "ERROR: Value: $value, URL: $url",
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