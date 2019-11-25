package com.cabinInformationTechnologies.cabin.fragments.home

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts
import com.cabinInformationTechnologies.cabinCustomerBase.Constants
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIHeader
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELHeaders

class CabinCustomerHomeInteractor(var output: CabinCustomerHomeContracts.InteractorOutput?) :
    CabinCustomerHomeContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun getHeaders(context: Context) {
        val responseObject = MODELHeaders()
        NetworkManager.requestFactory<APIHeader>(
            context,
            Constants.LIST_BANNERS_URL,
            null,
            null,
            null,
            responseObject,
            null,
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if (value == true) {
                        Logger.info(
                            context,
                            this::class.java.name,
                            "Header arrived.",
                            null
                        )
                        output?.setHeaderData(responseObject)
                    }
                }

                override fun onIssue(value: JSONIssue) {

                }

                override fun onError(value: String, url: String?) {

                }

                override fun onFailure(throwable: Throwable) {

                }

                override fun onServerDown() {

                }

                override fun onException(exception: Exception) {

                }

            }
        )
    }

    //endregion
}