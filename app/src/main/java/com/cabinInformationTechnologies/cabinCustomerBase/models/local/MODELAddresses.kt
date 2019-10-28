package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context

class MODELAddresses : com.cabinInformationTechnologies.cabinCustomerBase.models.local.LocalDataModel {
    private val addresses: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress?> = mutableListOf()

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIAddress
            jsonData.addresses.forEach {
                val address = com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress()
                if (address.mapFrom(
                        context,
                        it)
                )
                    addresses.add(address)
            }
            true
        } catch (exception: Exception) {
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                context,
                this::class.java.name,
                "A problem occurred while mapping Addresses!",
                exception
            )
            false
        }
    }

    fun getAddresses() = this.addresses
}