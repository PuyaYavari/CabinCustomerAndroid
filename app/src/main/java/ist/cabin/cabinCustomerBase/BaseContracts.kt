package ist.cabin.cabinCustomerBase

import android.content.Context
import android.os.Bundle
import ist.cabin.cabinCustomerBase.models.Request
import ist.cabin.cabinCustomerBase.models.backend.JSONIssue
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
        @Headers("APIKEY: "+Constants.API_KEY,
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
        fun debug(location: String?, message: String, exception: Exception?)
        fun error(location: String?, message: String, exception: Throwable)
        fun info(location: String?, message: String, exception: Exception?)
        fun verbose(location: String?, message: String, exception: Exception?)
        fun warn(location: String?, message: String, exception: Exception?)
        fun failure(location: String?, message: String?, exception: Exception?)
    }

}