package ist.cabin.cabinCustomerHome.fragments.favorite

class CabinCustomerHomeFavoriteFragmentInteractor(var output: CabinCustomerHomeFavoriteFragmentContracts.InteractorOutput?) :
    CabinCustomerHomeFavoriteFragmentContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}