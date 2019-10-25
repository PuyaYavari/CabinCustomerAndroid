package com.cabinInformationTechnologies.cabinCustomerCameraPreview

import java.io.File

object CabinCustomerCameraPreviewContracts {

    interface View : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.View {
        fun setupPage()
        fun checkPersmission(): Boolean
        fun requestPermission()
        fun takePicture(requestCode: Int)
        fun setMCurrentPhotoPath(path: String?)
        fun createFile(): File
    }

    interface Presenter : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Presenter {
        fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
        fun takePictureOnRequest(requestCode: Int)
    }

    interface Interactor : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Interactor {
        //TODO
    }

    interface InteractorOutput : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.InteractorOutput {
        //TODO
    }

    interface Router : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.Router {
        //TODO
    }

}