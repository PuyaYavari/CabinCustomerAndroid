package ist.cabin.cabinCustomerBase

import android.util.Log

object Logger: BaseContracts.Logger {
    override fun debug(
        location: String?,
        message: String,
        exception: Exception?
    ) {
        try {
            if (location != null)
                Log.d("Debug", "LOCATION: $location \nMESSAGE: $message", exception)
            else
                Log.d("Debug", message, exception)
            //TODO: SEND HANDLE THE REST
        } catch (exception: Exception) {
            failure(this::class.java.name, null, exception)
        }
    }

    override fun error(
        location: String?,
        message: String,
        exception: Throwable
    ) {
        try {
            if (location != null)
                Log.e("Error","LOCATION: $location \nMESSAGE: $message", exception)
            else
                Log.e("Error", message, exception)
            //TODO: SEND HANDLE THE REST
        } catch (exception: Exception) {
            failure(this::class.java.name, null, exception)
        }
    }

    override fun info(
        location: String?,
        message: String,
        exception: Exception?
    ) {
        try {
            if (location != null)
                Log.i("Info", "LOCATION: $location \nMESSAGE: $message", exception)
            else
                Log.i("Info", message, exception)
            //TODO: SEND HANDLE THE REST
        } catch (exception: Exception) {
            failure(this::class.java.name, null, exception)
        }
    }

    override fun verbose(
        location: String?,
        message: String,
        exception: Exception?
    ) {
        try {
            if (location != null)
                Log.v("Verbose", "LOCATION: $location \nMESSAGE: $message", exception)
            else
                Log.v("Verbose", message, exception)
            //TODO: SEND HANDLE THE REST
        } catch (exception: Exception) {
            failure(this::class.java.name, null, exception)
        }
    }

    override fun warn(location: String?, message: String, exception: Exception?) {
        try {
            if (location != null)
                Log.w("Warn", "LOCATION: $location \nMESSAGE: $message", exception)
            else
                Log.w("Warn", message, exception)
            //TODO: SEND HANDLE THE REST
        } catch (exception: Exception) {
            failure(this::class.java.name, null, exception)
        }
    }

    override fun failure(
        location: String?,
        message: String?,
        exception: Exception?
    ) {
        if (location != null && message != null && exception != null)
            Log.wtf("Failure", "LOCATION: $location \nMESSAGE: $message", exception)
        else if (exception != null)
            Log.wtf("Failure", null, exception)
        else
            Log.wtf("Failure", "Unexpected Problem", null)
        //TODO: SEND HANDLE THE REST
    }


}