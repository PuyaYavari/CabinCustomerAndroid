package ist.cabin.cabinCustomerBase.models.local

import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.JSONPersonalInfo
import java.text.SimpleDateFormat
import java.util.*

class MODELPersonalInfo: LocalDataModel {
    var name: String? = null
    var surname: String? = null
    var birthday: Date? = null
    var email: String? = null
    var phone: String? = null
    var sex: MODELSex = MODELSex()


    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as JSONPersonalInfo
            name = jsonData.name
            surname = jsonData.surname
            val birthdayString = jsonData.birthday
            if (birthdayString != null)
                birthday = SimpleDateFormat("yyyy-MM-dd").parse(birthdayString)
            if (birthday != null) {
                val birthyear = birthday?.year
                if (birthyear != null)
                    birthday?.year = birthyear + 1900
            }
            email = jsonData.email
            phone = jsonData.phone
            sex.mapFrom(jsonData.sex[0])
            true
        } catch (exception: Exception) {
            Logger.warn(this::class.java.name, "A problem occurred while mapping PersonalInfo.", exception)
            false
        }
    }
}