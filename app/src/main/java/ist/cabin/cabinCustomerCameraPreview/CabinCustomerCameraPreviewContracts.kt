package ist.cabin.cabinCustomerCameraPreview

import ist.cabin.cabinCustomerBase.BaseContracts
import java.io.File

object CabinCustomerCameraPreviewContracts {

    interface View : BaseContracts.View {
        fun setupPage()
        fun checkPersmission(): Boolean
        fun requestPermission()
        fun takePicture(requestCode: Int)
        fun setMCurrentPhotoPath(path: String?)
        fun createFile(): File
    }

    interface Presenter : BaseContracts.Presenter {
        fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
        fun takePictureOnRequest(requestCode: Int)
    }

    interface Interactor : BaseContracts.Interactor {
        //TODO
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        //TODO
    }

    interface Router : BaseContracts.Router {
        //TODO
    }

}