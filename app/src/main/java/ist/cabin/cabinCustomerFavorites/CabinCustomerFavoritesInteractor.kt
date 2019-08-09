package ist.cabin.cabinCustomerFavorites

class CabinCustomerFavoritesInteractor(var output: CabinCustomerFavoritesContracts.InteractorOutput?) :
    CabinCustomerFavoritesContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}