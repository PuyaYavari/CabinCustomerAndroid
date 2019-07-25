package ist.cabin.cabinCustomerRegister

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import ist.cabin.cabinCustomerBase.BaseActivity
import ist.cabin.cabincustomer.R
import kotlinx.android.synthetic.main.cabin_customer_register.*

class CabinCustomerRegisterActivity : BaseActivity(), CabinCustomerRegisterContracts.View {

    var presenter: CabinCustomerRegisterContracts.Presenter? = CabinCustomerRegisterPresenter(this)

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cabin_customer_register)
        presenter?.onCreate(intent.extras)

        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
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
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right)
    }

    //endregion

    //region View

    override fun setupPage() {
        register_man_button.background.alpha = 0
        register_man_button.setImageResource(R.drawable.man_icon_purple)
        register_man_button.setOnClickListener {
            presenter?.selectMan()
            presenter?.switchContinueButton()
        }
        register_woman_button.background.alpha = 0
        register_woman_button.setImageResource(R.drawable.woman_icon_purple)
        register_woman_button.setOnClickListener {
            presenter?.selectWoman()
            presenter?.switchContinueButton()
        }
        register_email_input.requestFocus()
        register_email_input.addTextChangedListener ( object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter?.setInputtedEmail(p0.toString())
                presenter?.switchContinueButton()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
        register_password_input.addTextChangedListener ( object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter?.setInputtedPassword(p0.toString())
                presenter?.switchContinueButton()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
        register_password_confirmation_input.addTextChangedListener ( object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter?.setInputtedPasswordConfirmation(p0.toString())
                presenter?.switchContinueButton()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
        register_continue_button.setOnClickListener { presenter?.continueToAgreement() }
    }

    override fun enableContinueButton() {
        register_continue_button.alpha = 1f
        register_continue_button.isClickable = true
        register_continue_button.isEnabled = true
    }

    override fun disableContinueButton() {
        register_continue_button.alpha = 0.5f
        register_continue_button.isClickable = false
        register_continue_button.isEnabled = false
    }

    override fun selectMan() {
        register_man_button.background.alpha = 255
        register_man_button.setImageResource(R.drawable.man_icon_white)
    }

    override fun unselectMan() {
        register_man_button.background.alpha = 0
        register_man_button.setImageResource(R.drawable.man_icon_purple)
    }

    override fun selectWoman() {
        register_woman_button.background.alpha = 255
        register_woman_button.setImageResource(R.drawable.woman_icon_white)
    }

    override fun unselectWoman() {
        register_woman_button.background.alpha = 0
        register_woman_button.setImageResource(R.drawable.woman_icon_purple)
    }

    //endregion
}