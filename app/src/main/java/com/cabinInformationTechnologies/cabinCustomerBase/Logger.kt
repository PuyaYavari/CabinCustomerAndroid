package com.cabinInformationTechnologies.cabinCustomerBase

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.crashlytics.android.Crashlytics
import com.google.firebase.analytics.FirebaseAnalytics

object Logger: BaseContracts.Logger, BaseContracts.FirebaseLogger {

    //region local logger

    override fun debug(
        context: Context,
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
            failure(context, this::class.java.name, null, exception)
        }
    }

    override fun error(
        context: Context,
        location: String?,
        message: String,
        exception: Throwable
    ) {
        try {
            if (location != null) {
                Log.e("Error", "LOCATION: $location \nMESSAGE: $message", exception)
                Crashlytics.log(Log.ERROR, "Error", "LOCATION: $location \nMESSAGE: $message \nEXCEPTION: ${exception.toString()}" )
            } else {
                Log.e("Error", message, exception)
                Crashlytics.log(Log.ERROR, "Error", "MESSAGE: $message \nEXCEPTION: ${exception.toString()}" )
            }
            //TODO: SEND HANDLE THE REST
        } catch (exception: Exception) {
            failure(context, this::class.java.name, null, exception)
        }
    }

    override fun info(
        context: Context,
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
            failure(context, this::class.java.name, null, exception)
        }
    }

    override fun verbose(
        context: Context,
        location: String?,
        message: String,
        throwable: Throwable?
    ) {
        try {
            if (location != null)
                Log.v("Verbose", "LOCATION: $location \nMESSAGE: $message", throwable)
            else
                Log.v("Verbose", message, throwable)
            //TODO: SEND HANDLE THE REST
        } catch (exception: Exception) {
            failure(context, this::class.java.name, null, exception)
        }
    }

    override fun warn(
        context: Context,
        location: String?,
        message: String,
        throwable: Throwable?) {
        try {
            if (location != null) {
                if (throwable != null) {
                    Log.w("Warn", "LOCATION: $location \nMESSAGE: $message", throwable)
                    Crashlytics.log(
                        Log.WARN,
                        "WARN",
                        "LOCATION: $location \nMESSAGE: $message \nEXCEPTION: ${throwable.toString()}"
                    )
                } else {
                    Log.w("Warn", "LOCATION: $location \nMESSAGE: $message", null)
                    Crashlytics.log(Log.WARN, "WARN", "LOCATION: $location \nMESSAGE: $message")
                }
            } else {
                if (throwable != null) {
                    Log.w("Warn", message, throwable)
                    Crashlytics.log(Log.WARN, "WARN", "MESSAGE: $message \nEXCEPTION: ${throwable.toString()}" )
                } else {
                    Log.w("Warn", message, null)
                    Crashlytics.log(Log.WARN, "WARN", "MESSAGE: $message" )
                }
            }
            //TODO: SEND HANDLE THE REST
        } catch (exception: Exception) {
            failure(context, this::class.java.name, null, exception)
        }
    }

    override fun failure(
        context: Context,
        location: String?,
        message: String?,
        throwable: Throwable?
    ) {
        if (location != null && message != null && throwable != null) {
            Log.wtf("Failure", "LOCATION: $location \nMESSAGE: $message", throwable)
            Crashlytics.log(Log.ERROR, "WTF", "MESSAGE: $message \nTHROWABLE: ${throwable.toString()}" )
        } else if (throwable != null) {
            Log.wtf("Failure", null, throwable)
            Crashlytics.log(Log.ERROR, "WTF", "THROWABLE: ${throwable.toString()}" )
        } else {
            Log.wtf("Failure", "Unexpected Problem", null)
            Crashlytics.log(Log.ERROR, "WTF", "" )
        }
        //TODO: SEND HANDLE THE REST
    }

    //end region local logger

    //region Firebase event logging

    override fun login(context: Context, method: String) {
        val firebaseAnalytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(context)
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.METHOD, method)
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle)
    }

    private fun firebaseLog(context: Context) {
        val firebaseAnalytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(context)

    }

    //end region Firebase event logging

}