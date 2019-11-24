package com.cabinInformationTechnologies.cabin.fragments.discover

import android.content.Context
import android.util.Log
import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts
import com.cabinInformationTechnologies.cabinCustomerBase.Constants
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager
import com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.APIProductAdapter
import com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.JSONProductAdapter
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.*
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProducts
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSorts
import com.squareup.moshi.Moshi

class CabinCustomerDiscoverInteractor(var output: CabinCustomerDiscoverContracts.InteractorOutput?) :
    CabinCustomerDiscoverContracts.Interactor {

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
        var products: MutableList<MODELProduct>? = null
        NetworkManager.requestFactory<APIProduct>(
            context,
            Constants.DISCOVER_LIST_PRODUCTS_URL,
            page,
            Constants.DISCOVER_PAGE_SIZE,
            data,
            responseClass,
            APIProductAdapter(moshi),
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    Logger.info(
                        context,
                        this::class.java.name,
                        "SUCCESS, Value: $value",
                        null)
                    if (value == true)
                        products = responseClass.products
                    if (products != null)
                        output?.addData(products)
                    //TODO: ELSE
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.info(
                        context,
                        this::class.java.name,
                        "ISSUE, Value: $value",
                        null)
                    output?.feedback(value.message)
                    output?.noInternet(NetworkManager.isNetworkConnected(context))
                }

                override fun onError(value: String, url: String?) {
                    Log.e("Discover ERROR", value)
                    if (url != null)
                        Log.d("Login onError url", url)
                    output?.feedback(value)
                    output?.noInternet(NetworkManager.isNetworkConnected(context))
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.info(
                        context,
                        this::class.java.name,
                        "FAILURE, ${throwable.message}",
                        null)
                    output?.feedback(throwable.message)//FIXME
                    output?.noInternet(NetworkManager.isNetworkConnected(context))
                }

                override fun onServerDown() {
                    Logger.info(
                        context,
                        this::class.java.name,
                        "SERVER DOWN",
                        null)
                    output?.feedback(null)
                    output?.noInternet(NetworkManager.isNetworkConnected(context))
                }

                override fun onException(exception: Exception) {
                    Logger.info(
                        context,
                        this::class.java.name,
                        "EXCEPTION",
                        exception)
                    output?.feedback(exception.message)//FIXME
                    output?.noInternet(NetworkManager.isNetworkConnected(context))
                }
            })
    }

    override fun getProduct(context: Context, id: Int) {
        val data: REQUESTAPIProduct =
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
        NetworkManager.requestFactory<APIProduct>(
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
                            null
                        )
                    }
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "ISSUE, Value: $value",
                        null
                    )
                    //TODO: FEEDBACK
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Error, Value: $value",
                        null
                    )
                    //TODO: FEEDBACK
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "FAILURE",
                        throwable
                    )
                    //TODO: FEEDBACK
                }

                override fun onServerDown() {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "SERVER DOWN",
                        null
                    )
                    //TODO: FEEDBACK
                }

                override fun onException(exception: Exception) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "EXCEPTION",
                        exception
                    )
                    //TODO: FEEDBACK
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
                        output?.showSorts(responseObject)
                        Logger.info(
                            context,
                            this::class.java.name,
                            "getSort, SUCCESS",
                            null
                        )
                    }
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "ISSUE, Value: $value",
                        null
                    )
                    //TODO: FEEDBACK
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Error, Value: $value",
                        null
                    )
                    //TODO: FEEDBACK
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "FAILURE",
                        throwable
                    )
                    //TODO: FEEDBACK
                }

                override fun onServerDown() {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "SERVER DOWN",
                        null
                    )
                    //TODO: FEEDBACK
                }

                override fun onException(exception: Exception) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "EXCEPTION",
                        exception
                    )
                    //TODO: FEEDBACK
                }
            }
        )
    }

    //endregion
}