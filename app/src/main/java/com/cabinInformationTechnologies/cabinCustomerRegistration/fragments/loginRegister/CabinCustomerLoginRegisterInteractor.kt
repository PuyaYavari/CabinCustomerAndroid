package com.cabinInformationTechnologies.cabinCustomerRegistration.fragments.loginRegister

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts
import com.cabinInformationTechnologies.cabinCustomerBase.Constants
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.*
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELUsers
import com.google.android.gms.auth.api.signin.GoogleSignInAccount


class CabinCustomerLoginRegisterInteractor(var output: CabinCustomerLoginRegisterContracts.InteractorOutput?) :
    CabinCustomerLoginRegisterContracts.Interactor {



    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun login(context: Context, email: String, password: String){
        val responseClass = MODELUsers()
        val data = REQUESTAPILogin(
            listOf(
                REQUESTLogin(
                    email,
                    password
                )
            )
        )
        NetworkManager.requestFactory<APIUser>(
            context,
            Constants.LOGIN_URL,
            null,
            null,
            data,
            responseClass,
            null,
            object :
                BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if (value == true && responseClass.users.size != 0) {
                        output?.setActiveUser(responseClass.users[0])
                        output?.closeActivity()
                    }
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        value.message,
                        null
                    )
                    //TODO: FEEDBACK
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        value,
                        null
                    )
                    //TODO: FEEDBACK
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Login OnFailure",
                        throwable
                    )
                    //TODO: FEEDBACK
                }

                override fun onServerDown() {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Server Down",
                        null
                    )
                    //TODO: FEEDBACK
                }

                override fun onException(exception: Exception) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "",
                        exception
                    )
                    //TODO: FEEDBACK
                }
            }
        )
    }

    override fun login(context: Context, account: GoogleSignInAccount) {
        val responseClass = MODELUsers()
        val userId = account.id
        val userEmail = account.email
        if (userId != null && userEmail != null) {
            val data = REQUESTAPIGoogleLogin(
                listOf(
                    REQUESTGoogleLogin(
                        userId,
                        userEmail,
                        account.displayName,
                        account.familyName
                    )
                )
            )
            NetworkManager.requestFactory<APIUser>(
                context,
                Constants.GOOGLE_LOGIN_URL,
                null,
                null,
                data,
                responseClass,
                null,
                object :
                    BaseContracts.ResponseCallbacks {
                    override fun onSuccess(value: Any?) {
                        if (value == true && responseClass.users.size != 0) {
                            output?.setActiveUser(responseClass.users[0])
                            output?.closeActivity()
                        }
                    }

                    override fun onIssue(value: JSONIssue) {
                        Logger.warn(
                            context,
                            this::class.java.name,
                            value.message,
                            null
                        )
                        //TODO: FEEDBACK
                    }

                    override fun onError(value: String, url: String?) {
                        Logger.warn(
                            context,
                            this::class.java.name,
                            value,
                            null
                        )
                        //TODO: FEEDBACK
                    }

                    override fun onFailure(throwable: Throwable) {
                        Logger.warn(
                            context,
                            this::class.java.name,
                            "Google Login OnFailure",
                            throwable
                        )
                        //TODO: FEEDBACK
                    }

                    override fun onServerDown() {
                        Logger.warn(
                            context,
                            this::class.java.name,
                            "Server Down",
                            null
                        )
                        //TODO: FEEDBACK
                    }

                    override fun onException(exception: Exception) {
                        Logger.error(
                            context,
                            this::class.java.name,
                            "",
                            exception
                        )
                        //TODO: FEEDBACK
                    }
                }
            )
        }
    }

    override fun register(
        context: Context,
        email: String,
        password: String,
        sex: Int,
        emailPermit: Boolean
    ) {
        val data = REQUESTAPIRegister(
            listOf(
                REQUESTRegister(
                    com.cabinInformationTechnologies.cabinCustomerBase.GlobalData.userId,
                    email,
                    password,
                    emailPermit,
                    REQUESTGender(sex)
                )
            )
        )

        NetworkManager.requestFactory<Any?>(
            context,
            Constants.REGISTER_URL,
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
                        "SUCCESS",
                        null)
                    output?.sendLoginRequest()
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "ISSUE",
                        null)
                    //TODO
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "ERROR",
                        null)
                    //TODO
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "FAILURE",
                        throwable)
                    //TODO
                }

                override fun onServerDown() {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "SERVER DOWN",
                        null)
                    //TODO
                }

                override fun onException(exception: Exception) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "Exception",
                        exception)
                    //TODO
                }

            }
        )
    }
    //endregion
}