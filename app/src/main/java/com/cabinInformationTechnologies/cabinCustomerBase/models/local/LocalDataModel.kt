package com.cabinInformationTechnologies.cabinCustomerBase.models.local

interface LocalDataModel {
    fun <T>mapFrom(modelData: T): Boolean
}