package ist.cabin.cabinCustomerRegistration

class CabinCustomerRegistrationInteractor(var output: CabinCustomerRegistrationContracts.InteractorOutput?) :
    CabinCustomerRegistrationContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}