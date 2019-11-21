package com.cabinInformationTechnologies.cabin.fragments.ordersDetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.R

class ExtraditionProcedureStepsAdapter(val fragment: ExtraditionProcedureDialogFragment,
                                       private val myDataset: List<String>) :
    RecyclerView.Adapter<ExtraditionProcedureStepsAdapter.ExtraditionStepsViewHolder>() {
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    class ExtraditionStepsViewHolder(stepView: View) : RecyclerView.ViewHolder(stepView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExtraditionStepsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val stepView = inflater.inflate(R.layout.cabin_customer_extradition_stepbox, parent, false)
        return ExtraditionStepsViewHolder(
            stepView
        )
    }

    override fun onBindViewHolder(holder: ExtraditionStepsViewHolder, position: Int) {
        holder.itemView.apply {
            findViewById<TextView>(R.id.extradition_stepbox_step_number).text = (position+1).toString()
            findViewById<TextView>(R.id.extradition_stepbox_step).text = myDataset[position]
        }
    }

    override fun getItemCount(): Int = myDataset.size
}