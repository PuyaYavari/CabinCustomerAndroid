package com.cabinInformationTechnologies.cabinCustomerBase

import android.content.Context
import android.os.Bundle
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

    interface Product { //FIXME: REMOVE WHEN EXTRADITIONS FIXED
        fun getID(): String
        //TODO: COLOR, SIZE, PRICE, PRICE UNIT
    }

    interface ApiServices{
        @Headers("APIKEY: "+ Constants.API_KEY,
                    "TOKEN: TOKEN", //FIXME: SEND TOKEN
                    "Content-Type: application/json")
        @POST
        fun sendRequest(@Body json: com.cabinInformationTechnologies.cabinCustomerBase.models.Request<Any>, @Url url: String): Call<String?>
    }

    interface ResponseCallbacks {
        fun onSuccess(value: Any?)
        fun onIssue(value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue)
        fun onError(value: String, url: String?)
        fun onFailure(throwable: Throwable)
        fun onServerDown()
        fun onException(exception: Exception)
    }

    interface Logger {
        fun debug(context: Context, location: String?, message: String, exception: Exception?)
        fun error(context: Context, location: String?, message: String, exception: Throwable)
        fun info(context: Context, location: String?, message: String, exception: Exception?)
        fun verbose(context: Context, location: String?, message: String, exception: Exception?)
        fun warn(context: Context, location: String?, message: String, exception: Exception?)
        fun failure(context: Context, location: String?, message: String?, exception: Exception?)
    }

}