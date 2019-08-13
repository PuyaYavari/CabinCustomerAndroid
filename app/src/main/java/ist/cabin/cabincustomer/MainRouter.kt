package ist.cabin.cabincustomer

import android.app.Activity

class MainRouter(var activity : Activity?) : MainContracts.Router {
    override fun unregister() {
        activity = null
    }

}