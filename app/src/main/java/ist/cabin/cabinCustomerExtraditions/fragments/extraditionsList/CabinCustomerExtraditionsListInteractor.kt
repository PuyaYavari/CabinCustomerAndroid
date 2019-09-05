package ist.cabin.cabinCustomerExtraditions.fragments.extraditionsList

class CabinCustomerExtraditionsListInteractor(var output: CabinCustomerExtraditionsListContracts.InteractorOutput?) :
    CabinCustomerExtraditionsListContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun getInitialData(): Unit? { //TODO: RETURN DATA FROM BACKEND
        return null
    }

    //endregion
}