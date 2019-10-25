package com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.R


class CabinCustomerAddExtraditionFragment : com.cabinInformationTechnologies.cabinCustomerBase.BaseFragment(),
    com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.CabinCustomerAddExtraditionContracts.View {

    var presenter: com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.CabinCustomerAddExtraditionContracts.Presenter? =
        com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.CabinCustomerAddExtraditionPresenter(
            this
        )
    private lateinit var pageView: View
    private lateinit var recyclerView: RecyclerView

    private lateinit var viewAdapter: com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.CabinCustomerAddExtraditionAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_extraditions_add_extradition, container, false)
        setupPage()
        return pageView
    }

    override fun onResume() {
        super.onResume()
        presenter?.onResume()
    }

    override fun onPause() {
        presenter?.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
        presenter = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter?.onCreate()
    }

    override fun onBackPressed() {
        activity!!.onBackPressed()
    }

    //region View

    override fun getApplicationContext(): Context {
        return activity!!.applicationContext
    }

    private fun setupPage() {
        (activity as com.cabinInformationTechnologies.cabinCustomerExtraditions.CabinCustomerExtraditionsActivity).hideHeaderHelperText()
        (activity as com.cabinInformationTechnologies.cabinCustomerExtraditions.CabinCustomerExtraditionsActivity).showBackArrow()
        (activity as com.cabinInformationTechnologies.cabinCustomerExtraditions.CabinCustomerExtraditionsActivity).hideCross()

        recyclerView = pageView.findViewById(R.id.add_extradition_products_recyclerview)

        val myDataset: List<com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.CabinCustomerAddExtraditionContracts.Product> =
            listOf(
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.ExtraditionProduct(
                    "1",
                    R.drawable.order_picture_example
                ),
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.ExtraditionProduct(
                    "2",
                    R.drawable.order_picture_example
                ),
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.ExtraditionProduct(
                    "3",
                    R.drawable.order_picture_example
                ),
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.ExtraditionProduct(
                    "4",
                    R.drawable.order_picture_example
                ),
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.ExtraditionProduct(
                    "5",
                    R.drawable.order_picture_example
                ),
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.ExtraditionProduct(
                    "6",
                    R.drawable.order_picture_example
                ),
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.ExtraditionProduct(
                    "7",
                    R.drawable.order_picture_example
                ),
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.ExtraditionProduct(
                    "8",
                    R.drawable.order_picture_example
                ),
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.ExtraditionProduct(
                    "9",
                    R.drawable.order_picture_example
                ),
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.ExtraditionProduct(
                    "10",
                    R.drawable.order_picture_example
                ),
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.ExtraditionProduct(
                    "11",
                    R.drawable.order_picture_example
                ),
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.ExtraditionProduct(
                    "12",
                    R.drawable.order_picture_example
                ),
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.ExtraditionProduct(
                    "13",
                    R.drawable.order_picture_example
                ),
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.ExtraditionProduct(
                    "14",
                    R.drawable.order_picture_example
                ),
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.ExtraditionProduct(
                    "15",
                    R.drawable.order_picture_example
                ),
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.ExtraditionProduct(
                    "16",
                    R.drawable.order_picture_example
                ),
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.ExtraditionProduct(
                    "17",
                    R.drawable.order_picture_example
                ),
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.ExtraditionProduct(
                    "18",
                    R.drawable.order_picture_example
                ),
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.ExtraditionProduct(
                    "19",
                    R.drawable.order_picture_example
                ),
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.ExtraditionProduct(
                    "20",
                    R.drawable.order_picture_example
                ),
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.ExtraditionProduct(
                    "21",
                    R.drawable.order_picture_example
                ),
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.ExtraditionProduct(
                    "22",
                    R.drawable.order_picture_example
                ),
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.ExtraditionProduct(
                    "23",
                    R.drawable.order_picture_example
                ),
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.ExtraditionProduct(
                    "24",
                    R.drawable.order_picture_example
                ),
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.ExtraditionProduct(
                    "25",
                    R.drawable.order_picture_example
                ),
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.ExtraditionProduct(
                    "26",
                    R.drawable.order_picture_example
                ),
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.ExtraditionProduct(
                    "27",
                    R.drawable.order_picture_example
                ),
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.ExtraditionProduct(
                    "28",
                    R.drawable.order_picture_example
                ),
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.ExtraditionProduct(
                    "29",
                    R.drawable.order_picture_example
                ),
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.ExtraditionProduct(
                    "30",
                    R.drawable.order_picture_example
                ),
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.ExtraditionProduct(
                    "31",
                    R.drawable.order_picture_example
                ),
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.ExtraditionProduct(
                    "32",
                    R.drawable.order_picture_example
                ),
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.ExtraditionProduct(
                    "33",
                    R.drawable.order_picture_example
                )
            ) //TODO: REMOVE

        viewAdapter =
            com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.CabinCustomerAddExtraditionAdapter(
                this,
                myDataset
            )
        val viewManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        pageView.findViewById<EditText>(R.id.add_extradition_extradition_reason_input).filters =
            arrayOf(
                if (presenter != null) InputFilter.LengthFilter(com.cabinInformationTechnologies.cabinCustomerBase.Constants.MAX_EXTRADITION_REASON_LENGTH)
                else InputFilter.LengthFilter(30))

        pageView.findViewById<Button>(R.id.add_extradition_add_button).setOnClickListener {
            presenter?.saveData()
            presenter?.moveToCongratulationsPage()
        }
    }

    override fun setupERListener(product: com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.addExtradition.CabinCustomerAddExtraditionContracts.Product) { //FIXME: MANY PROBLEMS :)
        pageView.findViewById<EditText>(R.id.add_extradition_extradition_reason_input).apply {
            Toast.makeText(this.context, product.temp.toString(), Toast.LENGTH_SHORT).show()
            setText(product.ER)
            addTextChangedListener(object :
                TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    product.ER = p0.toString()
                    product.temp += 1
                }
                override fun afterTextChanged(p0: Editable?) {}
            })
        }
    }

    //endregion
}