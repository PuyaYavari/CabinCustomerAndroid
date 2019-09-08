package ist.cabin.cabinCustomerMeasure.fragments.selectMethod

class CabinCustomerMeasureSelectMethodInteractor(var output: CabinCustomerMeasureSelectMethodContracts.InteractorOutput?) :
    CabinCustomerMeasureSelectMethodContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}