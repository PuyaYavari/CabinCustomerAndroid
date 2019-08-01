package ist.cabin.cabincustomer.models

import android.util.Log
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

@JsonClass(generateAdapter = true)
data class UserResponse (@Json(name = "KULLANICI") var userArray: List<User>?)

class UserResponseMapper() {
    fun getMeasuresNames(json: String): MutableList<String> {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val userResponseAdapter = moshi.adapter<UserResponse>(UserResponse::class.java)

        val map = userResponseAdapter.fromJson(json)
        val measures = map?.userArray?.get(0)?.measures
        val measuresArray: MutableList<String> = arrayListOf()

        if (measures != null && measures.isNotEmpty()) {
            for (measure in measures) {
                measuresArray.add(measure.name.toString())
            }
        } else {
            Log.d("Measures", "empty!!")
            measuresArray.add("")
        }
        return measuresArray
    }
}
