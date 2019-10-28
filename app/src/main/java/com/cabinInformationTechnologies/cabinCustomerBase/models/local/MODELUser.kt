package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context

class MODELUser: com.cabinInformationTechnologies.cabinCustomerBase.models.local.LocalDataModel {
    private var id: Int = 0
    private var session: String = ""
    var name: String? = null
    var surname: String? = null

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonModel = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONUser
            this.id = jsonModel.id
            this.session = jsonModel.session
            this.name = jsonModel.firstname
            this.surname = jsonModel.lastname
            true
        } catch (exception: Exception) {
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                context,
                this::class.java.name,
                "Error while mapping user!!",
                exception)
            false
        }
    }

    fun setData(id: Int, session: String, name: String?, surname: String?) {
        this.id = id
        this.session = session
        this.name = name
        this.surname = surname
    }

    fun getSession(): String = session
    fun getId(): Int = id
}