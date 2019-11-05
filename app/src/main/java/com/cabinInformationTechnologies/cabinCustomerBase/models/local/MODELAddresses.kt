package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIAddress

class MODELAddresses : LocalDataModel {
    private val addresses: MutableList<MODELAddress?> = mutableListOf()

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as APIAddress
            jsonData.addresses.forEach {
                val address = MODELAddress()
                if (address.mapFrom(
                        context,
                        it)
                )
                    addresses.add(address)
            }
            true
        } catch (exception: Exception) {
            Logger.error(
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