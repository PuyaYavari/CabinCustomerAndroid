package com.cabinInformationTechnologies.cabinCustomerBase

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
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
        .client(OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
        .baseUrl(com.cabinInformationTechnologies.cabinCustomerBase.Constants.BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    fun getActiveUserData(): com.cabinInformationTechnologies.cabinCustomerBase.models.UserAuth? {
        return if (com.cabinInformationTechnologies.cabinCustomerBase.GlobalData.userId != 0) {
            com.cabinInformationTechnologies.cabinCustomerBase.models.UserAuth(
                com.cabinInformationTechnologies.cabinCustomerBase.GlobalData.session,
                com.cabinInformationTechnologies.cabinCustomerBase.GlobalData.userId
            )
        } else {
            null
        }
    }

    fun getPaging(page: Int?, pageSize: Int?): com.cabinInformationTechnologies.cabinCustomerBase.models.Paging? {
        var paging : com.cabinInformationTechnologies.cabinCustomerBase.models.Paging? = null
        if (page != null && pageSize != null) {
            paging = com.cabinInformationTechnologies.cabinCustomerBase.models.Paging(page, pageSize)
        }
        return paging
    }


    @Throws(Exception::class)
    inline fun <reified T>requestFactory(context: Context?,
                                         url: String,
                                         page: Int?,
                                         pageSize: Int?,
                                         data: Any?,
                                         responseClass: com.cabinInformationTechnologies.cabinCustomerBase.models.local.LocalDataModel?,
                                         responseAdapter: JsonAdapter<T>?,
                                         ResponseCallbacks: com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.ResponseCallbacks
    ){
        val apiServices = com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager.retrofit()
            .create(com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.ApiServices::class.java)
        val adapter: JsonAdapter<T>? = responseAdapter
            ?: Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter<T>(T::class.java)

        val call = apiServices.sendRequest(
            com.cabinInformationTechnologies.cabinCustomerBase.models.Request(
                com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager.getActiveUserData(),
                com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager.getPaging(page, pageSize),
                data
            ), url)
        call.enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                when {
                    response.isSuccessful -> {
                        try {
                            val responseBody: Any? = response.body()
                            val issue = com.cabinInformationTechnologies.cabinCustomerBase.models.backend.IssueResponseMapper.issueResponseMapper(responseBody.toString())
                            if (issue == null) {
                                val dataModel = adapter?.fromJson(responseBody.toString()) ?: throw Exception("Some Exception")
                                if (responseClass != null)
                                    ResponseCallbacks.onSuccess(responseClass.mapFrom(dataModel))
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
                            val issue = com.cabinInformationTechnologies.cabinCustomerBase.models.backend.IssueResponseMapper.issueResponseMapper(responseBody.toString())
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
                Log.d("FAIL", t.toString())
                Log.d("FAIL MESSAGE", t.message.toString()) //TODO WHAT CAN I DO SOMETIMES!?
                ResponseCallbacks.onFailure(t) //TODO CHECK IF INTERNET CONNECTED
            }
        })
    }

}