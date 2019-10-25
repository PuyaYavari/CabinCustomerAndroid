package com.cabinInformationTechnologies.cabinCustomerBase.models.local

class MODELUsers: com.cabinInformationTechnologies.cabinCustomerBase.models.local.LocalDataModel {
    var users: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELUser> = mutableListOf()

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIUser
            jsonData.users?.forEach {
                val user = com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELUser()
                if (user.mapFrom(it))
                    users.add(user)
            }
            true
        } catch (exception: Exception) {
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(this::class.java.name, "A problem occurred while mapping Users.", exception)
            false
        }
    }
}