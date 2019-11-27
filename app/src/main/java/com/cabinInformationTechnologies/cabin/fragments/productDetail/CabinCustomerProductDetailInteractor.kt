package com.cabinInformationTechnologies.cabin.fragments.productDetail

import android.content.Context
import androidx.navigation.NavController
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.*
import com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.APICartAdapter
import com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.APIProductAdapter
import com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.JSONProductAdapter
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.*
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.*
import com.squareup.moshi.Moshi

class CabinCustomerProductDetailInteractor(var output: CabinCustomerProductDetailContracts.InteractorOutput?) :
    CabinCustomerProductDetailContracts.Interactor {

    private val informer: BaseContracts.Feedbacker by lazy {
        Informer()
    }

    override fun unregister() {
        output = null
    }

    //region Interactor

    private fun requestCart(context: Context) {
        val responseObject = MODELCarts()
        NetworkManager.requestFactory(
            context,
            Constants.CART_LIST_ALL_URL,
            null,
            null,
            null,
            responseObject,
            APICartAdapter(
                context,
                Moshi.Builder().build(),
                null
            ),
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if (value == true) {
                        Logger.info(
                            context,
                            this::class.java.name,
                            "SUCCESS: cart received.",
                            null
                        )
                        output?.setCart(responseObject.getCarts()[0])
                    } else {
                        Logger.warn(
                            context,
                            this::class.java.name,
                            "AMBIGUOUS RESPONSE: ${value.toString()}",
                            null
                        )
                        output?.productAddedToCart()
                    }
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "ISSUE: ${value.message}",
                        null
                    )
                    output?.productAddedToCart()
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "ERROR: Value: $value, URL: $url",
                        null
                    )
                    output?.productAddedToCart()
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "FAILURE",
                        throwable
                    )
                    output?.productAddedToCart()
                }

                override fun onServerDown() {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "SERVER DOWN!!",
                        null
                    )
                    output?.productAddedToCart()
                }

                override fun onException(exception: Exception) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "Exception",
                        exception
                    )
                    output?.productAddedToCart()
                }
            }
        )
    }

    override fun addToCart(context: Context,
                           productId: Int,
                           amount: Int,
                           colorId: Int,
                           sizeId: Int
    ){
        val data: REQUESTAPIProduct? =
            REQUESTAPIProduct(
                listOf(
                    REQUESTProduct(
                        productId,
                        amount,
                        listOf(
                            REQUESTColor(
                                colorId,
                                listOf(REQUESTSize(sizeId))
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
                    output?.showSuccessMessage()
                    requestCart(context)
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "ISSUE: ${value.message}",
                        null
                    )
                    output?.showButton()
                    informer.feedback(
                        context = context,
                        title = context.resources.getString(R.string.default_error_message),
                        message = value.message,
                        neutralText = context.resources.getString(R.string.okay)
                    ) { addToCart(context, productId, amount, colorId, sizeId) }
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "ERROR: Value: $value, URL: $url",
                        null
                    )
                    output?.showButton()
                    informer.feedback(
                        context = context,
                        title = context.resources.getString(R.string.default_error_message),
                        message = context.resources.getString(R.string.default_error_message),
                        neutralText = context.resources.getString(R.string.okay)
                    ) { addToCart(context, productId, amount, colorId, sizeId) }
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "FAILURE",
                        throwable
                    )
                    output?.showButton()
                    if (NetworkManager.isNetworkConnected(context))
                        informer.feedback(
                            context = context,
                            title = context.resources.getString(R.string.default_error_message),
                            message = context.resources.getString(R.string.default_error_message),
                            neutralText = context.resources.getString(R.string.okay)
                        ) { addToCart(context, productId, amount, colorId, sizeId) }
                    else
                        informer.feedback(
                            context = context,
                            title = context.resources.getString(R.string.attention),
                            message = context.resources.getString(R.string.no_internet),
                            neutralText = context.resources.getString(R.string.okay)
                        ) { addToCart(context, productId, amount, colorId, sizeId) }
                }

                override fun onServerDown() {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "SERVER DOWN!!",
                        null
                    )
                    output?.showButton()
                    informer.feedback(
                        context = context,
                        title = context.resources.getString(R.string.default_error_message),
                        message = context.resources.getString(R.string.default_error_message),
                        neutralText = context.resources.getString(R.string.okay)
                    ) { addToCart(context, productId, amount, colorId, sizeId) }
                }

                override fun onException(exception: Exception) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "EXCEPTION",
                        exception
                    )
                    output?.showButton()
                    informer.feedback(
                        context = context,
                        title = context.resources.getString(R.string.default_error_message),
                        message = context.resources.getString(R.string.default_error_message),
                        neutralText = context.resources.getString(R.string.okay)
                    ) { addToCart(context, productId, amount, colorId, sizeId) }
                }
            }
        )
    }

    override fun requestProduct(context: Context, id: Int, navController: NavController) {
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
                        informer.feedback(
                            context = context,
                            navController = navController,
                            title = context.resources.getString(R.string.error),
                            message = context.resources.getString(R.string.default_error_message)
                        ) { requestProduct(context, id, navController) }
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
                    ) { requestProduct(context, id, navController) }
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
                        message = context.resources.getString(R.string.default_error_message)
                    ) { requestProduct(context, id, navController) }
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
                        ) { requestProduct(context, id, navController) }
                    else
                        informer.feedback(
                            context = context,
                            navController = navController,
                            title = context.resources.getString(R.string.attention),
                            message = context.resources.getString(R.string.no_internet)
                        ) { requestProduct(context, id, navController) }
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
                    ) { requestProduct(context, id, navController) }
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
                    ) { requestProduct(context, id, navController) }
                }
            }
        )
    }

    override fun addFavorite(context: Context, product: MODELProduct, color: MODELColor) {
        val data = REQUESTAPIProduct(
            listOf(
                REQUESTProduct(
                    product.getId(),
                    null,
                    listOf(
                        REQUESTColor(
                            color.id,
                            null
                        )
                    )
                )
            )
        )
        NetworkManager.requestFactory<Any?>(
            context,
            Constants.DISCOVER_ADD_TO_FAVORITE_URL,
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
                        "SUCCESS: product added to favorite.",
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

    override fun removeFavorite(context: Context, product: MODELProduct, color: MODELColor) {
        val data = REQUESTAPIProduct(
            listOf(
                REQUESTProduct(
                    product.getId(),
                    null,
                    listOf(
                        REQUESTColor(
                            color.id,
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
                        "SUCCESS: product removed from favorite.",
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

    override fun updateProduct(context: Context, productId: Int, colorId: Int, sizeId: Int, amount: Int) {
        val carts = MODELCarts()
        val data = REQUESTAPIProduct(
                listOf(
                    REQUESTProduct(
                        productId,
                        amount,
                        listOf(
                            REQUESTColor(
                                colorId,
                                listOf(
                                    REQUESTSize(
                                        sizeId
                                    )
                                )
                            )
                        )
                    )
                )
            )
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
                            "SUCCESS: cart received.",
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