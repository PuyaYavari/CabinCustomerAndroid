package ist.cabin.cabinCustomerProfileOptions.fragments.invoiceAddress

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerInvoiceAddressContracts {

    interface View : BaseContracts.View {
        fun showCorporateInvoiceData()
        fun hideCorporateInvoiceData()
        fun enableAddButton()
        fun disableAddButton()
        fun onBackPressed()
    }

    interface Presenter : BaseContracts.Presenter {
        fun setName(inputtedName: String)
        fun setSurname(inputtedSurname: String)
        fun setPhone(inputtedPhone: String)
        fun setProvince(inputtedProvince: String)
        fun setDistrict(inputtedDistrict: String)
        fun setAddress(inputtedAddress: String)
        fun setAddressHeader(inputtedAddressHeader: String)
        fun setInvoiceType(isCorporate: Boolean)
        fun setCorporationName(inputtedCorporationName: String)
        fun setTaxNumber(inputtedTaxNumber: String)
        fun setTaxAdministration(inputtedTaxAdministration: String)
        fun isCorporate(isCorporate: Boolean)
        fun saveData() //TODO: CREATE JSONS AND SEND TO BACKEND
    }

    interface Interactor : BaseContracts.Interactor {
        fun saveData() //TODO: CREATE JSONS AND SEND TO BACKEND
    }

    interface InteractorOutput : BaseContracts.InteractorOutput

    interface Router : BaseContracts.Router

}