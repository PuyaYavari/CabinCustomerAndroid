package ist.cabin.cabinCustomerProfileOptions

class CabinCustomerProfileOptionsInteractor(var output: CabinCustomerProfileOptionsContracts.InteractorOutput?) :
    CabinCustomerProfileOptionsContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}