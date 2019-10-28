package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context
import java.text.SimpleDateFormat
import java.util.*

class MODELPersonalInfo: com.cabinInformationTechnologies.cabinCustomerBase.models.local.LocalDataModel {
    var name: String? = null
    var surname: String? = null
    var birthday: Date? = null
    var email: String? = null
    var phone: String? = null
    var sex: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSex =
        com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSex()


    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONPersonalInfo
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
            sex.mapFrom(context, jsonData.sex[0])
            true
        } catch (exception: Exception) {
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(
                context,
                this::class.java.name,
                "A problem occurred while mapping PersonalInfo.",
                exception)
            false
        }
    }
}