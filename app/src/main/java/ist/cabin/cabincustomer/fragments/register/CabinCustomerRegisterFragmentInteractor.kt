package ist.cabin.cabincustomer.fragments.register

class CabinCustomerRegisterFragmentInteractor(var output: CabinCustomerRegisterFragmentContracts.InteractorOutput?) :
    CabinCustomerRegisterFragmentContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor



    //endregion
}