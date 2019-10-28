package com.cabinInformationTechnologies.cabin.fragments.productDetail

import android.content.Context
import com.cabinInformationTechnologies.cabin.R
import com.squareup.moshi.Moshi

class CabinCustomerProductDetailInteractor(var output: CabinCustomerProductDetailContracts.InteractorOutput?) :
    CabinCustomerProductDetailContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun addToCart(context: Context,
                           productId: Int,
                           amount: Int,
                           colorId: Int,
                           sizeId: Int
    ){
        val data: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAPIProduct? =
            com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAPIProduct(
                listOf(
                    com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTProduct(
                        productId,
                        amount,
                        listOf(
                            com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTColor(
                                colorId,
                                listOf(com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTSize(sizeId))
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
                    output?.showMessage(null)
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                        context,
                        this::class.java.name,
                        "SUCCESS, Value: $value",
                        null)
                }

                override fun onIssue(value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue) {
                    output?.showMessage(value.message)
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
                    output?.showMessage(value)
                    //TODO: SHOW ERROR AND URL
                }

                override fun onFailure(throwable: Throwable) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                        context,
                        this::class.java.name,
                        "FAILURE",
                        throwable)
                    //TODO: SHOW DEFAULT FAILURE ERROR
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                }

                override fun onServerDown() {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(
                        context,
                        this::class.java.name,
                        "SERVER DOWN",
                        null)
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                }

                override fun onException(exception: Exception) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
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
            .add(com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.JSONProductAdapter(context,Moshi.Builder().build()))
            .build()
        com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager.requestFactory(
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
                        com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                            context,
                            this::class.java.name,
                            "SUCCESS, Value: $value",
                            null)
                    }
                }

                override fun onIssue(value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue) {
                    output?.showMessage(value.message) //TODO: HANDLE
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
                    output?.showMessage(value)
                    //TODO: SHOW ERROR AND URL
                }

                override fun onFailure(throwable: Throwable) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                        context,
                        this::class.java.name,
                        "FAILURE",
                        throwable)
                    //TODO: SHOW DEFAULT FAILURE ERROR
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                }

                override fun onServerDown() {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(
                        context,
                        this::class.java.name,
                        "SERVER DOWN",
                        null)
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                }

                override fun onException(exception: Exception) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
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

    override fun addFavorite(context: Context, product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct, color: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor) {
        val data = com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAPIProduct(
            listOf(
                com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTProduct(
                    product.getId(),
                    null,
                    listOf(
                        com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTColor(
                            color.id,
                            null
                        )
                    )
                )
            )
        )
        com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager.requestFactory<Any?>(
            context,
            com.cabinInformationTechnologies.cabinCustomerBase.Constants.DISCOVER_ADD_TO_FAVORITE_URL,
            null,
            null,
            data,
            null,
            null,
            object : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.ResponseCallbacks{
                override fun onSuccess(value: Any?) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                        context,
                        this::class.java.name,
                        "SUCCESS, Value: $value",
                        null)
                }

                override fun onIssue(value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue) {
                    output?.showMessage(value.message) //TODO: HANDLE
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
                    output?.showMessage(value)
                    //TODO: SHOW ERROR AND URL
                }

                override fun onFailure(throwable: Throwable) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                        context,
                        this::class.java.name,
                        "FAILURE",
                        throwable)
                    //TODO: SHOW DEFAULT FAILURE ERROR
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                }

                override fun onServerDown() {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(
                        context,
                        this::class.java.name,
                        "SERVER DOWN",
                        null)
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                }

                override fun onException(exception: Exception) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
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

    override fun removeFavorite(context: Context, product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct, color: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor) {
        val data = com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAPIProduct(
            listOf(
                com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTProduct(
                    product.getId(),
                    null,
                    listOf(
                        com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTColor(
                            color.id,
                            null
                        )
                    )
                )
            )
        )
        com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager.requestFactory<Any?>(
            context,
            com.cabinInformationTechnologies.cabinCustomerBase.Constants.DISCOVER_REMOVE_FROM_FAVORITE_URL,
            null,
            null,
            data,
            null,
            null,
            object : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.ResponseCallbacks{
                override fun onSuccess(value: Any?) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                        context,
                        this::class.java.name,
                        "SUCCESS, Value: $value",
                        null)
                }

                override fun onIssue(value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue) {
                    output?.showMessage(value.message) //TODO: HANDLE
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
                    output?.showMessage(value)
                    //TODO: SHOW ERROR AND URL
                }

                override fun onFailure(throwable: Throwable) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                        context,
                        this::class.java.name,
                        "FAILURE",
                        throwable)
                    //TODO: SHOW DEFAULT FAILURE ERROR
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                }

                override fun onServerDown() {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(
                        context,
                        this::class.java.name,
                        "SERVER DOWN",
                        null)
                    output?.showMessage(context.resources.getString(R.string.a_problem_occurred))
                }

                override fun onException(exception: Exception) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
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

    //endregion
}