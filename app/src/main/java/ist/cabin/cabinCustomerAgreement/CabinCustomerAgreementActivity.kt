package ist.cabin.cabinCustomerAgreement

import android.os.Bundle
import ist.cabin.cabinCustomerBase.BaseActivity
import ist.cabin.cabincustomer.R
import kotlinx.android.synthetic.main.cabin_customer_register_agreement.*

class CabinCustomerAgreementActivity : BaseActivity(),
    CabinCustomerAgreementContracts.View {

    var presenter: CabinCustomerAgreementContracts.Presenter? =
        CabinCustomerAgreementPresenter(this)

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cabin_customer_register_agreement)
        presenter?.onCreate(intent.extras)

        overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.slide_out_to_top)
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

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_from_top, R.anim.slide_out_to_bottom)
    }

    //endregion

    //region View

    override fun setupPage() {
        agreement_back_button.setOnClickListener { presenter?.goBack() }
        agreement_confirmation_button.setOnClickListener { presenter?.accept() }
    }

    //endregion
}