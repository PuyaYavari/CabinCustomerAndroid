package com.cabinInformationTechnologies.cabinCustomerCameraPreview

class CabinCustomerCameraPreviewInteractor(var output: com.cabinInformationTechnologies.cabinCustomerCameraPreview.CabinCustomerCameraPreviewContracts.InteractorOutput?) :
    com.cabinInformationTechnologies.cabinCustomerCameraPreview.CabinCustomerCameraPreviewContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    //TODO: Implement your Interactor methods here

    //endregion
}