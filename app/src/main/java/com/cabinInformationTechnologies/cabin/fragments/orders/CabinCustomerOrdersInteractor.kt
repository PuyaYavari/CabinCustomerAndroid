package com.cabinInformationTechnologies.cabin.fragments.orders

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts
import com.cabinInformationTechnologies.cabinCustomerBase.Constants
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIOrders
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELOrders

class CabinCustomerOrdersInteractor(var output: CabinCustomerOrdersContracts.InteractorOutput?) :
    CabinCustomerOrdersContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

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
                    Logger.info(
                        context,
                        this::class.java.name,
                        "list orders response arrived successfully.",
                        null
                    )
                    output?.setOrdersIn(responseObject, adapter)
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        value.message,
                        null
                    )
                    //TODO: FEEDBACK
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        value,
                        null
                    )
                    //TODO: FEEDBACK
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "Failure!",
                        throwable
                    )
                    //TODO: FEEDBACK
                }

                override fun onServerDown() {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "500 response returned.",
                        null
                    )
                    //TODO: FEEDBACK
                }

                override fun onException(exception: Exception) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "Exception!",
                        exception
                    )
                    //TODO: FEEDBACK
                }

            }
        )
    }

    //endregion
}