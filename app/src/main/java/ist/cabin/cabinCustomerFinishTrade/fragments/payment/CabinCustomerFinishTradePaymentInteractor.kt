package ist.cabin.cabinCustomerFinishTrade.fragments.payment

class CabinCustomerFinishTradePaymentInteractor(var output: CabinCustomerFinishTradePaymentContracts.InteractorOutput?) :
    CabinCustomerFinishTradePaymentContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}