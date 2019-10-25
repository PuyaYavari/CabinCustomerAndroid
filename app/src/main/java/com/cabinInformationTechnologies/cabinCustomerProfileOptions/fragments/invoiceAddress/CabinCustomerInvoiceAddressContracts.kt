package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.invoiceAddress

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.*

object CabinCustomerInvoiceAddressContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {
        var provinces: List<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProvince?>
        var districts: List<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELDistrict?>

        fun showCorporateInvoiceData()
        fun hideCorporateInvoiceData()
        fun enableAddButton()
        fun disableAddButton()
        fun onBackPressed()
    }

    interface Presenter : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        fun setId(id: Int?)
        fun setName(inputtedName: String)
        fun setSurname(inputtedSurname: String)
        fun setPhone(inputtedPhone: String)
        fun setProvince(province: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProvince)
        fun setDistrict(district: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELDistrict)
        fun setAddress(inputtedAddress: String)
        fun setAddressHeader(inputtedAddressHeader: String)
        fun setInvoiceType(isCorporate: Boolean)
        fun setCorporationName(inputtedCorporationName: String)
        fun setTaxNumber(inputtedTaxNumber: String)
        fun setTaxAdministration(inputtedTaxAdministration: String)
        fun getProvinces(context: Context)
        fun getDistrictsOfProvince(context: Context, province: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProvince)
        fun saveAddress(context: Context)
        fun updateAddress(context: Context)
    }

    interface Interactor : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor {
        fun getProvinces(context: Context)
        fun getDistrictsOfProvince(context: Context, province: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProvince)
        fun saveAddress(context: Context, address: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress)
        fun updateAddress(context: Context, address: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress)
    }

    interface InteractorOutput : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput {
        fun setProvinces(provinces: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProvinces)
        fun setDistricts(districts: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELDistricts)
        fun feedback(message: String?)
    }

    interface Router : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Router

}