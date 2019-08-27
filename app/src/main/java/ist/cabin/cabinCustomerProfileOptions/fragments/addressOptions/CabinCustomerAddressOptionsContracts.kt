package ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerAddressOptionsContracts {

    interface View : BaseContracts.View {
        fun setupEmptyDeliveryAddressList()
        fun setupEmptyInvoiceAddressList()
        fun setupDeliveryAddressList()
        fun setupInvoiceAddressList()
        fun addDeliveryAddressListener(name: String?, surname: String?, phone: String?, province: String?,
                                       district: String?, address: String?, addressHeader: String?)
        fun addInvoiceAddressListener(name: String?, surname: String?, phone: String?, province: String?,
                                      district: String?, address: String?, addressHeader: String?,
                                      isCorporate: Boolean?, corporationName: String?, taxNo: String?,
                                      taxAdministration: String?)
    }

    interface Presenter : BaseContracts.Presenter {
        fun setupPage()
        fun setupDeliveryAddressList()
        fun setupInvoiceAddressList()
        fun moveToAddDeliveryAddressPage(name: String?, surname: String?, phone: String?, province: String?,
                                         district: String?, address: String?, addressHeader: String?)
        fun moveToAddInvoiceAddressPage(name: String?, surname: String?, phone: String?, province: String?,
                                        district: String?, address: String?, addressHeader: String?,
                                        isCorporate: Boolean?, corporationName: String?, taxNo: String?,
                                        taxAdministration: String?)
    }

    interface Interactor : BaseContracts.Interactor {
        fun getAddressData(): Unit? //TODO: GET PROPER DATA
    }

    interface InteractorOutput : BaseContracts.InteractorOutput

    interface Router : BaseContracts.Router {
        fun moveToAddDeliveryAddressPage(name: String?, surname: String?, phone: String?, province: String?,
                                         district: String?, address: String?, addressHeader: String?)
        fun moveToAddInvoiceAddressPage(name: String?, surname: String?, phone: String?, province: String?,
                                        district: String?, address: String?, addressHeader: String?,
                                        isCorporate: Boolean, corporationName: String?, taxNo: String?,
                                        taxAdministration: String?)
    }

    interface Addressbox {
        fun getType(): Int
        fun getAddressTypeID(): Int
    }

}