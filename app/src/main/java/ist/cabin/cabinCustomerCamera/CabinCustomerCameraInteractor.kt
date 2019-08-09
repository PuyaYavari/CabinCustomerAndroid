package ist.cabin.cabinCustomerCamera

class CabinCustomerCameraInteractor(var output: CabinCustomerCameraContracts.InteractorOutput?) :
    CabinCustomerCameraContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor


    //endregion
}