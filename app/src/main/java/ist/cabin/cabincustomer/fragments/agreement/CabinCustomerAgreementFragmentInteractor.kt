package ist.cabin.cabincustomer.fragments.agreement

class CabinCustomerAgreementFragmentInteractor(var output: CabinCustomerAgreementFragmentContracts.InteractorOutput?) :
    CabinCustomerAgreementFragmentContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}