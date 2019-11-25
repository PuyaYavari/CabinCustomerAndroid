package com.cabinInformationTechnologies.cabinCustomerBase

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.cabinInformationTechnologies.cabin.R
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import java.util.concurrent.atomic.AtomicInteger


abstract class CallbackResultActivity : AppCompatActivity(), BaseContracts.View {
    //FIXME: HAS ISSUES, MANY PROBLEMS ON GETCONTEXT
    var dialog: Dialog? = null

    override fun getActivityContext(): Context? {
        return this
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun showErrorDialog(error: String?) {
        dialog?.dismiss()
        val message = error ?: getString(R.string.default_error_message)
//        dialog = ErrorDialog.showWithMessage(message, this)
        Log.e("ERROR",message)
    }

    private val callbacks: MutableMap<Int, BaseContracts.ActivityResultListener> =
        HashMap()
    private val requestCode: AtomicInteger = AtomicInteger(0)
    protected fun startActivityForResult(
        intent: Intent?,
        callback: BaseContracts.ActivityResultListener
    ) {
        val newRequestCode: Int = requestCode.incrementAndGet()
        callbacks[newRequestCode] = callback
        startActivityForResult(intent, newRequestCode)
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        val callback = callbacks[requestCode]
        if (callback != null) {
            callback.onActivityResult(resultCode, data)
            callbacks.remove(requestCode)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
