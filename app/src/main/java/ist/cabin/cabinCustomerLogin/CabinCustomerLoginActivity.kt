package ist.cabin.cabinCustomerLogin

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.content.ContextCompat
import ist.cabin.cabinCustomerBase.BaseActivity
import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerOrders.CabinCustomerOrdersActivity
import ist.cabin.cabincustomer.R
import kotlinx.android.synthetic.main.cabin_customer_login.*


class CabinCustomerLoginActivity : BaseActivity(),
    CabinCustomerLoginContracts.View {

    var presenter: CabinCustomerLoginContracts.Presenter? = CabinCustomerLoginPresenter(this)

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cabin_customer_login)
        presenter?.onCreate(intent.extras)

        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right)
        setupPage()

        demo_login.setOnClickListener {
            val orders: BaseContracts.View = CabinCustomerOrdersActivity() // TODO: DELETE THIS LINE
            val intent = Intent(this, orders::class.java) // TODO: DELETE THIS LINE
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // TODO: DELETE THIS LINE
            ContextCompat.startActivity(this.applicationContext, intent, Bundle.EMPTY) // TODO: DELETE THIS LINE
        }
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
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left)
    }

    //endregion

    //region View

    override fun setupPage() {
        login_button.setOnClickListener { presenter?.login() }
        forget_password_button.setOnClickListener { presenter?.forgetPassword() }
        login_email_input.addTextChangedListener ( object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter?.setInputtedEmail(p0.toString())
                presenter?.switchLoginButton()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
        login_password_input.addTextChangedListener ( object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter?.setInputtedPassword(p0.toString())
                presenter?.switchLoginButton()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    override fun enableLoginButton() {
        login_button.isEnabled = true
        login_button.isClickable = true
        login_button.alpha = 1.0F
    }

    override fun disableLoginbutton() {
        login_button.isEnabled = false
        login_button.isClickable = false
        login_button.alpha = 0.5F
    }

    //endregion
}