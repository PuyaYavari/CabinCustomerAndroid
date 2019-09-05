package ist.cabin.cabinCustomerFinishTrade.fragments.overview

class CabinCustomerFinishTradeOverviewInteractor(var output: CabinCustomerFinishTradeOverviewContracts.InteractorOutput?) :
    CabinCustomerFinishTradeOverviewContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}