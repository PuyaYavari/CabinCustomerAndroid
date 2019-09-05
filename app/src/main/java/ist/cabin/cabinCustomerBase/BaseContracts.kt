package ist.cabin.cabinCustomerBase

import android.content.Context
import android.os.Bundle

interface BaseContracts {

    interface View {
        fun getActivityContext(): Context?
        fun showErrorDialog(error: String?)
    }

    interface Presenter {
        fun onCreate(bundle: Bundle? = null) {}
        fun onResume() {}
        fun onPause() {}
        fun onDestroy()
    }

    interface Interactor {
        fun unregister()
    }

    interface InteractorOutput //does nothing for now

    interface Router {
        fun unregister()
    }

    interface Product {
        fun getID(): String
        //TODO: COLOR, SIZE, PRICE, PRICE UNIT
    }

}