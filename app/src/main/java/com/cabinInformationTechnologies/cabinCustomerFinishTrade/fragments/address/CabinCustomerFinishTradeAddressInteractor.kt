package com.cabinInformationTechnologies.cabinCustomerFinishTrade.fragments.address

import android.content.Context
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts
import com.cabinInformationTechnologies.cabinCustomerBase.Constants
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager
import com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.APIAddressAdapter
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIAddress
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddresses
import com.squareup.moshi.Moshi

class CabinCustomerFinishTradeAddressInteractor(var output: CabinCustomerFinishTradeAddressContracts.InteractorOutput?) :
    CabinCustomerFinishTradeAddressContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun getAddresses(context: Context) {
        val responseObject = MODELAddresses()
        NetworkManager.requestFactory<APIAddress>(
            context,
           Constants.LIST_ADDRESSES_URL,
            null,
            null,
            null,
            responseObject,
            APIAddressAdapter(
                context,
                Moshi.Builder().build()
            ),
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if (value == true)
                        output?.setAddresses(responseObject)
                    Logger.info(
                        context,
                        this::class.java.name,
                        "Success, value: ${value.toString()}",
                        null
                    )
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Issue, value: ${value.message}",
                        null
                    )
                    output?.feedback(value.message)
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Error, value: $value",
                        null
                    )
                    output?.feedback(null)
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "Failure",
                        throwable
                    )
                    if (NetworkManager.isNetworkConnected(context))
                        output?.feedback(null)
                    else
                        output?.feedback(context.resources.getString(R.string.no_internet))
                }

                override fun onServerDown() {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Server Down",
                        null
                    )
                    output?.feedback(null)
                }

                override fun onException(exception: Exception) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "Exception",
                        exception
                    )
                    output?.feedback(null)
                }

            }
        )
    }

    //endregion
}