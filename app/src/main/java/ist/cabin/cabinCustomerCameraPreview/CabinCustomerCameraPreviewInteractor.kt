package ist.cabin.cabinCustomerCameraPreview

class CabinCustomerCameraPreviewInteractor(var output: CabinCustomerCameraPreviewContracts.InteractorOutput?) :
    CabinCustomerCameraPreviewContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}