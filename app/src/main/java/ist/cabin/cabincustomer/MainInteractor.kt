package ist.cabin.cabincustomer

class MainInteractor(var output: MainContracts.InteractorOutput?) : MainContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //endregion
}