package ist.cabin.cabinCustomerProfileOptions.fragments.notificationChoices

import android.content.Context
import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerBase.Constants
import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.NetworkManager
import ist.cabin.cabinCustomerBase.baseAbstracts.Announcements
import ist.cabin.cabinCustomerBase.models.backend.APIAnnouncementPref
import ist.cabin.cabinCustomerBase.models.backend.JSONIssue
import ist.cabin.cabinCustomerBase.models.backend.REQUESTAPIAnnouncementPrefs
import ist.cabin.cabinCustomerBase.models.backend.REQUESTAnnouncemetPref
import ist.cabin.cabinCustomerBase.models.local.MODELAnnouncementPrefs

class CabinCustomerNotificationChoicesInteractor(var output: CabinCustomerNotificationChoicesContracts.InteractorOutput?) :
    CabinCustomerNotificationChoicesContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun recieveInitialData(context: Context) {
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
                    if (value == true)
                        output?.setPrefs(responceObject)
                    else
                        output?.error(null)
                    Logger.info(this::class.java.name, "value: ${value.toString()}", null)
                }

                override fun onIssue(value: JSONIssue) {
                    output?.error(value.message)
                    Logger.info(this::class.java.name, "value: $value", null)
                }

                override fun onError(value: String, url: String?) {
                    output?.error(value)
                    Logger.info(this::class.java.name, "value: $value", null)
                }

                override fun onFailure(throwable: Throwable) {
                    output?.error(null)
                    Logger.error(this::class.java.name, "Failure", throwable)
                }

                override fun onServerDown() {
                    output?.error(null)
                    Logger.info(this::class.java.name, "Server Down", null)
                }

                override fun onException(exception: Exception) {
                    output?.error(null)
                    Logger.error(this::class.java.name, "Exception", exception)
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
                    Logger.info(this::class.java.name, "value: ${value.toString()}", null)
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.info(this::class.java.name, "value: $value", null)
                }

                override fun onError(value: String, url: String?) {
                    Logger.info(this::class.java.name, "value: $value", null)
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.error(this::class.java.name, "Failure", throwable)
                }

                override fun onServerDown() {
                    Logger.info(this::class.java.name, "Server Down", null)
                }

                override fun onException(exception: Exception) {
                    Logger.error(this::class.java.name, "Exception", exception)
                }
            }
        )
    }

    //endregion
}