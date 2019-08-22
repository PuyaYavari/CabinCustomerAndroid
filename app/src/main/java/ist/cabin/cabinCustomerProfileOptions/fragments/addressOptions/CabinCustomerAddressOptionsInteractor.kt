package ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions

class CabinCustomerAddressOptionsInteractor(var output: CabinCustomerAddressOptionsContracts.InteractorOutput?) :
    CabinCustomerAddressOptionsContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun getAddressData(): Int? { //TODO: FIX AND SEND PROPPER DATA
        return 1
    }

    //endregion
}