package com.cabinInformationTechnologies.cabinCustomerBase

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.cabinInformationTechnologies.cabinCustomerBase.models.Paging
import com.cabinInformationTechnologies.cabinCustomerBase.models.Request
import com.cabinInformationTechnologies.cabinCustomerBase.models.UserAuth
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.IssueResponseMapper
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.LocalDataModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


object NetworkManager {

    fun retrofit() : Retrofit = Retrofit.Builder()
                                        .client(OkHttpClient()
                                        .newBuilder()
                                        .addInterceptor(HttpLoggingInterceptor() //FIXME: REMOVE TO STOP LOGGING REQUEST AND RESPONSES
                                        .setLevel(HttpLoggingInterceptor.Level.BODY)) //FIXME: REMOVE TO STOP LOGGING REQUEST AND RESPONSES
                                        .build())
                                        .baseUrl(Constants.BASE_URL)
                                        .addConverterFactory(ScalarsConverterFactory.create())
                                        .addConverterFactory(MoshiConverterFactory.create())
                                        .addCallAdapterFactory(CoroutineCallAdapterFactory())
                                        .build()

    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    fun getActiveUserData(): UserAuth? {
        return if (GlobalData.userId != 0) {
            UserAuth(
                GlobalData.session,
                GlobalData.userId
            )
        } else {
            null
        }
    }

    fun getPaging(page: Int?, pageSize: Int?): Paging? {
        var paging : Paging? = null
        if (page != null && pageSize != null) {
            paging = Paging(page, pageSize)
        }
        return paging
    }


    @Throws(Exception::class)
    inline fun <reified T>requestFactory(context: Context,
                                         url: String,
                                         page: Int?,
                                         pageSize: Int?,
                                         data: Any?,
                                         responseClass: LocalDataModel?,
                                         responseAdapter: JsonAdapter<T>?,
                                         ResponseCallbacks: BaseContracts.ResponseCallbacks
    ){
        val apiServices = retrofit()
            .create(BaseContracts.ApiServices::class.java)
        val adapter: JsonAdapter<T>? = responseAdapter
            ?: Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter<T>(T::class.java)

        val call = apiServices.sendRequest(
            Request(
                getActiveUserData(),
                getPaging(page, pageSize),
                data
            ), url)
        call.enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                when {
                    response.isSuccessful -> {
                        try {
                            val responseBody: Any? = response.body()
                            val issue = IssueResponseMapper.issueResponseMapper(
                                context,
                                responseBody.toString()
                            )
                            if (issue == null) {
                                val dataModel = adapter?.fromJson(responseBody.toString()) ?: throw Exception("Some Exception")
                                if (responseClass != null)
                                    ResponseCallbacks.onSuccess(responseClass.mapFrom(context, dataModel))
                                else
                                    ResponseCallbacks.onSuccess(dataModel)
                            } else {
                                ResponseCallbacks.onIssue(issue)
                            }
                        } catch (exception: Exception) {
                            ResponseCallbacks.onException(exception)
                        }
                    }
                    (response.code() in 300..500) -> {
                        try {
                            val responseBody: Any? = response.body()
                            val issue = IssueResponseMapper.issueResponseMapper(
                                context,
                                responseBody.toString()
                            )
                            if (issue == null)
                                ResponseCallbacks.onError(response.errorBody().toString(), null)
                            else
                                ResponseCallbacks.onError(issue.message, issue.url)
                        } catch (exception: Exception) {
                            ResponseCallbacks.onException(exception)
                        }
                    }
                    else -> ResponseCallbacks.onServerDown()
                }
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
                Logger.failure(
                    context,
                    this::class.java.name,
                    "SERIOUS PROBLEM, NETWORK MANAGER FAILURE!",
                    t
                )
                ResponseCallbacks.onFailure(t)
            }
        })
    }

}