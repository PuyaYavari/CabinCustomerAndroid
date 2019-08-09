package ist.cabin.cabinCustomerAgreement

class CabinCustomerAgreementInteractor(var output: CabinCustomerAgreementContracts.InteractorOutput?) :
    CabinCustomerAgreementContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //endregion
}