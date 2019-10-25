package com.cabinInformationTechnologies.cabin

import android.content.Context

class MainInteractor(var output: com.cabinInformationTechnologies.cabin.MainContracts.InteractorOutput?) :
    com.cabinInformationTechnologies.cabin.MainContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun logout(context: Context) {
        com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager.requestFactory<Any?>(
            context,
            com.cabinInformationTechnologies.cabinCustomerBase.Constants.LOGOUT_URL,
            null,
            null,
            null,//FIXME: WHAT SHOULD I SEND!!!??
            null,
            null,
            object : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    output?.logout()
                }

                override fun onIssue(value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(this::class.java.name, value.message, null)
                }

                override fun onError(value: String, url: String?) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(this::class.java.name, value, null)
                }

                override fun onFailure(throwable: Throwable) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(this::class.java.name, "", throwable)
                }

                override fun onServerDown() {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(this::class.java.name, "Server Down", null)
                }

                override fun onException(exception: Exception) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(this::class.java.name, "", exception)
                }

            }
        )
    }

    //endregion
}