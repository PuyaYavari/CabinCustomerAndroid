package ist.cabin.cabinCustomerRegistration.fragments.loginRegister

import android.content.Context
import android.util.Log
import ist.cabin.cabinCustomerBase.*
import ist.cabin.cabinCustomerBase.models.backend.*
import ist.cabin.cabinCustomerBase.models.local.MODELUsers


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
            context ,
            Constants.LOGIN_URL,
            null,
            null,
            data,
            responseClass,
            null,
            object : BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    if (value == true && responseClass.users.size != 0) {
                        output?.setActiveUser(responseClass.users[0])
                        output?.closeActivity()
                    }
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.info(this::class.java.name, value.message, null)
                }

                override fun onError(value: String, url: String?) {
                    Logger.info(this::class.java.name, value, null)
                    if (url != null)
                        Logger.info(this::class.java.name, url, null)
                }

                override fun onFailure(throwable: Throwable) {
                    Log.d("Login OnFailure", throwable.message.toString())
                    //TODO
                }

                override fun onServerDown() {
                    Logger.info(this::class.java.name, "Server Down", null)
                    //TODO
                }

                override fun onException(exception: Exception) {
                    Logger.error(this::class.java.name, "", exception)
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
                    Logger.info(this::class.java.name, "SUCCESS", null)
                    output?.sendLoginRequest()
                }

                override fun onIssue(value: JSONIssue) {
                    Logger.warn(this::class.java.name, "ISSUE", null)
                    //TODO
                }

                override fun onError(value: String, url: String?) {
                    Logger.warn(this::class.java.name, "ERROR", null)
                    //TODO
                }

                override fun onFailure(throwable: Throwable) {
                    Logger.error(this::class.java.name, "FAILURE", throwable)
                    //TODO
                }

                override fun onServerDown() {
                    Logger.warn(this::class.java.name, "SERVER DOWN", null)
                    //TODO
                }

                override fun onException(exception: Exception) {
                    Logger.error(this::class.java.name, "Exception", exception)
                    //TODO
                }

            }
        )
    }
    //endregion
}