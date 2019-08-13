package ist.cabin.cabincustomer.fragments.favorites

class CabinCustomerFavoritesInteractor(var output: CabinCustomerFavoritesContracts.InteractorOutput?) :
    CabinCustomerFavoritesContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}