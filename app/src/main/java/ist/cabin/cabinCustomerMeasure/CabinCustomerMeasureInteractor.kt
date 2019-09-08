package ist.cabin.cabinCustomerMeasure

class CabinCustomerMeasureInteractor(var output: CabinCustomerMeasureContracts.InteractorOutput?) :
    CabinCustomerMeasureContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}