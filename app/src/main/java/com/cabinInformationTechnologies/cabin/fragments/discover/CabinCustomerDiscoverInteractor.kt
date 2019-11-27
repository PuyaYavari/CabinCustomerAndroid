package com.cabinInformationTechnologies.cabin.fragments.discover

import android.content.Context
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.*
import com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.APIProductAdapter
import com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.JSONProductAdapter
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.*
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProducts
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSorts
import com.squareup.moshi.Moshi

class CabinCustomerDiscoverInteractor(var output: CabinCustomerDiscoverContracts.InteractorOutput?) :
    CabinCustomerDiscoverContracts.Interactor {

    private val informer: BaseContracts.Feedbacker by lazy {
        Informer()
    }

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun getProducts(context: Context, page: Int) {
        requestProducts(context, page, null)
    }

    override fun getProducts(context: Context, page: Int, sort: Int) {
        requestProducts(context, page, sort)
    }

    private fun requestProducts(context: Context, page: Int, sort: Int?){
        var data: REQUESTAPISort? = null
        if (sort != null)
            data = REQUESTAPISort(
                listOf(
                    REQUESTWITHID(
                        sort
                    )
                )
            )
        val responseClass = MODELProducts()
        val moshi: Moshi = Moshi.Builder()
            .add(JSONProductAdapter(context,Moshi.Builder().build()))
            .build()
        var products: MutableList<MODELProduct>?
        NetworkManager.requestFactory(
            context,
            Constants.DISCOVER_LIST_PRODUCTS_URL,
            page,
            Constants.DISCOVER_PAGE_SIZE,
            data,
            responseClass,
            APIProductAdapter(moshi),
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if (value == true) {
                        Logger.verbose(
                            context,
                            this::class.java.name,
                            "SUCCESS: products received",
                            null
                        )
                        products = responseClass.products
                        if (products != null)
                            output?.addData(products)
                        else
                            Logger.verbose(
                                context,
                                this::class.java.name,
                                "products are null!!",
                                null
                            )
                    } else {
                        Logger.warn(
                            context,
                            this::class.java.name,
                            "AMBIGUOUS RESPONSE: ${value.toString()}",
                            null
                        )
                        if (page == 1)
                            informer.feedback(
                                context = context,
                                title = context.resources.getString(R.string.error),
                                message = context.resources.getString(R.string.default_error_message),
                                neutralText = context.resources.getString(R.string.okay)
                            ) { requestProducts(context, page, sort) }
                        else
                            informer.feedback(
                                context,
                                context.resources.getString(R.string.default_error_message)
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
                    if (page == 1)
                        informer.feedback(
                            context = context,
                            title = context.resources.getString(R.string.error),
                            message = value.message,
                            neutralText = context.resources.getString(R.string.okay)
                        ) { requestProducts(context, page, sort) }
                    else
                        informer.feedback(
                            context,
                            context.resources.getString(R.string.default_error_message)
                        )
                    output?.noInternet(NetworkManager.isNetworkConnected(context))
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "ERROR: Value: $value, URL: $url",
                        null
                    )
                    if (page == 1)
                        informer.feedback(
                            context = context,
                            title = context.resources.getString(R.string.error),
                            message = context.resources.getString(R.string.default_error_message),
                            neutralText = context.resources.getString(R.string.okay)
                        ) { requestProducts(context, page, sort) }
                    else
                        informer.feedback(
                            context,
                            context.resources.getString(R.string.default_error_message)
                        )
                    output?.noInternet(NetworkManager.isNetworkConnected(context))
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "FAILURE",
                        throwable
                    )
                    if (page == 1) {
                        if (NetworkManager.isNetworkConnected(context))
                            informer.feedback(
                                context = context,
                                title = context.resources.getString(R.string.error),
                                message = context.resources.getString(R.string.default_error_message),
                                neutralText = context.resources.getString(R.string.okay)
                            ) { requestProducts(context, page, sort) }
                        else
                            informer.feedback(
                                context = context,
                                title = context.resources.getString(R.string.attention),
                                message = context.resources.getString(R.string.no_internet),
                                neutralText = context.resources.getString(R.string.okay)
                            ) { requestProducts(context, page, sort) }
                    }
                    else
                        informer.feedback(
                            context,
                            context.resources.getString(R.string.default_error_message)
                        )
                    output?.noInternet(NetworkManager.isNetworkConnected(context))
                }

                override fun onServerDown() {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "SERVER DOWN!!",
                        null
                    )
                    if (page == 1)
                        informer.feedback(
                            context = context,
                            title = context.resources.getString(R.string.error),
                            message = context.resources.getString(R.string.default_error_message),
                            neutralText = context.resources.getString(R.string.okay)
                        ) { requestProducts(context, page, sort) }
                    else
                        informer.feedback(
                            context,
                            context.resources.getString(R.string.default_error_message)
                        )
                    output?.noInternet(NetworkManager.isNetworkConnected(context))
                }

                override fun onException(exception: Exception) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "EXCEPTION",
                        exception
                    )
                    if (page == 1)
                        informer.feedback(
                            context = context,
                            title = context.resources.getString(R.string.error),
                            message = context.resources.getString(R.string.default_error_message),
                            neutralText = context.resources.getString(R.string.okay)
                        ) { requestProducts(context, page, sort) }
                    else
                        informer.feedback(
                            context,
                            context.resources.getString(R.string.default_error_message)
                        )
                    output?.noInternet(NetworkManager.isNetworkConnected(context))
                }
            })
    }

    override fun getProduct(context: Context, id: Int) {
        val data =
            REQUESTAPIProduct(
                listOf(
                    REQUESTProduct(
                        id,
                        null,
                        null
                    )
                )
            )
        val responseClass = MODELProducts()
        val moshi: Moshi = Moshi.Builder()
            .add(JSONProductAdapter(context,Moshi.Builder().build()))
            .build()
        NetworkManager.requestFactory(
            context,
            Constants.LIST_PRODUCT_DETAIL_URL,
            null,
            null,
            data,
            responseClass,
            APIProductAdapter(moshi),
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if (value == true) {
                        Logger.verbose(
                            context,
                            this::class.java.name,
                            "SUCCESS: product received.",
                            null
                        )
                        if (responseClass.products.isNotEmpty())
                            output?.updateProduct(responseClass.products[0])
                    } else {
                        Logger.warn(
                            context,
                            this::class.java.name,
                            "AMBIGUOUS RESPONSE: ${value.toString()}",
                            null
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

    override fun getSortOptions(context: Context) {
        val responseObject = MODELSorts()
        NetworkManager.requestFactory<APISortOptions>(
            context,
            Constants.LIST_SORT_URL,
            null,
            null,
            null,
            responseObject,
            null,
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if (value == true) {
                        Logger.verbose(
                            context,
                            this::class.java.name,
                            "SUCCESS: sort receivec.",
                            null
                        )
                        output?.showSorts(responseObject)
                    } else {
                        Logger.warn(
                            context,
                            this::class.java.name,
                            "AMBIGUOUS RESPONSE: ${value.toString()}",
                            null
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
                        neutralText = context.resources.getString(R.string.okay)
                    ) { getSortOptions(context) }
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
                    ) { getSortOptions(context) }
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
                        ) { getSortOptions(context) }
                    else
                        informer.feedback(
                            context = context,
                            title = context.resources.getString(R.string.attention),
                            message = context.resources.getString(R.string.no_internet),
                            neutralText = context.resources.getString(R.string.okay)
                        ) { getSortOptions(context) }
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
                    ) { getSortOptions(context) }
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
                    ) { getSortOptions(context) }
                }
            }
        )
    }

    //endregion
}