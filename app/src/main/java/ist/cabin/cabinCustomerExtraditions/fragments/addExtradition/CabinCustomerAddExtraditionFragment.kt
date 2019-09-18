package ist.cabin.cabinCustomerExtraditions.fragments.addExtradition

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
import ist.cabin.cabinCustomerBase.BaseFragment
import ist.cabin.cabinCustomerExtraditions.CabinCustomerExtraditionsActivity
import ist.cabin.cabinCustomerBase.Constants
import ist.cabin.cabincustomer.R


class CabinCustomerAddExtraditionFragment : BaseFragment(),
    CabinCustomerAddExtraditionContracts.View {

    var presenter: CabinCustomerAddExtraditionContracts.Presenter? =
        CabinCustomerAddExtraditionPresenter(this)
    private lateinit var pageView: View
    private lateinit var recyclerView: RecyclerView

    private lateinit var viewAdapter: CabinCustomerAddExtraditionAdapter

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
        (activity as CabinCustomerExtraditionsActivity).hideHeaderHelperText()
        (activity as CabinCustomerExtraditionsActivity).showBackArrow()
        (activity as CabinCustomerExtraditionsActivity).hideCross()

        recyclerView = pageView.findViewById(R.id.add_extradition_products_recyclerview)

        val myDataset: List<CabinCustomerAddExtraditionContracts.Product> =
            listOf(ExtraditionProduct("1", R.drawable.order_picture_example),
                ExtraditionProduct("2", R.drawable.order_picture_example),
                ExtraditionProduct("3", R.drawable.order_picture_example),
                ExtraditionProduct("4", R.drawable.order_picture_example),
                ExtraditionProduct("5", R.drawable.order_picture_example),
                ExtraditionProduct("6", R.drawable.order_picture_example),
                ExtraditionProduct("7", R.drawable.order_picture_example),
                ExtraditionProduct("8", R.drawable.order_picture_example),
                ExtraditionProduct("9", R.drawable.order_picture_example),
                ExtraditionProduct("10", R.drawable.order_picture_example),
                ExtraditionProduct("11", R.drawable.order_picture_example),
                ExtraditionProduct("12", R.drawable.order_picture_example),
                ExtraditionProduct("13", R.drawable.order_picture_example),
                ExtraditionProduct("14", R.drawable.order_picture_example),
                ExtraditionProduct("15", R.drawable.order_picture_example),
                ExtraditionProduct("16", R.drawable.order_picture_example),
                ExtraditionProduct("17", R.drawable.order_picture_example),
                ExtraditionProduct("18", R.drawable.order_picture_example),
                ExtraditionProduct("19", R.drawable.order_picture_example),
                ExtraditionProduct("20", R.drawable.order_picture_example),
                ExtraditionProduct("21", R.drawable.order_picture_example),
                ExtraditionProduct("22", R.drawable.order_picture_example),
                ExtraditionProduct("23", R.drawable.order_picture_example),
                ExtraditionProduct("24", R.drawable.order_picture_example),
                ExtraditionProduct("25", R.drawable.order_picture_example),
                ExtraditionProduct("26", R.drawable.order_picture_example),
                ExtraditionProduct("27", R.drawable.order_picture_example),
                ExtraditionProduct("28", R.drawable.order_picture_example),
                ExtraditionProduct("29", R.drawable.order_picture_example),
                ExtraditionProduct("30", R.drawable.order_picture_example),
                ExtraditionProduct("31", R.drawable.order_picture_example),
                ExtraditionProduct("32", R.drawable.order_picture_example),
                ExtraditionProduct("33", R.drawable.order_picture_example)) //TODO: REMOVE

        viewAdapter = CabinCustomerAddExtraditionAdapter(this, myDataset)
        val viewManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        pageView.findViewById<EditText>(R.id.add_extradition_extradition_reason_input).filters =
            arrayOf(
                if (presenter != null) InputFilter.LengthFilter(Constants.MAX_EXTRADITION_REASON_LENGTH)
                else InputFilter.LengthFilter(30))

        pageView.findViewById<Button>(R.id.add_extradition_add_button).setOnClickListener {
            presenter?.saveData()
            presenter?.moveToCongratulationsPage()
        }
    }

    override fun setupERListener(product: CabinCustomerAddExtraditionContracts.Product) { //FIXME: MANY PROBLEMS :)
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