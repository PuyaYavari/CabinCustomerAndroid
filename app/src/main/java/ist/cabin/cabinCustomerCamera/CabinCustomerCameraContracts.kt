package ist.cabin.cabinCustomerCamera

import ist.cabin.cabinCustomerBase.BaseContracts

object CabinCustomerCameraContracts {

    interface View : BaseContracts.View {
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

    interface Presenter : BaseContracts.Presenter {
        fun cameraSwitch(pitch: Float, roll: Float)
        fun checkPermissions()
        fun switchCamera(cameraStatus: CabinCustomerCameraActivity.CameraState?)
        fun switchFlash(flashState: CabinCustomerCameraActivity.FlashState?)
        fun indicate(pitch: Float, roll: Float)
    }

    interface Interactor : BaseContracts.Interactor

    interface InteractorOutput : BaseContracts.InteractorOutput

    interface Router : BaseContracts.Router

}