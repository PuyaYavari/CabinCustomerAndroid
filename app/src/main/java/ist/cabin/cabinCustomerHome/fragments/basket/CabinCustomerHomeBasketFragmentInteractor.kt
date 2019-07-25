package ist.cabin.cabinCustomerHome.fragments.basket

class CabinCustomerHomeBasketFragmentInteractor(var output: CabinCustomerHomeBasketFragmentContracts.InteractorOutput?) :
    CabinCustomerHomeBasketFragmentContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}