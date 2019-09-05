package ist.cabin.cabinCustomerFinishTrade.fragments.address

class CabinCustomerFinishTradeAddressInteractor(var output: CabinCustomerFinishTradeAddressContracts.InteractorOutput?) :
    CabinCustomerFinishTradeAddressContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}