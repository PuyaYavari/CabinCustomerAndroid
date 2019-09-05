package ist.cabin.cabinCustomerFinishTrade

class CabinCustomerFinishTradeInteractor(var output: CabinCustomerFinishTradeContracts.InteractorOutput?) :
    CabinCustomerFinishTradeContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}