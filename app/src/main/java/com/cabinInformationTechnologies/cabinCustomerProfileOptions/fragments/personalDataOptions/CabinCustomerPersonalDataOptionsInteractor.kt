package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.personalDataOptions

import android.content.Context
import androidx.navigation.NavController
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.*
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.*
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELPersonalInfo
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELPersonalInfos

class CabinCustomerPersonalDataOptionsInteractor(var output: CabinCustomerPersonalDataOptionsContracts.InteractorOutput?) :
    CabinCustomerPersonalDataOptionsContracts.Interactor {

    private val informer: BaseContracts.Feedbacker by lazy {
        Informer()
    }

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun getInitialData(context: Context, navController: NavController) {
        val responseClass = MODELPersonalInfos()
        NetworkManager.requestFactory<APIPersonalInfo>(
            context,
            Constants.LIST_PERSONAL_INFO_URL,
            null,
            null,
            null,
            responseClass,
            null,
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if (value == true) {
                        Logger.verbose(
                            context,
                            this::class.java.name,
                            "SUCCESS: personal information received.",
                            null
                        )
                        output?.setInitialData(responseClass.getPersonalInfos()[0])
                    } else {
                        Logger.warn(
                            context,
                            this::class.java.name,
                            "AMBIGUOUS RESPONSE: ${value.toString()}",
                            null
                        )
                        informer.feedback(
                            context = context,
                            navController = navController,
                            title = context.resources.getString(R.string.error),
                            message = context.resources.getString(R.string.default_error_message)
                        ) { getInitialData(context, navController) }
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
                        navController = navController,
                        title = context.resources.getString(R.string.error),
                        message = value.message
                    ) { getInitialData(context, navController) }
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
                        navController = navController,
                        title = context.resources.getString(R.string.error),
                        message = context.resources.getString(R.string.default_error_message)
                    ) { getInitialData(context, navController) }
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
                            navController = navController,
                            title = context.resources.getString(R.string.error),
                            message = context.resources.getString(R.string.default_error_message)
                        ) { getInitialData(context, navController) }
                    else
                        informer.feedback(
                            context = context,
                            navController = navController,
                            title = context.resources.getString(R.string.attention),
                            message = context.resources.getString(R.string.no_internet)
                        ) { getInitialData(context, navController) }
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
                        navController = navController,
                        title = context.resources.getString(R.string.error),
                        message = context.resources.getString(R.string.default_error_message)
                    ) { getInitialData(context, navController) }
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
                        navController = navController,
                        title = context.resources.getString(R.string.error),
                        message = context.resources.getString(R.string.default_error_message)
                    ) { getInitialData(context, navController) }
                }
            }
        )
    }

    @Suppress("DEPRECATION")
    override fun sendPersonalInfo(context: Context, personalInfo: MODELPersonalInfo) {
        val name = personalInfo.name
        val surname = personalInfo.surname
        val birthyear = personalInfo.birthday?.year
        val birthmonth = personalInfo.birthday?.month
        val birthdate = personalInfo.birthday?.date
        var birthday : String? = null
        if (birthdate != null && birthmonth != null && birthyear != null) {
            birthday =
                "${birthyear}-${birthmonth + 1}-${birthdate}"
        }
        val email = personalInfo.email
        val phone = personalInfo.phone
        val sex = REQUESTGender(personalInfo.sex.getId())

        var data: REQUESTAPIPersonalInfo? = null
        if (name != null && surname != null && birthday != null && email != null && phone != null) {
            data = REQUESTAPIPersonalInfo(
                listOf(
                    REQUESTPersonalInfo(
                        name,
                        surname,
                        birthday,
                        email,
                        phone,
                        listOf(
                            sex
                        )
                    )
                )
            )
        }
        NetworkManager.requestFactory<APIPersonalInfo>(
            context,
            Constants.UPDATE_PERSONAL_INFO_URL,
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
                        "SUCCESS: personal information updated.",
                        null
                    )
                    informer.feedback(
                        context = context,
                        title = context.resources.getString(R.string.congratulations),
                        message = context.resources.getString(R.string.address_saved_successfully),
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
                        title = context.resources.getString(R.string.error),
                        message = value.message,
                        neutralText = context.resources.getString(R.string.okay)
                    ) { sendPersonalInfo(context, personalInfo) }
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
                    ) { sendPersonalInfo(context, personalInfo) }
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
                        ) { sendPersonalInfo(context, personalInfo) }
                    else
                        informer.feedback(
                            context = context,
                            title = context.resources.getString(R.string.attention),
                            message = context.resources.getString(R.string.no_internet),
                            neutralText = context.resources.getString(R.string.okay)
                        ) { sendPersonalInfo(context, personalInfo) }
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
                    ) { sendPersonalInfo(context, personalInfo) }
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
                    ) { sendPersonalInfo(context, personalInfo) }
                }
            }
        )
    }

    //endregion
}