package ist.cabin.cabincustomer.fragments.home

class CabinCustomerHomeInteractor(var output: CabinCustomerHomeContracts.InteractorOutput?) :
    CabinCustomerHomeContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}