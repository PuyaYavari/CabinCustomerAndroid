package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context

interface LocalDataModel {
    fun <T>mapFrom(context: Context, modelData: T): Boolean
}