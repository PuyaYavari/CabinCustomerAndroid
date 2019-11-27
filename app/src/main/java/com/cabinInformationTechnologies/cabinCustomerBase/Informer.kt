package com.cabinInformationTechnologies.cabinCustomerBase

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import com.cabinInformationTechnologies.cabin.R

class Informer: BaseContracts.Feedbacker {

    //region: Toast

    /** Shows a simple, short duration Toast containing the message
     *
     *  @param context: Current context
     *  @param message: Message to show
     */
    override fun feedback(context: Context, message: String) {
        toast(context, message, null)
    }

    /** Shows a simple
     *
     *  @param context: Current context
     *  @param message: Message to show
     *  @param length: Length of the Toast message, default is short
     */
    override fun feedback(context: Context, message: String, length: Int) {
        toast(context, message, length)
    }

    //endregion: Toast

    /** Central toast generator, takes parameters from other functions and creates toasts.
     *
     *  @param context: Current context
     *  @param message: Message to show
     *  @param length: Length of the Toast message
     */
    private fun toast(context: Context, message: String, length: Int?) {
        if (length != null) {
            try {
                Toast.makeText(
                    context,
                    message,
                    length
                ).show()
            } catch (exception: Exception) {
                Logger.error(
                    context,
                    this::class.java.name,
                    "exception while displaying toast.",
                    exception
                )
                Toast.makeText(
                    context,
                    "Error",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            try {
                Toast.makeText(
                    context,
                    message,
                    Toast.LENGTH_SHORT
                ).show()
            } catch (exception: Exception) {
                Logger.error(
                    context,
                    this::class.java.name,
                    "exception while displaying toast.",
                    exception
                )
                Toast.makeText(
                    context,
                    "Error",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    //region: Dialog
    /** Shows an alert dialog containing the message,
     *  the positive button of the dialog will invoke the returnFunction.
     *
     *  @param context: Current context
     *  @param message: Message to show
     *  @param retryFunction: Function to alert on positive button, usually a failed function to retry.
     */
    override fun feedback(context: Context, message: String, retryFunction: () -> Unit) {
        try {
            AlertDialog.Builder(context)
                .setTitle(null)
                .setMessage(message)
                .setPositiveButton(R.string.retry) { dialogInterface, _ ->
                    dialogInterface.dismiss()
                    retryFunction.invoke()
                }
                .setCancelable(false)
                .show()
        } catch (exception: Exception) {
            Logger.error(
                context,
                this::class.java.name,
                "exception while displaying dialog.",
                exception
            )
            toast(
                context,
                message,
                Toast.LENGTH_SHORT
            )
        }
    }

    /** Shows an alert dialog containing the message with the given title,
     *  the positive button of the dialog will invoke the returnFunction.
     *  WARNING: THIS FUNCTION BLOCKS APPLICATION
     *
     *  @param context: Current context
     *  @param title: Dialogs title
     *  @param message: Message to show
     *  @param retryFunction: Function to alert on positive button, usually a failed function to retry.
     */
    override fun feedback(
        context: Context,
        title: String,
        message: String,
        retryFunction: () -> Unit
    ) {
        try {
            AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.retry) { dialogInterface, _ ->
                    dialogInterface.dismiss()
                    retryFunction.invoke()
                }
                .setCancelable(false)
                .show()
        } catch (exception: Exception) {
            Logger.error(
                context,
                this::class.java.name,
                "exception while displaying dialog.",
                exception
            )
            toast(
                context,
                message,
                Toast.LENGTH_SHORT
            )
        }
    }

    /** Shows an alert dialog containing the message with the given title,
     *  the neutral button will close the dialog and do nothing.
     *
     *  @param context: Current context
     *  @param title: Dialogs title
     *  @param message: Message to show
     *  @param neutralText: Neutral button text.
     */
    override fun feedback(context: Context, title: String, message: String, neutralText: String) {
        try {
            AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setNeutralButton(neutralText) { dialogInterface, _ ->
                    dialogInterface.dismiss()
                }
                .setCancelable(true)
                .show()
        } catch (exception: Exception) {
            Logger.error(
                context,
                this::class.java.name,
                "exception while displaying dialog.",
                exception
            )
            toast(
                context,
                message,
                Toast.LENGTH_SHORT
            )
        }
    }

    /** Shows an alert dialog containing the message with the given title,
     *  the positive button of the dialog will invoke the returnFunction,
     *  the neutral button will close the dialog and do nothing.
     *
     *  @param context: Current context
     *  @param title: Dialogs title
     *  @param message: Message to show
     *  @param neutralText: Neutral button text.
     *  @param retryFunction: Function to alert on positive button, usually a failed function to retry.
     */
    override fun feedback(
        context: Context,
        title: String,
        message: String,
        neutralText: String,
        retryFunction: () -> Unit
    ) {
        try {
            AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.retry) { dialogInterface, _ ->
                    dialogInterface.dismiss()
                    retryFunction.invoke()
                }
                .setNeutralButton(neutralText) { dialogInterface, _ ->
                    dialogInterface.dismiss()
                }
                .setCancelable(true)
                .show()
        } catch (exception: Exception) {
            Logger.error(
                context,
                this::class.java.name,
                "exception while displaying dialog.",
                exception
            )
            toast(
                context,
                message,
                Toast.LENGTH_SHORT
            )
        }
    }

    /** Shows an alert dialog containing the message with the given title,
     *  the positive button of the dialog will invoke the returnFunction,
     *  the negative button will close the current page.
     *
     *  @param context: Current context
     *  @param navController: Navigation controller of the current navigation host fragment.
     *  @param title: Dialogs title
     *  @param message: Message to show
     *  @param retryFunction: Function to alert on positive button, usually a failed function to retry.
     */
    override fun feedback(
        context: Context,
        navController: NavController,
        title: String,
        message: String,
        retryFunction: () -> Unit
    ) {
        try {
            AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.retry) { dialogInterface, _ ->
                    dialogInterface.dismiss()
                    retryFunction.invoke()
                }
                .setNegativeButton(R.string.okay) { dialogInterface, _ ->
                    dialogInterface.dismiss()
                    navController.popBackStack()
                }
                .setCancelable(false)
                .show()
        } catch (exception: Exception) {
            Logger.error(
                context,
                this::class.java.name,
                "exception while displaying dialog.",
                exception
            )
            toast(
                context,
                message,
                Toast.LENGTH_SHORT
            )
            navController.popBackStack()
        }
    }


    /** Shows an alert dialog containing the message with the given title,
     *  the positive button of the dialog will invoke the positiveFunction and
     *  the negative button of the dialog will invoke the negativeFunction.
     *
     *  @param context: Current context
     *  @param title: Dialogs title
     *  @param message: Message to show
     *  @param positiveText: Positive buttons text
     *  @param positiveFunction: Positive buttons function
     *  @param negativeText: Negative buttons text
     *  @param negativeFunction: Negative buttons function
     */
    override fun feedback(
        context: Context,
        title: String,
        message: String,
        positiveText: String,
        positiveFunction: () -> Unit,
        negativeText: String,
        negativeFunction: () -> Unit
    ) {
        try {
            AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveText) { dialogInterface, _ ->
                    dialogInterface.dismiss()
                    positiveFunction.invoke()
                }
                .setNegativeButton(negativeText) { dialogInterface, _ ->
                    dialogInterface.dismiss()
                    negativeFunction.invoke()
                }
                .setCancelable(false)
                .show()
        } catch (exception: Exception) {
            Logger.error(
                context,
                this::class.java.name,
                "exception while displaying dialog.",
                exception
            )
            toast(
                context,
                message,
                Toast.LENGTH_SHORT
            )
        }
    }

    /** Shows an alert dialog containing the message with the given title,
     *  the neutral button of the dialog will invoke the neutralFunction and
     *  the positive button of the dialog will invoke the positiveFunction and
     *  the negative button of the dialog will invoke the negativeFunction.
     *
     *  @param context: Current context
     *  @param title: Dialogs title
     *  @param message: Message to show
     *  @param neutralText: Neutral button text
     *  @param neutralFunction: Neutral button function
     *  @param positiveText: Positive buttons text
     *  @param positiveFunction: Positive buttons function
     *  @param negativeText: Negative buttons text
     *  @param negativeFunction: Negative buttons function
     */
    override fun feedback(
        context: Context,
        title: String,
        message: String,
        neutralText: String?,
        neutralFunction: (() -> Unit)?,
        positiveText: String?,
        positiveFunction: (() -> Unit)?,
        negativeText: String?,
        negativeFunction: (() -> Unit)?
    ) {
        try {
            AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setNeutralButton(neutralText) { dialogInterface, _ ->
                    dialogInterface.dismiss()
                    negativeFunction?.invoke()
                }
                .setPositiveButton(positiveText) { dialogInterface, _ ->
                    dialogInterface.dismiss()
                    positiveFunction?.invoke()
                }
                .setNegativeButton(negativeText) { dialogInterface, _ ->
                    dialogInterface.dismiss()
                    negativeFunction?.invoke()
                }
                .setCancelable(false)
                .show()
        } catch (exception: Exception) {
            Logger.error(
                context,
                this::class.java.name,
                "exception while displaying dialog.",
                exception
            )
            toast(
                context,
                message,
                Toast.LENGTH_SHORT
            )
        }
    }

    //endregion: Dialog
}