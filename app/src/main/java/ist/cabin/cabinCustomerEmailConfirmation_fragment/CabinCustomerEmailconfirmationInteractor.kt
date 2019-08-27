package ist.cabin.cabinCustomerEmailConfirmation_fragment

class CabinCustomerEmailconfirmationInteractor(var output: CabinCustomerEmailconfirmationContracts.InteractorOutput?) :
    CabinCustomerEmailconfirmationContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //endregion
}