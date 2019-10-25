package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.deliveryAddress

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.*

class CabinCustomerDeliveryAddressPresenter(var view: com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.deliveryAddress.CabinCustomerDeliveryAddressContracts.View?) :
    com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.deliveryAddress.CabinCustomerDeliveryAddressContracts.Presenter,
    com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.deliveryAddress.CabinCustomerDeliveryAddressContracts.InteractorOutput {

    var interactor: com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.deliveryAddress.CabinCustomerDeliveryAddressContracts.Interactor? =
        com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.deliveryAddress.CabinCustomerDeliveryAddressInteractor(
            this
        )
    var router: com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.deliveryAddress.CabinCustomerDeliveryAddressContracts.Router? = null

    private val address = com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELAddress()

    private lateinit var countryCode: String
    private var phone: String = ""

    private var nameFilled = false
    private var surnameFilled = false
    private var phoneFilled = false
    private var addressFilled = false
    private var addressHeaderFilled = false

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router =
            com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.deliveryAddress.CabinCustomerDeliveryAddressRouter(
                activity
            )

    }

    override fun onDestroy() {
        view = null
        interactor?.unregister()
        interactor = null
        router?.unregister()
        router = null
    }

    //endregion

    //region Presenter

    override fun setId(id: Int?) {
        this.address.id = id
    }

    override fun setName(inputtedName: String) {
        if (inputtedName.length <= com.cabinInformationTechnologies.cabinCustomerBase.Constants.MAX_NAME_LENGTH) {
            this.address.name = inputtedName
            nameFilled = inputtedName.isNotEmpty()
        } else {
            nameFilled = false
            Log.e("input error", "name too long!")
        }

        validatePage()
    }

    override fun setSurname(inputtedSurname: String) {
        if (inputtedSurname.length <= com.cabinInformationTechnologies.cabinCustomerBase.Constants.MAX_SURNAME_LENGTH) {
            this.address.surname = inputtedSurname
            surnameFilled = inputtedSurname.isNotEmpty()
        } else {
            surnameFilled = false
            Log.e("input error", "surname too long!")
        }

        validatePage()
    }

    override fun setPhone(inputtedPhone: String){
        countryCode = "0090"
        if(inputtedPhone.isNotEmpty())
        {
            phone = countryCode
            for (char in inputtedPhone) if (char.isDigit()) phone = "$phone$char"
            phoneFilled = phone.length == com.cabinInformationTechnologies.cabinCustomerBase.Constants.MAX_PHONE_LENGTH
        } else {
            phone = ""
            phoneFilled = false
        }
        address.phone = this.phone

        validatePage()
    }

    override fun setProvince(province: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProvince) {
        this.address.province = province.name
        this.address.provinceId = province.id

        validatePage()
    }

    override fun setDistrict(district: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELDistrict) {
        this.address.district = district.name
        this.address.districtId = district.id

        validatePage()
    }

    override fun setAddress(inputtedAddress: String) {
        if(inputtedAddress.length <= com.cabinInformationTechnologies.cabinCustomerBase.Constants.MAX_ADDRESS_LENGTH) {
            this.address.address = inputtedAddress
            addressFilled = inputtedAddress.isNotEmpty()
        } else {
            addressFilled = false
            Log.e("input error", "address too long!")
        }

        validatePage()
    }

    override fun setAddressHeader(inputtedAddressHeader: String) {
        if(inputtedAddressHeader.length <= com.cabinInformationTechnologies.cabinCustomerBase.Constants.MAX_ADDRESS_HEADER_LENGTH) {
            this.address.header = inputtedAddressHeader
            addressHeaderFilled = inputtedAddressHeader.isNotEmpty()
        } else {
            addressHeaderFilled = false
            Log.e("input error", "address header too long!")
        }

        validatePage()
    }

    override fun getProvinces(context: Context) {
        interactor?.getProvinces(context)
    }

    override fun getDistrictsOfProvince(
        context: Context,
        province: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProvince
    ) {
        interactor?.getDistrictsOfProvince(context, province)
    }

    override fun saveAddress(context: Context) {
        interactor?.saveAddress(context, address)
    }

    override fun updateAddress(context: Context) {
        interactor?.updateAddress(context, address)
    }

    private fun validatePage() {
        if (nameFilled && surnameFilled && phoneFilled && addressFilled && addressHeaderFilled) view!!.enableAddButton()
        else view!!.disableAddButton()
    }

    //endregion

    //region InteractorOutput

    override fun setProvinces(provinces: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProvinces) {
        view?.provinces = provinces.getProvinces()
    }

    override fun setDistricts(districts: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELDistricts) {
        view?.districts = districts.getDistricts()
    }

    override fun feedback(message: String?) {
        //TODO: SHOW MESSAGE
        view?.onBackPressed()
    }

    //endregion
}