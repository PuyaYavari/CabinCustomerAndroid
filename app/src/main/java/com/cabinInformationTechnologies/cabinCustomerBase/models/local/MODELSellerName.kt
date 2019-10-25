package com.cabinInformationTechnologies.cabinCustomerBase.models.local

class MODELSellerName: com.cabinInformationTechnologies.cabinCustomerBase.models.local.LocalDataModel {
    lateinit var name: String
    var id: Int? = null

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonModel = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONSellerName
            name = jsonModel.name
            id = jsonModel.id
            true
        } catch (exception: Exception){
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(this::class.java.name, "A problem occurred while mapping Seller.", exception)
            false
        }
    }
}