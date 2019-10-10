package ist.cabin.cabinCustomerProfileOptions.fragments.changePassword

import android.content.Context
import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerBase.Constants
import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.NetworkManager
import ist.cabin.cabinCustomerBase.models.backend.JSONIssue
import ist.cabin.cabinCustomerBase.models.backend.REQUESTAPIUpdatePassword
import ist.cabin.cabinCustomerBase.models.backend.REQUESTUpdatePassword

class CabinCustomerChangePasswordInteractor(var output: CabinCustomerChangePasswordContracts.InteractorOutput?) :
    CabinCustomerChangePasswordContracts.Interactor {

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
            object : BaseContracts.ResponseCallbacks { //TODO: SHOW SUCCESS AND ERROR
                override fun onSuccess(value: Any?) {
                    Logger.info(this::class.java.name, "Success", null)
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.warn(this::class.java.name, "Issue", null)
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(this::class.java.name, "Error", null)
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.error(this::class.java.name, "Failure", throwable)
                }

                override fun onServerDown() {
                    Logger.warn(this::class.java.name, "Server Down", null)
                }

                override fun onException(exception: Exception) {
                    Logger.error(this::class.java.name, "Exception", exception)
                }

            }
        )
    }

    //endregion
}