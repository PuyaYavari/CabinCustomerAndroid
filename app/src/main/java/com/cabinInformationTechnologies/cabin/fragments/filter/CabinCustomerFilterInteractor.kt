package com.cabinInformationTechnologies.cabin.fragments.filter

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.Constants
import com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIFilter
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilters

class CabinCustomerFilterInteractor(var output: CabinCustomerFilterContracts.InteractorOutput?) :
    CabinCustomerFilterContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun getFilter(context: Context) {
        val responseObject = MODELFilters()
        NetworkManager.requestFactory<APIFilter?>(
            context,
            Constants.LIST_FILTER_URL,
            null,
            null,
            null,
            responseObject,
            null,
            object : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if (value == true) {
                        val filter = responseObject.getFilters()[0]
                        if (filter != null)
                            output?.setFilter(filter)
                        com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                            context,
                            this::class.java.name,
                            "Filter received.",
                            null)
                    }
                }

                override fun onIssue(value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.failure(
                        context,
                        this::class.java.name,
                        "Filter not received.\n" + "ISSUE: ${value.message}",
                        null)
                }

                override fun onError(value: String, url: String?) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.failure(
                        context,
                        this::class.java.name,
                        "Filter not received.\n" + "ERROR: $value",
                        null)
                }

                override fun onFailure(throwable: Throwable) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                        context,
                        this::class.java.name,
                        "Failure.",
                        throwable)
                }

                override fun onServerDown() {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.failure(
                        context,
                        this::class.java.name,
                        "Server Down.",
                        null)
                }

                override fun onException(exception: Exception) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                        context,
                        this::class.java.name,
                        "Exception.",
                        exception)
                }

            }
        )
    }

    //endregion
}