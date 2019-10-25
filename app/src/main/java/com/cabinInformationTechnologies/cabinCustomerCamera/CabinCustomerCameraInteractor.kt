package com.cabinInformationTechnologies.cabinCustomerCamera

class CabinCustomerCameraInteractor(var output: com.cabinInformationTechnologies.cabinCustomerCamera.CabinCustomerCameraContracts.InteractorOutput?) :
    com.cabinInformationTechnologies.cabinCustomerCamera.CabinCustomerCameraContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor


    //endregion
}