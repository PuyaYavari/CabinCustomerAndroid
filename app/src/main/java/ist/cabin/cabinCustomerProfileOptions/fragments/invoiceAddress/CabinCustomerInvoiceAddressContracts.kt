package ist.cabin.cabinCustomerProfileOptions.fragments.invoiceAddress

import android.content.Context
import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerBase.models.local.*

object CabinCustomerInvoiceAddressContracts {

    interface View : BaseContracts.View {
        var provinces: List<MODELProvince?>
        var districts: List<MODELDistrict?>

        fun showCorporateInvoiceData()
        fun hideCorporateInvoiceData()
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
        fun setInvoiceType(isCorporate: Boolean)
        fun setCorporationName(inputtedCorporationName: String)
        fun setTaxNumber(inputtedTaxNumber: String)
        fun setTaxAdministration(inputtedTaxAdministration: String)
        fun getProvinces(context: Context)
        fun getDistrictsOfProvince(context: Context, province: MODELProvince)
        fun saveAddress(context: Context)
        fun updateAddress(context: Context)
    }

    interface Interactor : BaseContracts.Interactor {
        fun getProvinces(context: Context)
        fun getDistrictsOfProvince(context: Context, province: MODELProvince)
        fun saveAddress(context: Context, address: MODELAddress)
        fun updateAddress(context: Context, address: MODELAddress)
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        fun setProvinces(provinces: MODELProvinces)
        fun setDistricts(districts: MODELDistricts)
        fun feedback(message: String?)
    }

    interface Router : BaseContracts.Router

}