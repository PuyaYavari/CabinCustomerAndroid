package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.changePassword

import android.content.Context
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.*
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAPIUpdatePassword
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTUpdatePassword

class CabinCustomerChangePasswordInteractor(var output: CabinCustomerChangePasswordContracts.InteractorOutput?) :
    CabinCustomerChangePasswordContracts.Interactor {

    private val informer: BaseContracts.Feedbacker by lazy {
        Informer()
    }

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun sendPasswordData(
        context: Context,
        newPassword: String,
        password: String
    ) {
        val data = REQUESTAPIUpdatePassword(
            listOf(
                REQUESTUpdatePassword(
                    password,
                    newPassword
                )
            )
        )
        NetworkManager.requestFactory<Any?>(
            context,
            Constants.CHANGE_PASSWORD_URL,
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
                        "SUCCESS: password changed.",
                        null
                    )
                    informer.feedback(
                        context = context,
                        title = context.resources.getString(R.string.congratulations),
                        message = context.resources.getString(R.string.password_changed_successfully),
                        neutralText = null,
                        neutralFunction = null,
                        negativeText = null,
                        negativeFunction = null,
                        positiveText = context.resources.getString(R.string.okay),
                        positiveFunction = { output?.success() }
                    )
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
                        title = context.resources.getString(R.string.attention),
                        message = value.message,
                        neutralText = context.resources.getString(R.string.okay)
                    )
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "ERROR: Value: $value, URL: $url",
                        null
                    )
                    informer.feedback(
                        context = context,
                        title = context.resources.getString(R.string.error),
                        message = value,
                        neutralText = context.resources.getString(R.string.okay)
                    ) { sendPasswordData(context, newPassword, password) }
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
                        ) { sendPasswordData(context, newPassword, password) }
                    else
                        informer.feedback(
                            context = context,
                            title = context.resources.getString(R.string.attention),
                            message = context.resources.getString(R.string.no_internet),
                            neutralText = context.resources.getString(R.string.okay)
                        ) { sendPasswordData(context, newPassword, password) }
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
                    ) { sendPasswordData(context, newPassword, password) }
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
                    ) { sendPasswordData(context, newPassword, password) }
                }
            }
        )
    }
    //endregion
}