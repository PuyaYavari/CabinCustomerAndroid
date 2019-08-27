package ist.cabin.cabinCustomerLogin_fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import ist.cabin.cabinCustomerBase.BaseFragment
import ist.cabin.cabincustomer.R

class CabinCustomerLoginFragment : BaseFragment(),
    CabinCustomerLoginContracts.View {

    var presenter: CabinCustomerLoginContracts.Presenter? =
        CabinCustomerLoginPresenter(this)
    private lateinit var pageView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_login, container, false)
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

    //region View

    private fun setupPage() {
        pageView.findViewById<Button>(R.id.login_button).setOnClickListener { presenter?.login() }
        pageView.findViewById<Button>(R.id.forget_password_button).setOnClickListener { presenter?.forgetPassword() }
        pageView.findViewById<EditText>(R.id.login_email_input).addTextChangedListener ( object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter?.setInputtedEmail(p0.toString())
                presenter?.switchLoginButton()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
        pageView.findViewById<EditText>(R.id.login_password_input).addTextChangedListener ( object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter?.setInputtedPassword(p0.toString())
                presenter?.switchLoginButton()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    override fun enableLoginButton() {
        pageView.findViewById<Button>(R.id.login_button).isEnabled = true
        pageView.findViewById<Button>(R.id.login_button).isClickable = true
        pageView.findViewById<Button>(R.id.login_button).alpha = 1.0F
    }

    override fun disableLoginbutton() {
        pageView.findViewById<Button>(R.id.login_button).isEnabled = false
        pageView.findViewById<Button>(R.id.login_button).isClickable = false
        pageView.findViewById<Button>(R.id.login_button).alpha = 0.5F
    }

    //endregion
}