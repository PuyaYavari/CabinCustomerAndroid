package ist.cabin.cabinCustomerBase

import ist.cabin.cabinCustomerBase.models.Request
import ist.cabin.cabinCustomerBase.models.backend.JSONIssue
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Url


object NetworkManagerContracts {
    interface ApiServices{
        @Headers("APIKEY: "+Constants.API_KEY,
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
}