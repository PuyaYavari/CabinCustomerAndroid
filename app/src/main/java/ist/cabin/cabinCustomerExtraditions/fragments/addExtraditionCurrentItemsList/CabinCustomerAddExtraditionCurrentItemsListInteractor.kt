package ist.cabin.cabinCustomerExtraditions.fragments.addExtraditionCurrentItemsList

class CabinCustomerAddExtraditionCurrentItemsListInteractor(var output: CabinCustomerAddExtraditionCurrentItemsListContracts.InteractorOutput?) :
    CabinCustomerAddExtraditionCurrentItemsListContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    override fun getProductsList() {
        //TODO: GET DATA FROM BACKEND
    }

    //endregion
}