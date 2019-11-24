package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.overview

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts
import com.cabinInformationTechnologies.cabinCustomerBase.Constants
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIAgreements
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAPIWITHORDERID
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTWITHID
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAgreements

class CabinCustomerFinishTradeOverviewInteractor(var output: CabinCustomerFinishTradeOverviewContracts.InteractorOutput?) :
    CabinCustomerFinishTradeOverviewContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun listAgreements(context: Context, orderId: Int) {
        val responseObject = MODELAgreements()
        val data = REQUESTAPIWITHORDERID (
            listOf(
                REQUESTWITHID(
                    orderId
                )
            )
        )
        NetworkManager.requestFactory<APIAgreements>(
            context,
            Constants.CART_LIST_ORDER_AGREEMENT_URL,
            null,
            null,
            data,
            responseObject,
            null,
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if (value == true) {
                        Logger.info(
                            context,
                            this::class.java.name,
                            "Agreements arrived successfully!",
                            null
                        )
                        output?.setAgreements(responseObject)
                    } else {
                        //TODO: HANDLE
                    }
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Issue while requesting agreements! ${value.message}",
                        null
                    )
                    //TODO: FEEDBACK
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Error while requesting agreements! $value",
                        null
                    )
                    //TODO: FEEDBACK
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Failure while requesting agreements!",
                        throwable
                    )
                    //TODO: FEEDBACK
                }

                override fun onServerDown() {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Server Down while requesting agreements!",
                        null
                    )
                    //TODO: FEEDBACK
                }

                override fun onException(exception: Exception) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Exception while requesting agreements!",
                        exception
                    )
                    //TODO: FEEDBACK
                }

            }
        )
    }

    //endregion
}