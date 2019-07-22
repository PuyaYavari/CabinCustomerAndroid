package ist.cabin.cabinCustomerRegister

class CabinCustomerRegisterInteractor(var output: CabinCustomerRegisterContracts.InteractorOutput?) :
    CabinCustomerRegisterContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}