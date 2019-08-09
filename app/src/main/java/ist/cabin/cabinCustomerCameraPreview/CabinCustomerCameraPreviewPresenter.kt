package ist.cabin.cabinCustomerCameraPreview

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast

class CabinCustomerCameraPreviewPresenter(var view: CabinCustomerCameraPreviewContracts.View?) :
    CabinCustomerCameraPreviewContracts.Presenter, CabinCustomerCameraPreviewContracts.InteractorOutput {

    var interactor: CabinCustomerCameraPreviewContracts.Interactor? = CabinCustomerCameraPreviewInteractor(this)
    var router: CabinCustomerCameraPreviewContracts.Router? = null

    private val PERMISSION_REQUEST_CODE: Int = 101

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerCameraPreviewRouter(activity)

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

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {

                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    view!!.takePicture(requestCode)
                } else {
                    Toast.makeText(view!!.getActivityContext(), "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }

            else -> {

            }
        }
    }

    override fun takePictureOnRequest(requestCode: Int) {
        if (view!!.checkPersmission()) view!!.takePicture(requestCode) else view!!.requestPermission()
    }



    //endregion

    //region InteractorOutput

    //TODO: Implement your InteractorOutput methods here

    //endregion
}