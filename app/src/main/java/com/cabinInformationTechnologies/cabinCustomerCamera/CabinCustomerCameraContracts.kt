package com.cabinInformationTechnologies.cabinCustomerCamera

object CabinCustomerCameraContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {
        fun enableCamera()
        fun disableCamera()
        fun hasNoPermissions(): Boolean
        fun requestPermission()
        fun backCamera()
        fun frontCamera()
        fun flashOff()
        fun flashOn()
        fun setIndicatorVerticalPosition(pitch: Float)
        fun setIndicatorRoll(roll: Float)
        fun setIndicatorAlpha(alpha: Float)
    }

    interface Presenter : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        fun cameraSwitch(pitch: Float, roll: Float)
        fun checkPermissions()
        fun switchCamera(cameraStatus: com.cabinInformationTechnologies.cabinCustomerCamera.CabinCustomerCameraActivity.CameraState?)
        fun switchFlash(flashState: com.cabinInformationTechnologies.cabinCustomerCamera.CabinCustomerCameraActivity.FlashState?)
        fun indicate(pitch: Float, roll: Float)
    }

    interface Interactor : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor

    interface InteractorOutput : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput

    interface Router : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Router

}