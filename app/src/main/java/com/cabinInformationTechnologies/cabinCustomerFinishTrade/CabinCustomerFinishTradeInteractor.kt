package com.cabinInformationTechnologies.cabinCustomerFinishTrade

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts
import com.cabinInformationTechnologies.cabinCustomerBase.Constants
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAPIWITHORDERID
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTWITHID

class CabinCustomerFinishTradeInteractor(var output: CabinCustomerFinishTradeContracts.InteractorOutput?) :
    CabinCustomerFinishTradeContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun activateOrder(context: Context, orderId: Int) {
        val data = REQUESTAPIWITHORDERID(
            listOf(
                REQUESTWITHID(
                    orderId
                )
            )
        )
        NetworkManager.requestFactory<Any?>(
            context,
            Constants.CART_ACTIVATE_ORDER_URL,
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
                        "Order activated successfully!",
                        null
                    )
                    output?.success()
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Issue while activating order! ${value.message}",
                        null
                    )
                    //TODO: FEEDBACK
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Error while activating order! $value",
                        null
                    )
                    //TODO: FEEDBACK
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Failure while activating order!",
                        throwable
                    )
                    //TODO: FEEDBACK
                }

                override fun onServerDown() {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Server Down while activating order!",
                        null
                    )
                    //TODO: FEEDBACK
                }

                override fun onException(exception: Exception) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Exception while activating order!",
                        exception
                    )
                    //TODO: FEEDBACK
                }

            }
        )
    }

    //endregion
}