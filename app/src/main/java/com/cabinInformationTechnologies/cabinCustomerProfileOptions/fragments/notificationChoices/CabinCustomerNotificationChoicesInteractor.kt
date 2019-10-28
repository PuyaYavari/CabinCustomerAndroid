package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.notificationChoices

import android.content.Context

class CabinCustomerNotificationChoicesInteractor(var output: com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.notificationChoices.CabinCustomerNotificationChoicesContracts.InteractorOutput?) :
    com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.notificationChoices.CabinCustomerNotificationChoicesContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun recieveInitialData(context: Context) {
        val responceObject = com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAnnouncementPrefs()
        com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager.requestFactory<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIAnnouncementPref>(
            context,
            com.cabinInformationTechnologies.cabinCustomerBase.Constants.LIST_ANNOUNCEMENT_URL,
            null,
            null,
            null,
            responceObject,
            null,
            object : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.ResponseCallbacks{
                override fun onSuccess(value: Any?) {
                    if (value == true)
                        output?.setPrefs(responceObject)
                    else
                        output?.error(null)
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                        context,
                        this::class.java.name,
                        "value: ${value.toString()}",
                        null)
                }

                override fun onIssue(value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue) {
                    output?.error(value.message)
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                        context,
                        this::class.java.name,
                        "value: $value",
                        null)
                }

                override fun onError(value: String, url: String?) {
                    output?.error(value)
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                        context,
                        this::class.java.name,
                        "value: $value",
                        null)
                }

                override fun onFailure(throwable: Throwable) {
                    output?.error(null)
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                        context,
                        this::class.java.name,
                        "Failure",
                        throwable)
                }

                override fun onServerDown() {
                    output?.error(null)
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                        context,
                        this::class.java.name,
                        "Server Down",
                        null)
                }

                override fun onException(exception: Exception) {
                    output?.error(null)
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                        context,
                        this::class.java.name,
                        "Exception",
                        exception)
                }
            }
        )
    }

    override fun sendPrefs(context: Context, prefs: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAnnouncementPrefs) {
        val data = com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAPIAnnouncementPrefs(
            listOf(
                com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAnnouncemetPref(
                    com.cabinInformationTechnologies.cabinCustomerBase.baseAbstracts.Announcements.EMAIL,
                    prefs.email
                ),
                com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAnnouncemetPref(
                    com.cabinInformationTechnologies.cabinCustomerBase.baseAbstracts.Announcements.SMS,
                    prefs.sms
                ),
                com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAnnouncemetPref(
                    com.cabinInformationTechnologies.cabinCustomerBase.baseAbstracts.Announcements.PHONE,
                    prefs.phone
                )
            )
        )
        com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager.requestFactory<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIAnnouncementPref>(
            context,
            com.cabinInformationTechnologies.cabinCustomerBase.Constants.UPDATE_ANNOUNCEMENT_URL,
            null,
            null,
            data,
            null,
            null,
            object : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                        context,
                        this::class.java.name,
                        "value: ${value.toString()}",
                        null)
                }

                override fun onIssue(value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                        context,
                        this::class.java.name,
                        "value: $value",
                        null)
                }

                override fun onError(value: String, url: String?) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                        context,
                        this::class.java.name,
                        "value: $value",
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
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
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