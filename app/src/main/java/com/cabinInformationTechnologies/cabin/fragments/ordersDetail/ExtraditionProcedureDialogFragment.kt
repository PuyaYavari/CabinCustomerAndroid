package com.cabinInformationTechnologies.cabin.fragments.ordersDetail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabin.fragments.ordersDetail.adapter.Headerbox

class ExtraditionProcedureDialogFragment(val headerbox: Headerbox): DialogFragment() {

    private lateinit var dialogView: View

    override fun onStart() {
        dialog?.window?.setLayout(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        super.onStart()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialogView = inflater.inflate(R.layout.cabin_customer_extradition_procedure_dialog, container, false)
        dialogView.findViewById<ImageButton>(R.id.extradition_procedure_dialog_close_button).setOnClickListener {
            dismiss()
        }
        dialogView.findViewById<TextView>(R.id.extradition_procedure_description).text = headerbox.returnDescription

        val viewAdapter = ExtraditionProcedureStepsAdapter(this, headerbox.returnSteps)
        val viewManager = LinearLayoutManager(this.context)

        dialogView.findViewById<RecyclerView>(R.id.extradition_procedure_steps_recycler_view).apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        dialogView.findViewById<TextView>(R.id.extradition_procedure_cargo_price).text = headerbox.returnPayment
        dialogView.findViewById<TextView>(R.id.extradition_procedure_seller_name).text = headerbox.sellerName
        dialogView.findViewById<TextView>(R.id.extradition_procedure_seller_address).text = headerbox.sellerAddress
        dialogView.findViewById<TextView>(R.id.extradition_procedure_seller_phone).text = headerbox.sellerPhone
        return dialogView
    }
}