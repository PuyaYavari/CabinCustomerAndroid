package com.cabinInformationTechnologies.cabinCustomerBase

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.NavController
import com.cabinInformationTechnologies.cabinCustomerBase.models.Request
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Url


interface BaseContracts {

    interface View {
        fun getActivityContext(): Context?
        fun showErrorDialog(error: String?)
    }

    interface Presenter {
        fun onCreate(bundle: Bundle? = null) {}
        fun onResume() {}
        fun onPause() {}
        fun onDestroy()
    }

    interface Interactor {
        fun unregister()
    }

    interface InteractorOutput //does nothing for now

    interface Router {
        fun unregister()
    }

    interface ApiServices{
        @Headers("APIKEY: "+ Constants.API_KEY,
                    "TOKEN: TOKEN", //FIXME: SEND TOKEN
                    "Content-Type: application/json")
        @POST
        fun sendRequest(@Body json: Request<Any>, @Url url: String): Call<String?>
    }

    interface ResponseCallbacks {
        fun onSuccess(value: Any?)
        fun onIssue(value: JSONIssue)
        fun onError(value: String, url: String?)
        fun onFailure(throwable: Throwable)
        fun onServerDown()
        fun onException(exception: Exception)
    }

    interface Logger {
        fun debug(context: Context, location: String?, message: String, exception: Exception?)
        fun error(context: Context, location: String?, message: String, exception: Throwable)
        fun info(context: Context, location: String?, message: String, exception: Exception?)
        fun verbose(context: Context, location: String?, message: String, throwable: Throwable?)
        fun warn(context: Context, location: String?, message: String, throwable: Throwable?)
        fun failure(context: Context, location: String?, message: String?, throwable: Throwable?)
    }

    interface FirebaseLogger {
        fun login(context: Context, method: String)
    }

    interface ActivityResultListener {
        fun onActivityResult(resultCode: Int, data: Intent?)
    }

    interface Feedbacker {
        //region: Toast
        fun feedback(context: Context, message: String)
        fun feedback(context: Context, message: String, length: Int)

        //region: Dialog
        fun feedback(
            context: Context,
            message: String,
            retryFunction: () -> Unit
        )
        fun feedback(
            context: Context,
            title: String,
            message: String,
            retryFunction: () -> Unit
        )
        fun feedback(
            context: Context,
            title: String,
            message: String,
            neutralText: String
        )
        fun feedback(
            context: Context,
            title: String,
            message: String,
            neutralText: String,
            retryFunction: () -> Unit
        )
        fun feedback(
            context: Context,
            navController: NavController,
            title: String,
            message: String,
            retryFunction: () -> Unit
        )
        fun feedback(
            context: Context,
            title: String,
            message: String,
            positiveText: String,
            positiveFunction: () -> Unit,
            negativeText: String,
            negativeFunction: () -> Unit
        )
        fun feedback(
            context: Context,
            title: String,
            message: String,
            neutralText: String?,
            neutralFunction: (() -> Unit)?,
            positiveText: String?,
            positiveFunction: (() -> Unit)?,
            negativeText: String?,
            negativeFunction: (() -> Unit)?
        )
    }

}