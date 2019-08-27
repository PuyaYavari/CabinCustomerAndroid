package ist.cabin.cabinCustomerProfileOptions.fragments.deliveryAddress

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerDeliveryAddressContracts {

    interface View : BaseContracts.View {
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
        fun saveData() //TODO: SEND DATA TO BACKEND
    }

    interface Interactor : BaseContracts.Interactor {
        //TODO: CREATE JSONS AND SEND TO BACKEND
    }

    interface InteractorOutput : BaseContracts.InteractorOutput

    interface Router : BaseContracts.Router

}