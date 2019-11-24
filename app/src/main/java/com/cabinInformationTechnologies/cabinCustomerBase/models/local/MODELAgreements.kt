package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIAgreements

class MODELAgreements : LocalDataModel {
    private var PIF: String = ""
    private var DSA: String = ""

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as APIAgreements
            this.PIF = jsonData.agreements[0].PIF
            this.DSA = jsonData.agreements[0].DSA
            true
        } catch (exception: Exception) {
            Logger.warn(
                context,
                this::class.java.name,
                "Unable to map Agreements!",
                exception
            )
            false
        }
    }

    fun getPIF() = this.PIF
    fun getDSA() = this.DSA
}