package ist.cabin.cabinCustomerExtraditions

class CabinCustomerExtraditionsInteractor(var output: CabinCustomerExtraditionsContracts.InteractorOutput?) :
    CabinCustomerExtraditionsContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}