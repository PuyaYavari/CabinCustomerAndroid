package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.notificationChoices

import android.content.Context
import androidx.navigation.NavController
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.*
import com.cabinInformationTechnologies.cabinCustomerBase.baseAbstracts.Announcements
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIAnnouncementPref
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAPIAnnouncementPrefs
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAnnouncemetPref
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAnnouncementPrefs

class CabinCustomerNotificationChoicesInteractor(var output: CabinCustomerNotificationChoicesContracts.InteractorOutput?) :
    CabinCustomerNotificationChoicesContracts.Interactor {

    private val informer: BaseContracts.Feedbacker by lazy {
        Informer()
    }

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun receiveInitialData(context: Context, navController: NavController) {
        val responceObject = MODELAnnouncementPrefs()
        NetworkManager.requestFactory<APIAnnouncementPref>(
            context,
            Constants.LIST_ANNOUNCEMENT_URL,
            null,
            null,
            null,
            responceObject,
            null,
            object : BaseContracts.ResponseCallbacks{
                override fun onSuccess(value: Any?) {
                    if (value == true) {
                        Logger.verbose(
                            context,
                            this::class.java.name,
                            "SUCCESS: announcements received.",
                            null
                        )
                        output?.setPrefs(responceObject)
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
                        ) { receiveInitialData(context, navController) }
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
                    ) { receiveInitialData(context, navController) }
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
                        navController = navController,
                        title = context.resources.getString(R.string.error),
                        message = value
                    ) { receiveInitialData(context, navController) }
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
                        ) { receiveInitialData(context, navController) }
                    else
                        informer.feedback(
                            context = context,
                            navController = navController,
                            title = context.resources.getString(R.string.attention),
                            message = context.resources.getString(R.string.no_internet)
                        ) { receiveInitialData(context, navController) }
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
                    ) { receiveInitialData(context, navController) }
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
                    ) { receiveInitialData(context, navController) }
                }
            }
        )
    }

    override fun sendPrefs(context: Context, prefs: MODELAnnouncementPrefs) {
        val data = REQUESTAPIAnnouncementPrefs(
            listOf(
                REQUESTAnnouncemetPref(
                    Announcements.EMAIL,
                    prefs.email
                ),
                REQUESTAnnouncemetPref(
                    Announcements.SMS,
                    prefs.sms
                ),
                REQUESTAnnouncemetPref(
                    Announcements.PHONE,
                    prefs.phone
                )
            )
        )
        NetworkManager.requestFactory<APIAnnouncementPref>(
            context,
            Constants.UPDATE_ANNOUNCEMENT_URL,
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
                        "SUCCESS: announcements updated.",
                        null
                    )
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "ISSUE: ${value.message}",
                        null
                    )
                    informer.feedback(context, value.message)
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "ERROR: Value: $value, URL: $url",
                        null
                    )
                    informer.feedback(context, value)
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.verbose(
                        context,
                        this::class.java.name,
                        "FAILURE",
                        throwable
                    )
                    informer.feedback(context, context.resources.getString(R.string.default_error_message))
                }

                override fun onServerDown() {
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "SERVER DOWN!!",
                        null
                    )
                    informer.feedback(context, context.resources.getString(R.string.default_error_message))
                }

                override fun onException(exception: Exception) {
                    Logger.error(
                        context,
                        this::class.java.name,
                        "EXCEPTION",
                        exception
                    )
                    informer.feedback(context, context.resources.getString(R.string.default_error_message))
                }
            }
        )
    }
    //endregion
}