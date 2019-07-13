package ist.cabin.cabinCustomerLogin

class CabinCustomerLoginInteractor(var output: CabinCustomerLoginContracts.InteractorOutput?) :
    CabinCustomerLoginContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}