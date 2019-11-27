package com.cabinInformationTechnologies.cabinCustomerRegistration.fragments.loginRegister

import android.content.Context
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.*
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.*
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELUsers
import com.google.android.gms.auth.api.signin.GoogleSignInAccount


class CabinCustomerLoginRegisterInteractor(var output: CabinCustomerLoginRegisterContracts.InteractorOutput?) :
    CabinCustomerLoginRegisterContracts.Interactor {

    private val informer: BaseContracts.Feedbacker by lazy {
        Informer()
    }

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
                        Logger.verbose(
                            context,
                            this::class.java.name,
                            "SUCCESS: login",
                            null
                        )
                        output?.setActiveUser(responseClass.users[0])
                        output?.closeActivity()
                    } else {
                        Logger.warn(
                            context,
                            this::class.java.name,
                            "AMBIGUOUS RESPONSE: ${value.toString()}",
                            null
                        )
                        informer.feedback(
                            context = context,
                            title = context.resources.getString(R.string.error),
                            message = context.resources.getString(R.string.default_error_message),
                            neutralText = context.resources.getString(R.string.okay)
                        ) { login(context, email, password) }
                    }
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "ISSUE: ${value.message}",
                        null
                    )
                    informer.feedback(
                        context = context,
                        title = context.resources.getString(R.string.error),
                        message = value.message,
                        neutralText = context.resources.getString(R.string.okay)
                    ) { login(context, email, password) }
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Error, Value: $value, URL: $url",
                        null
                    )
                    informer.feedback(
                        context = context,
                        title = context.resources.getString(R.string.error),
                        message = context.resources.getString(R.string.default_error_message),
                        neutralText = context.resources.getString(R.string.okay)
                    ) { login(context, email, password) }
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "FAILURE",
                        throwable
                    )
                    if (NetworkManager.isNetworkConnected(context))
                        informer.feedback(
                            context = context,
                            title = context.resources.getString(R.string.error),
                            message = context.resources.getString(R.string.default_error_message),
                            neutralText = context.resources.getString(R.string.okay)
                        ) { login(context, email, password) }
                    else
                        informer.feedback(
                            context = context,
                            title = context.resources.getString(R.string.attention),
                            message = context.resources.getString(R.string.no_internet),
                            neutralText = context.resources.getString(R.string.okay)
                        ) { login(context, email, password) }
                }

                override fun onServerDown() {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "SERVER DOWN!!",
                        null
                    )
                    informer.feedback(
                        context = context,
                        title = context.resources.getString(R.string.error),
                        message = context.resources.getString(R.string.default_error_message),
                        neutralText = context.resources.getString(R.string.okay)
                    ) { login(context, email, password) }
                }

                override fun onException(exception: Exception) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "EXCEPTION",
                        exception
                    )
                    informer.feedback(
                        context = context,
                        title = context.resources.getString(R.string.error),
                        message = context.resources.getString(R.string.default_error_message),
                        neutralText = context.resources.getString(R.string.okay)
                    ) { login(context, email, password) }
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
                            Logger.verbose(
                                context,
                                this::class.java.name,
                                "SUCCESS: google login.",
                                null
                            )
                            output?.setActiveUser(responseClass.users[0])
                            output?.closeActivity()
                        } else {
                            Logger.warn(
                                context,
                                this::class.java.name,
                                "AMBIGUOUS RESPONSE: ${value.toString()}",
                                null
                            )
                            informer.feedback(
                                context = context,
                                title = context.resources.getString(R.string.error),
                                message = context.resources.getString(R.string.default_error_message),
                                neutralText = context.resources.getString(R.string.okay)
                            ) { login(context, account) }
                        }
                    }

                    override fun onIssue(value: JSONIssue) {
                        Logger.verbose(
                            context,
                            this::class.java.name,
                            "ISSUE: ${value.message}",
                            null
                        )
                        informer.feedback(
                            context = context,
                            title = context.resources.getString(R.string.error),
                            message = value.message,
                            neutralText = context.resources.getString(R.string.okay)
                        ) { login(context, account) }
                    }

                    override fun onError(value: String, url: String?) {
                        Logger.warn(
                            context,
                            this::class.java.name,
                            "Error, Value: $value, URL: $url",
                            null
                        )
                        informer.feedback(
                            context = context,
                            title = context.resources.getString(R.string.error),
                            message = context.resources.getString(R.string.default_error_message),
                            neutralText = context.resources.getString(R.string.okay)
                        ) { login(context, account) }
                    }

                    override fun onFailure(throwable: Throwable) {
                        Logger.verbose(
                            context,
                            this::class.java.name,
                            "FAILURE",
                            throwable
                        )
                        if (NetworkManager.isNetworkConnected(context))
                            informer.feedback(
                                context = context,
                                title = context.resources.getString(R.string.error),
                                message = context.resources.getString(R.string.default_error_message),
                                neutralText = context.resources.getString(R.string.okay)
                            ) { login(context, account) }
                        else
                            informer.feedback(
                                context = context,
                                title = context.resources.getString(R.string.attention),
                                message = context.resources.getString(R.string.no_internet),
                                neutralText = context.resources.getString(R.string.okay)
                            ) { login(context, account) }
                    }

                    override fun onServerDown() {
                        Logger.warn(
                            context,
                            this::class.java.name,
                            "SERVER DOWN!!",
                            null
                        )
                        informer.feedback(
                            context = context,
                            title = context.resources.getString(R.string.error),
                            message = context.resources.getString(R.string.default_error_message),
                            neutralText = context.resources.getString(R.string.okay)
                        ) { login(context, account) }
                    }

                    override fun onException(exception: Exception) {
                        Logger.error(
                            context,
                            this::class.java.name,
                            "EXCEPTION",
                            exception
                        )
                        informer.feedback(
                            context = context,
                            title = context.resources.getString(R.string.error),
                            message = context.resources.getString(R.string.default_error_message),
                            neutralText = context.resources.getString(R.string.okay)
                        ) { login(context, account) }
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
                    GlobalData.userId,
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
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "SUCCESS: registered.",
                        null
                    )
                    output?.sendLoginRequest()
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "ISSUE: ${value.message}",
                        null
                    )
                    informer.feedback(
                        context = context,
                        title = context.resources.getString(R.string.error),
                        message = value.message,
                        neutralText = context.resources.getString(R.string.okay)
                    ) { register(context, email, password, sex, emailPermit) }
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "Error, Value: $value, URL: $url",
                        null
                    )
                    informer.feedback(
                        context = context,
                        title = context.resources.getString(R.string.error),
                        message = context.resources.getString(R.string.default_error_message),
                        neutralText = context.resources.getString(R.string.okay)
                    ) { register(context, email, password, sex, emailPermit) }
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "FAILURE",
                        throwable
                    )
                    if (NetworkManager.isNetworkConnected(context))
                        informer.feedback(
                            context = context,
                            title = context.resources.getString(R.string.error),
                            message = context.resources.getString(R.string.default_error_message),
                            neutralText = context.resources.getString(R.string.okay)
                        ) { register(context, email, password, sex, emailPermit) }
                    else
                        informer.feedback(
                            context = context,
                            title = context.resources.getString(R.string.attention),
                            message = context.resources.getString(R.string.no_internet),
                            neutralText = context.resources.getString(R.string.okay)
                        ) { register(context, email, password, sex, emailPermit) }
                }

                override fun onServerDown() {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "SERVER DOWN!!",
                        null
                    )
                    informer.feedback(
                        context = context,
                        title = context.resources.getString(R.string.error),
                        message = context.resources.getString(R.string.default_error_message),
                        neutralText = context.resources.getString(R.string.okay)
                    ) { register(context, email, password, sex, emailPermit) }
                }

                override fun onException(exception: Exception) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "EXCEPTION",
                        exception
                    )
                    informer.feedback(
                        context = context,
                        title = context.resources.getString(R.string.error),
                        message = context.resources.getString(R.string.default_error_message),
                        neutralText = context.resources.getString(R.string.okay)
                    ) { register(context, email, password, sex, emailPermit) }
                }

            }
        )
    }
    //endregion
}