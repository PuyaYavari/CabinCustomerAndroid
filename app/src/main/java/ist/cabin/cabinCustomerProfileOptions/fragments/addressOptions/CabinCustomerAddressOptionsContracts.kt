package ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerAddressOptionsContracts {

    interface View : BaseContracts.View {
        fun setupEmptyDeliveryAddressList()
        fun setupEmptyInvoiceAddressList()
        fun setupDeliveryAddressList()
        fun setupInvoiceAddressList()
        fun addDeliveryAddressListener()
        fun addInvoiceAddressListener()
    }

    interface Presenter : BaseContracts.Presenter {
        fun setupDeliveryAddressList()
        fun setupInvoiceAddressList()
        fun moveToAddDeliveryAddressPage()
        fun moveToAddInvoiceAddressPage()
    }

    interface Interactor : BaseContracts.Interactor {
        fun getAddressData(): Int?
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        //TODO
    }

    interface Router : BaseContracts.Router {
        fun moveToAddDeliveryAddressPage()
        fun moveToAddInvoiceAddressPage()
    }

    interface Addressbox {
        fun getType(): Int
        fun getAddressTypeID(): Int
    }

}