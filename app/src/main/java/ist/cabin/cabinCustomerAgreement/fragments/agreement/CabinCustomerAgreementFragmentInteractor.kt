package ist.cabin.cabinCustomerAgreement.fragments.agreement

class CabinCustomerAgreementFragmentInteractor(var output: CabinCustomerAgreementFragmentContracts.InteractorOutput?) :
    CabinCustomerAgreementFragmentContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //endregion
}