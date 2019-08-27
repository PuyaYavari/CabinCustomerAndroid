package ist.cabin.cabinCustomerLogin_fragment

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import ist.cabin.cabincustomer.models.LoginRequest
import ist.cabin.cabincustomer.models.UserResponse


class CabinCustomerLoginInteractor(var output: CabinCustomerLoginContracts.InteractorOutput?) :
    CabinCustomerLoginContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

//    var json: String = "{\"KULLANICI\":[{\"ID\":1,\"AD\":\"BÜNYAMİN\",\"SOYAD\":\"HATİPOĞLU\",\"KULLANICIAD\":\"bunyaminhatipoglu@hotmail.com\"}]}"
//    var requestJson: String = "{\"KULLANICI\":[{\"KULLANICIAD\":\"ABC2@hotmail.com\",\"SIFRE\":\"ABC\"}]}"


    override fun login(email: String, password: String){
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val userResponseAdapter = moshi.adapter<UserResponse>(UserResponse::class.java)
        val loginRequestAdapter = moshi.adapter<LoginRequest>(LoginRequest::class.java)

//        Log.d("Email", email)
//        Log.d("password", password)
//
//        Log.d("raw data", json)
//        Log.d("json data", userResponseAdapter.fromJson(json).toString())
//        Log.d("userArray", userResponseAdapter.fromJson(json)?.userArray.toString())
//        if(userResponseAdapter.fromJson(json)?.userArray == null)
//            Log.d("json data", "hatali")
//        Log.d("Login Request", loginRequestAdapter.fromJson(requestJson)?.toString())

        //TODO: Send login request and recieve user data
    }

    //endregion
}