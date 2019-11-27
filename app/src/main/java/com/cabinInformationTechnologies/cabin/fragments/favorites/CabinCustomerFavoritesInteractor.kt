package com.cabinInformationTechnologies.cabin.fragments.favorites

import android.content.Context
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.*
import com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.APIProductAdapter
import com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.JSONProductAdapter
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.*
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProducts
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize
import com.squareup.moshi.Moshi

class CabinCustomerFavoritesInteractor(var output: CabinCustomerFavoritesContracts.InteractorOutput?) :
    CabinCustomerFavoritesContracts.Interactor {

    private val informer: BaseContracts.Feedbacker by lazy {
        Informer()
    }

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun getFavorites(context: Context, page: Int) {
        val responseObject = MODELProducts()
        NetworkManager.requestFactory(
            context,
            Constants.LIST_FAVORITES_URL,
            page,
            Constants.FAVORITE_PAGE_SIZE,
            null,
            responseObject,
            APIProductAdapter(
                Moshi.Builder().add(
                    JSONProductAdapter(
                        context,
                        Moshi.Builder().build()
                    )
                ).build()
            ),
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if (value == true) {
                        Logger.verbose(
                            context,
                            this::class.java.name,
                            "SUCCESS: favorites received.",
                            null
                        )
                        output?.setData(responseObject.products)
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
                            ) { getFavorites(context, page) }
                        else
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
                    if (page == 1)
                        informer.feedback(
                            context = context,
                            title = context.resources.getString(R.string.error),
                            message = value.message,
                            neutralText = context.resources.getString(R.string.okay)
                        ) { getFavorites(context, page) }
                    else
                        informer.feedback(context, context.resources.getString(R.string.default_error_message))
                    output?.noInternet(NetworkManager.isNetworkConnected(context))
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Error, Value: $value, URL: $url",
                        null
                    )
                    if (page == 1)
                        informer.feedback(
                            context = context,
                            title = context.resources.getString(R.string.error),
                            message = context.resources.getString(R.string.default_error_message),
                            neutralText = context.resources.getString(R.string.okay)
                        ) { getFavorites(context, page) }
                    else
                        informer.feedback(context, context.resources.getString(R.string.default_error_message))
                    output?.noInternet(NetworkManager.isNetworkConnected(context))
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "FAILURE",
                        throwable
                    )
                    if (page == 1)
                        informer.feedback(
                            context = context,
                            title = context.resources.getString(R.string.error),
                            message = context.resources.getString(R.string.default_error_message),
                            neutralText = context.resources.getString(R.string.okay)
                        ) { getFavorites(context, page) }
                    else
                        informer.feedback(context, context.resources.getString(R.string.default_error_message))
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
                        ) { getFavorites(context, page) }
                    else
                        informer.feedback(context, context.resources.getString(R.string.default_error_message))
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
                        ) { getFavorites(context, page) }
                    else
                        informer.feedback(context, context.resources.getString(R.string.default_error_message))
                    output?.noInternet(NetworkManager.isNetworkConnected(context))
                }

            }
        )
    }

    override fun removeFromFavorites(context: Context, product: MODELProduct) {
        val data = REQUESTAPIProduct(
            listOf(
                REQUESTProduct(
                    product.getId(),
                    null,
                    listOf(
                        REQUESTColor(
                            product.getColors()[0].id,
                            null
                        )
                    )
                )
            )
        )
        NetworkManager.requestFactory<Any?>(
            context,
            Constants.DISCOVER_REMOVE_FROM_FAVORITE_URL,
            null,
            null,
            data,
            null,
            null,
            object : BaseContracts.ResponseCallbacks{
                override fun onSuccess(value: Any?) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "SUCCESS: product removed from favorites.",
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
                    informer.feedback(context, value.message)
                    output?.undoRemove()
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Error, Value: $value, URL: $url",
                        null
                    )
                    informer.feedback(context, context.resources.getString(R.string.default_error_message))
                    output?.undoRemove()
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "FAILURE",
                        throwable
                    )
                    informer.feedback(context, context.resources.getString(R.string.default_error_message))
                    output?.undoRemove()
                }

                override fun onServerDown() {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "SERVER DOWN!!",
                        null
                    )
                    informer.feedback(context, context.resources.getString(R.string.default_error_message))
                    output?.undoRemove()
                }

                override fun onException(exception: Exception) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "EXCEPTION",
                        exception
                    )
                    informer.feedback(context, context.resources.getString(R.string.default_error_message))
                    output?.undoRemove()
                }
            }
        )
    }

    override fun addToCart(
        context: Context,
        amount: Int,
        productId: Int,
        color: MODELColor,
        size: MODELSize
    ) {
        val data = REQUESTAPIProduct(
            listOf(
                REQUESTProduct(
                    productId,
                    amount,
                    listOf(
                        REQUESTColor(
                            productId,
                            listOf(REQUESTSize(size.id))
                        )
                    )
                )
            )
        )
        NetworkManager.requestFactory<APIProduct>(
            context,
            Constants.DISCOVER_ADD_TO_CART_URL,
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
                        "SUCCESS: product added to cart.",
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