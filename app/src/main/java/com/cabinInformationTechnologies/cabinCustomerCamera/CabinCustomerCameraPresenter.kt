package com.cabinInformationTechnologies.cabinCustomerCamera

import android.app.Activity
import android.os.Bundle
import kotlin.math.abs


class CabinCustomerCameraPresenter(var view: com.cabinInformationTechnologies.cabinCustomerCamera.CabinCustomerCameraContracts.View?) :
    com.cabinInformationTechnologies.cabinCustomerCamera.CabinCustomerCameraContracts.Presenter,
    com.cabinInformationTechnologies.cabinCustomerCamera.CabinCustomerCameraContracts.InteractorOutput {

    var interactor: com.cabinInformationTechnologies.cabinCustomerCamera.CabinCustomerCameraContracts.Interactor? =
        com.cabinInformationTechnologies.cabinCustomerCamera.CabinCustomerCameraInteractor(this)
    var router: com.cabinInformationTechnologies.cabinCustomerCamera.CabinCustomerCameraContracts.Router? = null

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = com.cabinInformationTechnologies.cabinCustomerCamera.CabinCustomerCameraRouter(activity)

        bundle?.let {
            //you can delete this if there's no need to get extras from the intent
        }
    }

    override fun onDestroy() {
        view = null
        interactor?.unregister()
        interactor = null
        router?.unregister()
        router = null
    }

    //endregion

    //region Presenter

    override fun checkPermissions() {
        if (view!!.hasNoPermissions()) {
            view!!.requestPermission()
        }
    }

    override fun cameraSwitch(pitch: Float, roll: Float) {
        if (pitch > 10 || pitch < -10 || roll > 10 || roll < -10)
            view!!.disableCamera()
        else
            view!!.enableCamera()
    }

    override fun switchCamera(cameraStatus: com.cabinInformationTechnologies.cabinCustomerCamera.CabinCustomerCameraActivity.CameraState?) {
        if(cameraStatus == com.cabinInformationTechnologies.cabinCustomerCamera.CabinCustomerCameraActivity.CameraState.BACK)
            view!!.frontCamera()
        else
            view!!.backCamera()
    }

    override fun switchFlash(flashState: com.cabinInformationTechnologies.cabinCustomerCamera.CabinCustomerCameraActivity.FlashState?) {
        if(flashState == com.cabinInformationTechnologies.cabinCustomerCamera.CabinCustomerCameraActivity.FlashState.TORCH)
            view!!.flashOff()
        else
            view!!.flashOn()
    }

    override fun indicate (pitch: Float, roll: Float){
        view!!.setIndicatorVerticalPosition(pitch)
        view!!.setIndicatorRoll(roll)
        if (abs(pitch) > abs(roll))
            view!!.setIndicatorAlpha(1f - abs(pitch / 10))
        else
            view!!.setIndicatorAlpha(1f - abs(roll / 10))
    }

    //endregion

    //region InteractorOutput


    //endregion
}