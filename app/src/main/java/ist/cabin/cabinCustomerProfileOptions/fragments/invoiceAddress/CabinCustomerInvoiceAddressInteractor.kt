package ist.cabin.cabinCustomerProfileOptions.fragments.invoiceAddress

class CabinCustomerInvoiceAddressInteractor(var output: CabinCustomerInvoiceAddressContracts.InteractorOutput?) :
    CabinCustomerInvoiceAddressContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun saveData() {
        //TODO: save data
    }

    //endregion
}