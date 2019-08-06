package ist.cabin.cabinCustomerCameraPreview

import android.app.Activity

class CabinCustomerCameraPreviewRouter(var activity: Activity?) : CabinCustomerCameraPreviewContracts.Router {

    override fun unregister() {
        activity = null
    }

    //region Router

    //TODO: Implement your Router methods here

    //endregion
}