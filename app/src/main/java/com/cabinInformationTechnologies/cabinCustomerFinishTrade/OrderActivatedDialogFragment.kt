package com.cabinInformationTechnologies.cabinCustomerFinishTrade

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.fragment.app.DialogFragment
import com.cabinInformationTechnologies.cabin.R

class OrderActivatedDialogFragment(private val  activity: CabinCustomerFinishTradeContracts.View): DialogFragment() {

    private lateinit var dialogView: View

    override fun onStart() {
        dialog?.window?.setLayout(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        super.onStart()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialogView = inflater.inflate(R.layout.cabin_customer_order_activated_dialog, container, false)
        dialogView.findViewById<Button>(R.id.order_activated_button).setOnClickListener {
            dismiss()
        }
        return dialogView
    }

    override fun dismiss() {
        super.dismiss()
        activity.finishActivity()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        activity.finishActivity()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        activity.finishActivity()
    }
}