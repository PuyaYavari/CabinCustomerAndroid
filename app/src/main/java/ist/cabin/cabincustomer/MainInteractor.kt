package ist.cabin.cabincustomer

import android.content.Context
import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerBase.Constants
import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.NetworkManager
import ist.cabin.cabinCustomerBase.models.backend.JSONIssue

class MainInteractor(var output: MainContracts.InteractorOutput?) : MainContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun logout(context: Context) {
        NetworkManager.requestFactory<Any?>(
            context,
            Constants.LOGOUT_URL,
            null,
            null,
            null,//FIXME: WHAT SHOULD I SEND!!!??
            null,
            null,
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    output?.logout()
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.warn(this::class.java.name, value.message, null)
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(this::class.java.name, value, null)
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.error(this::class.java.name, "", throwable)
                }

                override fun onServerDown() {
                    Logger.warn(this::class.java.name, "Server Down", null)
                }

                override fun onException(exception: Exception) {
                    Logger.error(this::class.java.name, "", exception)
                }

            }
        )
    }

    //endregion
}