package com.cabinInformationTechnologies.cabinCustomerBase.models.local

class MODELSex: com.cabinInformationTechnologies.cabinCustomerBase.models.local.LocalDataModel {
    private var id : Int = -1
    private var name: String? = null

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONSex
            id = jsonData.id
            name = jsonData.name
            true
        } catch (exception: Exception) {
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(this::class.java.name, "A problem occurred while mapping sex.", exception)
            false
        }
    }

    fun getId() = id
    fun getName() = name

    fun setSex(id: Int) {
        when (id) {
            com.cabinInformationTechnologies.cabinCustomerBase.baseAbstracts.Sex.MAN -> {
                this.id = com.cabinInformationTechnologies.cabinCustomerBase.baseAbstracts.Sex.MAN
                this.name = "Erkek"
            }
            com.cabinInformationTechnologies.cabinCustomerBase.baseAbstracts.Sex.WOMAN -> {
                this.id = com.cabinInformationTechnologies.cabinCustomerBase.baseAbstracts.Sex.WOMAN
                this.name = "Kadın"
            }
            else -> {
                this.id = -1
                this.name = null
            }
        }
    }
}