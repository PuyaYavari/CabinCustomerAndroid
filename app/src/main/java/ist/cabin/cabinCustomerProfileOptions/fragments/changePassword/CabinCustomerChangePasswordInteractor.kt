package ist.cabin.cabinCustomerProfileOptions.fragments.changePassword

class CabinCustomerChangePasswordInteractor(var output: CabinCustomerChangePasswordContracts.InteractorOutput?) :
    CabinCustomerChangePasswordContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}