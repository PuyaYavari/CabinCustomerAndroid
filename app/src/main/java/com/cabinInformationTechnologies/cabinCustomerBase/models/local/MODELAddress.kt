package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class MODELAddress: com.cabinInformationTechnologies.cabinCustomerBase.models.local.LocalDataModel, Parcelable {
    var id: Int? = null
    var isInvoice: Boolean = false
    var name: String = ""
    var surname: String = ""
    var phone: String = ""
    var province: String = ""
    var provinceId: Int = -1
    var district: String = ""
    var districtId: Int = -1
    var address: String = ""
    var header: String = ""
    var isCorporate: Boolean = false
    var corporationName: String? = null
    var taxNumber: String? = null
    var taxAdministration: String? = null

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONAddress
            this.id = jsonData.id
            this.isInvoice = jsonData.isBilling
            this.name = jsonData.name
            this.surname = jsonData.surname
            this.phone = jsonData.phone
            this.province = jsonData.province[0].name
            this.provinceId = jsonData.province[0].id
            this.district = jsonData.district[0].name
            this.districtId = jsonData.district[0].id
            this.address = jsonData.address
            this.header = jsonData.title
            this.isCorporate = !jsonData.isIndividual
            this.corporationName = jsonData.companyName
            this.taxNumber = jsonData.taxNumber
            this.taxAdministration = jsonData.taxAuthority
            true
        } catch (exception: Exception) {
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                context,
                this::class.java.name,
                "A problem occurred while mapping Address!",
                exception
            )
            false
        }
    }
}