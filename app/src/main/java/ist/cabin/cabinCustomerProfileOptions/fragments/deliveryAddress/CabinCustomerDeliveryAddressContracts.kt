package ist.cabin.cabinCustomerProfileOptions.fragments.deliveryAddress

import android.content.Context
import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerBase.models.local.*

object CabinCustomerDeliveryAddressContracts {

    interface View : BaseContracts.View {
        var provinces: List<MODELProvince?>
        var districts: List<MODELDistrict?>

        fun enableAddButton()
        fun disableAddButton()
        fun onBackPressed()
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
        fun getProvinces(context: Context)
        fun getDistrictsOfProvince(context: Context, province: MODELProvince)
    }

    interface Interactor : BaseContracts.Interactor {
        fun saveAddress(context: Context, address: MODELAddress)
        fun updateAddress(context: Context, address: MODELAddress)
        fun getProvinces(context: Context)
        fun getDistrictsOfProvince(context: Context, province: MODELProvince)
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        fun setProvinces(provinces: MODELProvinces)
        fun setDistricts(districts: MODELDistricts)
        fun feedback(message: String?)
    }

    interface Router : BaseContracts.Router

}