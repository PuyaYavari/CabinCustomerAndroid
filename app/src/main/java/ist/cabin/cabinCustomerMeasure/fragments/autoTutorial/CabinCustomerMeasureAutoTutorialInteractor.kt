package ist.cabin.cabinCustomerMeasure.fragments.autoTutorial

class CabinCustomerMeasureAutoTutorialInteractor(var output: CabinCustomerMeasureAutoTutorialContracts.InteractorOutput?) :
    CabinCustomerMeasureAutoTutorialContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}