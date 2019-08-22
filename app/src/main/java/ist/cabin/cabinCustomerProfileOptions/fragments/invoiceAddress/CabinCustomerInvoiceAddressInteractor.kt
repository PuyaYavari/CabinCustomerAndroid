package ist.cabin.cabinCustomerProfileOptions.fragments.invoiceAddress

class CabinCustomerInvoiceAddressInteractor(var output: CabinCustomerInvoiceAddressContracts.InteractorOutput?) :
    CabinCustomerInvoiceAddressContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}