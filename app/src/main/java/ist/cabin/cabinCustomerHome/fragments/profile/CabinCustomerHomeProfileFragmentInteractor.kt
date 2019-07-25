package ist.cabin.cabinCustomerHome.fragments.profile

class CabinCustomerHomeProfileFragmentInteractor(var output: CabinCustomerHomeProfileFragmentContracts.InteractorOutput?) :
    CabinCustomerHomeProfileFragmentContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}