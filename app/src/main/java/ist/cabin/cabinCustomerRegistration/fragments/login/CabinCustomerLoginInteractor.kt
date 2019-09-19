package ist.cabin.cabinCustomerRegistration.fragments.login

import android.content.Context
import android.util.Log
import ist.cabin.cabinCustomerBase.Constants
import ist.cabin.cabinCustomerBase.NetworkManager
import ist.cabin.cabinCustomerBase.NetworkManagerContracts
import ist.cabin.cabinCustomerBase.models.backend.APILogin
import ist.cabin.cabinCustomerBase.models.backend.APIUser
import ist.cabin.cabinCustomerBase.models.backend.JSONIssue
import ist.cabin.cabinCustomerBase.models.backend.JSONLoginData


class CabinCustomerLoginInteractor(var output: CabinCustomerLoginContracts.InteractorOutput?) :
    CabinCustomerLoginContracts.Interactor {



    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun login(context: Context, email: String, password: String){
        val data = APILogin(
            listOf(
                JSONLoginData(
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
            null,
            null,
            object : NetworkManagerContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    Log.d("Login OnSuccess", value.toString())
//                    val user = UserResponseMapper.mapUserLoginResponse(value)
//                    if (user != null) { //TODO
//                        Log.d("OK", user.toString())
//                    } else {
//                        val JSONIssue = IssueResponseMapper.issueResponseMapper(value)
//                        if (JSONIssue != null){
//                            //TODO
//                        } else {
//                            //TODO: Unknown problem
//                        }
//                    }
                }

                override fun onIssue(value: JSONIssue) {
                    Log.d("ISSUE", value.toString())
                }

                override fun onError(value: String, url: String?) {
                    Log.d("Login onError", value)
                    if (url != null)
                        Log.d("Login onError url", url)
                }

                override fun onFailure(throwable: Throwable) {
                    Log.d("Login OnFailure", throwable.message.toString())
                    //TODO
                }

                override fun onServerDown() {
                    //TODO
                }

                override fun onException(exception: Exception) {
                    Log.d("EXCEPTION", exception.message.toString())
                }
            }
        )
        val value = "{\"USER\":[{\"ID\":5,\"NAME\":\"Puya\",\"SURNAME\":\"YAVARI\",\"SESSION\":\"123\"}]}"
        val issue = "{\"ISSUE\":[{\"ID\":200,\"CODE\":505,\"MESSAGE\":\"SOME ISSUE\",\"URL\":\"SOME URL\"}]}"

    }

    //endregion
}