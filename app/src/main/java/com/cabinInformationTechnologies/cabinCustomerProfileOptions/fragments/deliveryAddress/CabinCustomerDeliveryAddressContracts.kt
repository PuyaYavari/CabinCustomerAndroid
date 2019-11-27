package com.cabinInformationTechnologies.cabinCustomerProfileOptions.fragments.deliveryAddress

import android.content.Context
import androidx.navigation.NavController
import com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.*

object CabinCustomerDeliveryAddressContracts {

    interface View : BaseContracts.View {
        var provinces: List<MODELProvince?>
        var districts: List<MODELDistrict?>

        fun enableAddButton()
        fun disableAddButton()
        fun success()
    }

    interface Presenter : BaseContracts.Presenter {
        fun setId(id: Int?)
        fun setName(inputtedName: String)
        fun setSurname(inputtedSurname: String)
        fun setPhone(inputtedPhone: String)
        fun setProvince(province: MODELProvince)
        fun setDistrict(district: MODELDistrict)
        fun setAddress(inputtedAddress: String)
        fun setAddressHeader(inputtedAddressHeader: String)
        fun saveAddress(context: Context)
        fun updateAddress(context: Context)
        fun getProvinces(context: Context, navController: NavController)
        fun getDistrictsOfProvince(context: Context, province: MODELProvince, navController: NavController)
    }

    interface Interactor : BaseContracts.Interactor {
        fun saveAddress(context: Context, address: MODELAddress)
        fun updateAddress(context: Context, address: MODELAddress)
        fun getProvinces(context: Context, navController: NavController)
        fun getDistrictsOfProvince(context: Context, province: MODELProvince, navController: NavController)
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        fun setProvinces(provinces: MODELProvinces)
        fun setDistricts(districts: MODELDistricts)
        fun success()
    }

    interface Router : BaseContracts.Router

}