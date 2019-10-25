package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions

import android.content.Context
import com.squareup.moshi.Moshi

class CabinCustomerAddressOptionsInteractor(var output: com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions.CabinCustomerAddressOptionsContracts.InteractorOutput?) :
    com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.addressOptions.CabinCustomerAddressOptionsContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun getAddresses(context: Context) {
        val responseObject = com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddresses()
        com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager.requestFactory<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIAddress>(
            context,
            com.cabinInformationTechnologies.cabinCustomerBase.Constants.LIST_ADDRESSES_URL,
            null,
            null,
            null,
            responseObject,
            com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.APIAddressAdapter(Moshi.Builder().build()),
            object : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if (value == true)
                        output?.setAddresses(responseObject)
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(this::class.java.name, "Success, value: ${value.toString()}", null)
                }

                override fun onIssue(value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(this::class.java.name, "Issue, value: ${value.message}", null)
                }

                override fun onError(value: String, url: String?) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(this::class.java.name, "Error, value: $value", null)
                }

                override fun onFailure(throwable: Throwable) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(this::class.java.name, "Failure", throwable)
                }

                override fun onServerDown() {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(this::class.java.name, "Server Down", null)
                }

                override fun onException(exception: Exception) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(this::class.java.name, "Exception", exception)
                }

            }
        )
    }

    override fun removeAddress(context: Context, address: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress) {
        var data: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAPIRemoveAddress? = null
        val id = address.id
        if (id != null)
            data = com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAPIRemoveAddress(
                listOf(
                    com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTRemoveAddress(
                        id
                    )
                )
            )
        if (data != null)
            com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager.requestFactory<Any?>(
                context,
                com.cabinInformationTechnologies.cabinCustomerBase.Constants.REMOVE_ADDRESS_URL,
                null,
                null,
                data,
                null,
                null,
                object : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.ResponseCallbacks {
                    override fun onSuccess(value: Any?) {
                        com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(this::class.java.name, "Success, value: ${value.toString()}", null)
                        output?.addressRemovedFeedback(true, null)//TODO: FEEDBACK
                    }

                    override fun onIssue(value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue) {
                        com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(this::class.java.name, "Issue, value: ${value.message}", null)
                        output?.addressRemovedFeedback(false, null)//TODO: FEEDBACK
                    }

                    override fun onError(value: String, url: String?) {
                        com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(this::class.java.name, "Error, value: $value", null)
                        output?.addressRemovedFeedback(false, null)//TODO: FEEDBACK
                    }

                    override fun onFailure(throwable: Throwable) {
                        com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(this::class.java.name, "Failure", throwable)
                        output?.addressRemovedFeedback(false, null)//TODO: FEEDBACK
                    }

                    override fun onServerDown() {
                        com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(this::class.java.name, "Server Down", null)
                        output?.addressRemovedFeedback(false, null)//TODO: FEEDBACK
                    }

                    override fun onException(exception: Exception) {
                        com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(this::class.java.name, "Exception", exception)
                        output?.addressRemovedFeedback(false, null)//TODO: FEEDBACK
                    }

                }
            )
    }

    //endregion
}