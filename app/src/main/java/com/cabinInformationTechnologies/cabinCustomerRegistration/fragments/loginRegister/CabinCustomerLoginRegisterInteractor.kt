package com.cabinInformationTechnologies.cabinCustomerRegistration.fragments.loginRegister

import android.content.Context
import android.util.Log


class CabinCustomerLoginRegisterInteractor(var output: com.cabinInformationTechnologies.cabinCustomerRegistration.fragments.loginRegister.CabinCustomerLoginRegisterContracts.InteractorOutput?) :
    com.cabinInformationTechnologies.cabinCustomerRegistration.fragments.loginRegister.CabinCustomerLoginRegisterContracts.Interactor {



    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun login(context: Context, email: String, password: String){
        val responseClass = com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELUsers()
        val data = com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAPILogin(
            listOf(
                com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTLogin(
                    email,
                    password
                )
            )
        )
        com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager.requestFactory<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIUser>(
            context ,
            com.cabinInformationTechnologies.cabinCustomerBase.Constants.LOGIN_URL,
            null,
            null,
            data,
            responseClass,
            null,
            object : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if (value == true && responseClass.users.size != 0) {
                        output?.setActiveUser(responseClass.users[0])
                        output?.closeActivity()
                    }
                }

                override fun onIssue(value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(this::class.java.name, value.message, null)
                }

                override fun onError(value: String, url: String?) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(this::class.java.name, value, null)
                    if (url != null)
                        com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(this::class.java.name, url, null)
                }

                override fun onFailure(throwable: Throwable) {
                    Log.d("Login OnFailure", throwable.message.toString())
                    //TODO
                }

                override fun onServerDown() {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(this::class.java.name, "Server Down", null)
                    //TODO
                }

                override fun onException(exception: Exception) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(this::class.java.name, "", exception)
                }
            }
        )
    }

    override fun register(
        context: Context,
        email: String,
        password: String,
        sex: Int,
        emailPermit: Boolean
    ) {
        val data = com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAPIRegister(
            listOf(
                com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTRegister(
                    com.cabinInformationTechnologies.cabinCustomerBase.GlobalData.userId,
                    email,
                    password,
                    emailPermit,
                    com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTGender(sex)
                )
            )
        )

        com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager.requestFactory<Any?>(
            context,
            com.cabinInformationTechnologies.cabinCustomerBase.Constants.REGISTER_URL,
            null,
            null,
            data,
            null,
            null,
            object : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(this::class.java.name, "SUCCESS", null)
                    output?.sendLoginRequest()
                }

                override fun onIssue(value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(this::class.java.name, "ISSUE", null)
                    //TODO
                }

                override fun onError(value: String, url: String?) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(this::class.java.name, "ERROR", null)
                    //TODO
                }

                override fun onFailure(throwable: Throwable) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(this::class.java.name, "FAILURE", throwable)
                    //TODO
                }

                override fun onServerDown() {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(this::class.java.name, "SERVER DOWN", null)
                    //TODO
                }

                override fun onException(exception: Exception) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(this::class.java.name, "Exception", exception)
                    //TODO
                }

            }
        )
    }
    //endregion
}