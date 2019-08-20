package ist.cabin.cabinCustomerProfileOptions.fragment.addressOptions

class CabinCustomerAddressOptionsInteractor(var output: CabinCustomerAddressOptionsContracts.InteractorOutput?) :
    CabinCustomerAddressOptionsContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}