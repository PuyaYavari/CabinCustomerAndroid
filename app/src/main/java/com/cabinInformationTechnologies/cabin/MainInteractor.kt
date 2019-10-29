package com.cabinInformationTechnologies.cabin

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue

class MainInteractor(var output: MainContracts.InteractorOutput?) :
    MainContracts.Interactor {

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

                override fun onIssue(value: JSONIssue) {
                    Logger.warn(context, this::class.java.name, value.message, null)
                    output?.unableToLogout(value.message)
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(context, this::class.java.name, value, null)
                    output?.unableToLogout(value)
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.error(context, this::class.java.name, "", throwable)
                    output?.unableToLogout(null)
                }

                override fun onServerDown() {
                    Logger.warn(context, this::class.java.name, "Server Down", null)
                    output?.unableToLogout(null)
                }

                override fun onException(exception: Exception) {
                    Logger.error(context, this::class.java.name, "", exception)
                    output?.unableToLogout(null)
                }

            }
        )
    }

    //endregion
}