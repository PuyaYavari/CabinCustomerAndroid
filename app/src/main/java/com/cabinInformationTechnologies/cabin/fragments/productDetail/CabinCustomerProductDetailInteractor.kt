package com.cabinInformationTechnologies.cabin.fragments.productDetail

import android.content.Context
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts
import com.cabinInformationTechnologies.cabinCustomerBase.Constants
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager
import com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.APICartAdapter
import com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.APIProductAdapter
import com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.JSONProductAdapter
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.*
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.*
import com.squareup.moshi.Moshi

class CabinCustomerProductDetailInteractor(var output: CabinCustomerProductDetailContracts.InteractorOutput?) :
    CabinCustomerProductDetailContracts.Interactor {

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
                    Logger.info(
                        context,
                        this::class.java.name,
                        "Success ${value.toString()}",
                        null
                    )
                    output?.setCart(responseObject.getCarts()[0])
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.info(
                        context,
                        this::class.java.name,
                        "Issue ${value.message}",
                        null
                    )
                    output?.productAddedToCart()
                    //TODO: FEEDBACK
                }

                override fun onError(value: String, url: String?) {
                    Logger.info(
                        context,
                        this::class.java.name,
                        "Error $value",
                        null
                    )
                    output?.productAddedToCart()
                    //TODO: FEEDBACK
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.info(
                        context,
                        this::class.java.name,
                        "Failure ${throwable.message}",
                        null
                    )
                    output?.productAddedToCart()
                    //TODO: FEEDBACK
                }

                override fun onServerDown() {
                    Logger.info(
                        context,
                        this::class.java.name,
                        "Server Down",
                        null
                    )
                    output?.productAddedToCart()
                    //TODO: FEEDBACK
                }

                override fun onException(exception: Exception) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "Exception",
                        exception
                    )
                    output?.productAddedToCart()
                    //TODO: FEEDBACK
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
                    Logger.info(
                        context,
                        this::class.java.name,
                        "SUCCESS, Value: $value",
                        null
                    )
                    output?.showMessage(null)
                    requestCart(context)
                }

                override fun onIssue(value: JSONIssue) {
                    output?.showMessage(value.message)
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "ISSUE, Value: $value",
                        null)
                    //TODO: SHOW ISSUE
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Error, Value: $value",
                        null)
                    output?.showMessage(value)
                    //TODO: SHOW ERROR AND URL
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "FAILURE",
                        throwable)
                    //TODO: SHOW DEFAULT FAILURE ERROR
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                }

                override fun onServerDown() {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "SERVER DOWN",
                        null)
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                }

                override fun onException(exception: Exception) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "EXCEPTION",
                        exception)
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                    //TODO: HANDLE
                }
            }
        )
    }

    override fun requestProduct(context: Context, id: Int) {
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
                        if (responseClass.products.isNotEmpty())
                            output?.updateProduct(responseClass.products[0])
                        Logger.info(
                            context,
                            this::class.java.name,
                            "SUCCESS, Value: $value",
                            null)
                    }
                }

                override fun onIssue(value: JSONIssue) {
                    output?.showMessage(value.message) //TODO: HANDLE
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "ISSUE, Value: $value",
                        null)
                    //TODO: SHOW ISSUE
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Error, Value: $value",
                        null)
                    output?.showMessage(value)
                    //TODO: SHOW ERROR AND URL
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "FAILURE",
                        throwable)
                    //TODO: SHOW DEFAULT FAILURE ERROR
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                }

                override fun onServerDown() {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "SERVER DOWN",
                        null)
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                }

                override fun onException(exception: Exception) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "EXCEPTION",
                        exception)
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                    //TODO: HANDLE
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
                    Logger.info(
                        context,
                        this::class.java.name,
                        "SUCCESS, Value: $value",
                        null)
                }

                override fun onIssue(value: JSONIssue) {
                    output?.showMessage(value.message) //TODO: HANDLE
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "ISSUE, Value: $value",
                        null)
                    //TODO: SHOW ISSUE
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Error, Value: $value",
                        null)
                    output?.showMessage(value)
                    //TODO: SHOW ERROR AND URL
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "FAILURE",
                        throwable)
                    //TODO: SHOW DEFAULT FAILURE ERROR
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                }

                override fun onServerDown() {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "SERVER DOWN",
                        null)
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                }

                override fun onException(exception: Exception) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "EXCEPTION",
                        exception)
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                    //TODO: HANDLE
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
                    Logger.info(
                        context,
                        this::class.java.name,
                        "SUCCESS, Value: $value",
                        null)
                }

                override fun onIssue(value: JSONIssue) {
                    output?.showMessage(value.message) //TODO: HANDLE
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "ISSUE, Value: $value",
                        null)
                    //TODO: SHOW ISSUE
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Error, Value: $value",
                        null)
                    output?.showMessage(value)
                    //TODO: SHOW ERROR AND URL
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "FAILURE",
                        throwable)
                    //TODO: SHOW DEFAULT FAILURE ERROR
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                }

                override fun onServerDown() {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "SERVER DOWN",
                        null)
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                }

                override fun onException(exception: Exception) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "EXCEPTION",
                        exception)
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                    //TODO: HANDLE
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
                    Logger.info(
                        context,
                        this::class.java.name,
                        "Success ${value.toString()}",
                        null
                    )
                    var cart: MODELCart? = null
                    if (carts.getCarts().isNotEmpty())
                        cart = carts.getCarts()[0]
                    output?.setCart(cart)
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.info(
                        context,
                        this::class.java.name,
                        "Issue ${value.message}",
                        null
                    )
                }

                override fun onError(value: String, url: String?) {
                    Logger.info(
                        context,
                        this::class.java.name,
                        "Error $value",
                        null
                    )
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.info(
                        context,
                        this::class.java.name,
                        "Failure ${throwable.message}",
                        null
                    )
                }

                override fun onServerDown() {
                    Logger.info(
                        context,
                        this::class.java.name,
                        "Server Down",
                        null
                    )
                }

                override fun onException(exception: Exception) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "Exception",
                        exception
                    )
                }
            }
        )
    }

    //endregion
}