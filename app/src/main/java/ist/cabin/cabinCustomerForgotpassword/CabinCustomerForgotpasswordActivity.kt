package ist.cabin.cabinCustomerForgotpassword

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import ist.cabin.cabinCustomerBase.BaseActivity
import ist.cabin.cabincustomer.R
import kotlinx.android.synthetic.main.cabin_customer_forgotpassword.*

class CabinCustomerForgotpasswordActivity : BaseActivity(),
    CabinCustomerForgotpasswordContracts.View {

    var presenter: CabinCustomerForgotpasswordContracts.Presenter? = CabinCustomerForgotpasswordPresenter(this)

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cabin_customer_forgotpassword)
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
        forgetpassword_confirm_button.setOnClickListener { presenter?.confirm() }
        forgetpassword_email.addTextChangedListener ( object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter?.setInputtedEmail(p0.toString())
                presenter?.switchConfirmationButton()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    override fun disableConfirmationButton() {
        forgetpassword_confirm_button.isEnabled = false
        forgetpassword_confirm_button.isClickable = false
        forgetpassword_confirm_button.alpha = 0.5f
    }

    override fun enableConfirmationButton() {
        forgetpassword_confirm_button.isEnabled = true
        forgetpassword_confirm_button.isClickable = true
        forgetpassword_confirm_button.alpha = 1f
    }
    //endregion
}