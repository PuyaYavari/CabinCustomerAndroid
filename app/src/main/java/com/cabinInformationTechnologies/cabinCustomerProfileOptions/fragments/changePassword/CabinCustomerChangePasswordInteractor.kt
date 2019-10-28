package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.changePassword

import android.content.Context

class CabinCustomerChangePasswordInteractor(var output: com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.changePassword.CabinCustomerChangePasswordContracts.InteractorOutput?) :
    com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.changePassword.CabinCustomerChangePasswordContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun sendPasswordData(
        context: Context,
        newPassword: String,
        password: String
    ) {
        val data = com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAPIUpdatePassword(
            listOf(
                com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTUpdatePassword(
                    password,
                    newPassword
                )
            )
        )
        com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager.requestFactory<Any?>(
            context,
            com.cabinInformationTechnologies.cabinCustomerBase.Constants.CHANGE_PASSWORD_URL,
            null,
            null,
            data,
            null,
            null,
            object : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.ResponseCallbacks { //TODO: SHOW SUCCESS AND ERROR
                override fun onSuccess(value: Any?) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                        context,
                        this::class.java.name,
                        "Success",
                        null)
                }

                override fun onIssue(value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(
                        context,
                        this::class.java.name,
                        "Issue",
                        null)
                }

                override fun onError(value: String, url: String?) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(
                        context,
                        this::class.java.name,
                        "Error",
                        null)
                }

                override fun onFailure(throwable: Throwable) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                        context,
                        this::class.java.name,
                        "Failure",
                        throwable)
                }

                override fun onServerDown() {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(
                        context,
                        this::class.java.name,
                        "Server Down",
                        null)
                }

                override fun onException(exception: Exception) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                        context,
                        this::class.java.name,
                        "Exception",
                        exception)
                }

            }
        )
    }

    //endregion
}