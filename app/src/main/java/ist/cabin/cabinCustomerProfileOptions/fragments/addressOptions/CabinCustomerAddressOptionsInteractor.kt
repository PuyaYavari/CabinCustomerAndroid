package ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions

class CabinCustomerAddressOptionsInteractor(var output: CabinCustomerAddressOptionsContracts.InteractorOutput?) :
    CabinCustomerAddressOptionsContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun getAddressData(): Unit? { //TODO: FIX AND SEND PROPPER DATA
        return null
    }

    //endregion
}