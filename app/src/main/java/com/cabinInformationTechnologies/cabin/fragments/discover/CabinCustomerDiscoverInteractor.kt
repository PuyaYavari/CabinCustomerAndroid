package com.cabinInformationTechnologies.cabin.fragments.discover

import android.content.Context
import android.util.Log
import com.squareup.moshi.Moshi

class CabinCustomerDiscoverInteractor(var output: CabinCustomerDiscoverContracts.InteractorOutput?) :
    CabinCustomerDiscoverContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun getProducts(context: Context, page: Int, pageSize: Int) {
        val responseClass = com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProducts()
        val moshi: Moshi = Moshi.Builder()
            .add(com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.JSONProductAdapter(Moshi.Builder().build()))
            .build()
        var products: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct>? = null
        com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager.requestFactory<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIProduct>(
            context,
            com.cabinInformationTechnologies.cabinCustomerBase.Constants.PRODUCT_DETAIL_URL,
            page,
            pageSize,
            null,
            responseClass,
            com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.APIProductAdapter(moshi),
            object : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(this::class.java.name, "SUCCESS, Value: $value", null)
                    if (value == true)
                        products = responseClass.products
                    if (products != null)
                        output?.addData(products)
                    //TODO: ELSE
                }

                override fun onIssue(value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(this::class.java.name, "ISSUE, Value: $value", null)
                    //TODO: SHOW ISSUE
                }

                override fun onError(value: String, url: String?) {
                    Log.e("Discover ERROR", value)
                    if (url != null)
                        Log.d("Login onError url", url)
                    //TODO: SHOW ERROR AND URL
                }

                override fun onFailure(throwable: Throwable) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(this::class.java.name, "FAILURE, ${throwable.message}", null)
                    //TODO: SHOW DEFAULT FAILURE ERROR
                }

                override fun onServerDown() {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(this::class.java.name, "SERVER DOWN", null)
                    //TODO: SHOW DEFAULT FAILURE ERROR
                }

                override fun onException(exception: Exception) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(this::class.java.name, "EXCEPTION", exception)
                    //TODO: HANDLE
                }
        })
    }

    override fun getProduct(context: Context, id: Int) {
        val data: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAPIProduct =
            com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAPIProduct(
                listOf(
                    com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTProduct(
                        id,
                        null,
                        null
                    )
                )
            )
        val responseClass = com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProducts()
        val moshi: Moshi = Moshi.Builder()
            .add(com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.JSONProductAdapter(Moshi.Builder().build()))
            .build()
        com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager.requestFactory<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIProduct>(
            context,
            com.cabinInformationTechnologies.cabinCustomerBase.Constants.PRODUCT_DETAIL_URL,
            null,
            null,
            data,
            responseClass,
            com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.APIProductAdapter(moshi),
            object : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if (value == true) {
                        if (responseClass.products.isNotEmpty())
                            output?.updateProduct(responseClass.products[0])
                        com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(this::class.java.name, "SUCCESS, Value: $value", null)
                    }
                }

                override fun onIssue(value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(this::class.java.name, "ISSUE, Value: $value", null)
                    //TODO: SHOW ISSUE
                }

                override fun onError(value: String, url: String?) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(this::class.java.name, "Error, Value: $value", null)
                }

                override fun onFailure(throwable: Throwable) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(this::class.java.name, "FAILURE", throwable)
                }

                override fun onServerDown() {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(this::class.java.name, "SERVER DOWN", null)
                }

                override fun onException(exception: Exception) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(this::class.java.name, "EXCEPTION", exception)
                }
            }
        )
    }

    //endregion
}