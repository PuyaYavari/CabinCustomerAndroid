package com.cabinInformationTechnologies.cabin.fragments.favorites

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts
import com.cabinInformationTechnologies.cabinCustomerBase.Constants
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager
import com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.APIProductAdapter
import com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.JSONProductAdapter
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAPIProduct
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTColor
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTProduct
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProducts
import com.squareup.moshi.Moshi

class CabinCustomerFavoritesInteractor(var output: CabinCustomerFavoritesContracts.InteractorOutput?) :
    CabinCustomerFavoritesContracts.Interactor {

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
                    if (value == true)
                        output?.setData(responseObject.products)
                    else
                        Logger.warn(
                            context,
                            this::class.java.name,
                            "Value not mapped properly!\nValue: $value",
                            null
                        )
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Issue: ${value.message}",
                        null
                    )
                    output?.feedback(value.message)
                    output?.noInternet(NetworkManager.isNetworkConnected(context))
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Error: $value",
                        null
                    )
                    output?.feedback(value)
                    output?.noInternet(NetworkManager.isNetworkConnected(context))
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "Failure",
                        throwable
                    )
                    output?.feedback(null)
                    output?.noInternet(NetworkManager.isNetworkConnected(context))
                }

                override fun onServerDown() {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Server Down",
                        null
                    )
                    output?.feedback(null)
                    output?.noInternet(NetworkManager.isNetworkConnected(context))
                }

                override fun onException(exception: Exception) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "Exception",
                        exception
                    )
                    output?.feedback(null)
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
                    Logger.info(
                        context,
                        this::class.java.name,
                        "SUCCESS, Value: $value",
                        null)
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "ISSUE, Value: $value",
                        null)
                    //TODO: SHOW ISSUE
                    output?.undoRemove()
                }

                override fun onError(value: String, url: String?) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(
                        context,
                        this::class.java.name,
                        "Error, Value: $value",
                        null)
                    //TODO: SHOW ERROR AND URL
                    output?.undoRemove()
                }

                override fun onFailure(throwable: Throwable) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                        context,
                        this::class.java.name,
                        "FAILURE",
                        throwable)
                    //TODO: SHOW DEFAULT FAILURE ERROR
                    output?.undoRemove()
                }

                override fun onServerDown() {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(
                        context,
                        this::class.java.name,
                        "SERVER DOWN",
                        null)
                    output?.undoRemove()
                }

                override fun onException(exception: Exception) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                        context,
                        this::class.java.name,
                        "EXCEPTION",
                        exception)
                    //TODO: HANDLE
                    output?.undoRemove()
                }
            }
        )
    }

    override fun addToCart(
        context: Context,
        amount: Int,
        productId: Int,
        color: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor,
        size: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize
    ) {
        val data = com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAPIProduct(
            listOf(
                com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTProduct(
                    productId,
                    amount,
                    listOf(
                        com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTColor(
                            productId,
                            listOf(com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTSize(size.id))
                        )
                    )
                )
            )
        )
        com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager.requestFactory<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIProduct>(
            context,
            com.cabinInformationTechnologies.cabinCustomerBase.Constants.DISCOVER_ADD_TO_CART_URL,
            null,
            null,
            data,
            null,
            null,
            object : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                        context,
                        this::class.java.name,
                        "SUCCESS, Value: $value",
                        null)
                }

                override fun onIssue(value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(
                        context,
                        this::class.java.name,
                        "ISSUE, Value: $value",
                        null)
                    //TODO: SHOW ISSUE
                }

                override fun onError(value: String, url: String?) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(
                        context,
                        this::class.java.name,
                        "Error, Value: $value",
                        null)
                    //TODO: SHOW ERROR AND URL
                }

                override fun onFailure(throwable: Throwable) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                        context,
                        this::class.java.name,
                        "FAILURE",
                        throwable)
                    //TODO: SHOW DEFAULT FAILURE ERROR
                }

                override fun onServerDown() {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(
                        context,
                        this::class.java.name,
                        "SERVER DOWN",
                        null)
                }

                override fun onException(exception: Exception) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                        context,
                        this::class.java.name,
                        "EXCEPTION",
                        exception)
                    //TODO: HANDLE
                }
            }
        )
    }

    //endregion
}