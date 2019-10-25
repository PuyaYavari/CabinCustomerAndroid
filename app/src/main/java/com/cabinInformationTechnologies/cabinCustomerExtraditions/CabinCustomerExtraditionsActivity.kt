package com.cabinInformationTechnologies.cabinCustomerExtraditions

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import com.cabinInformationTechnologies.cabin.R
import kotlinx.android.synthetic.main.cabin_customer_extraditions_main.*

class CabinCustomerExtraditionsActivity : com.cabinInformationTechnologies.cabinCustomerBase.BaseActivity(),
    com.cabinInformationTechnologies.cabinCustomerExtraditions.CabinCustomerExtraditionsContracts.View {

    var presenter: com.cabinInformationTechnologies.cabinCustomerExtraditions.CabinCustomerExtraditionsContracts.Presenter? =
        com.cabinInformationTechnologies.cabinCustomerExtraditions.CabinCustomerExtraditionsPresenter(this)

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cabin_customer_extraditions_main)
        presenter?.onCreate(intent.extras)

        setupPage()
    }

    override fun onResume() {
        super.onResume()
        presenter?.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter?.onPause()
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }

    //endregion

    //region View

    private fun setupPage() {
        findViewById<ImageButton>(R.id.extraditions_back_button).setOnClickListener { onBackPressed() }
        findViewById<ImageButton>(R.id.extraditions_close_button).setOnClickListener { onBackPressed() }
    }

    override fun showBackArrow() {
        extraditions_back_button.visibility = View.VISIBLE
    }

    override fun hideBackArrow() {
        extraditions_back_button.visibility = View.GONE
    }

    override fun showCross() {
        extraditions_close_button.visibility = View.VISIBLE
    }

    override fun hideCross() {
        extraditions_close_button.visibility = View.GONE
    }

    override fun hideHeaderHelperText() {
        extraditions_header_helper_text_layout.visibility = View.GONE
    }

    override fun showHeaderHelperText(text: String) {
        extraditions_header_helper_text_layout.visibility = View.VISIBLE
        extraditions_header_helper_text.text = text
    }

    //endregion
}