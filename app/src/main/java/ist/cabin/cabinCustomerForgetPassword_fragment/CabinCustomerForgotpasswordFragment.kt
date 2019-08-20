package ist.cabin.cabincustomer.fragments.forgetPassword

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

class CabinCustomerForgotpasswordFragment : BaseFragment(), CabinCustomerForgotpasswordContracts.View {

    var presenter: CabinCustomerForgotpasswordContracts.Presenter? = CabinCustomerForgotpasswordPresenter(this)
    private lateinit var pageView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_forgotpassword, container, false)
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
        pageView.findViewById<Button>(R.id.forgetpassword_confirm_button).setOnClickListener { presenter?.confirm() }
        pageView.findViewById<EditText>(R.id.forgetpassword_email).addTextChangedListener ( object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter?.setInputtedEmail(p0.toString())
                presenter?.switchConfirmationButton()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    override fun disableConfirmationButton() {
        pageView.findViewById<Button>(R.id.forgetpassword_confirm_button).isEnabled = false
        pageView.findViewById<Button>(R.id.forgetpassword_confirm_button).isClickable = false
        pageView.findViewById<Button>(R.id.forgetpassword_confirm_button).alpha = 0.5f
    }

    override fun enableConfirmationButton() {
        pageView.findViewById<Button>(R.id.forgetpassword_confirm_button).isEnabled = true
        pageView.findViewById<Button>(R.id.forgetpassword_confirm_button).isClickable = true
        pageView.findViewById<Button>(R.id.forgetpassword_confirm_button).alpha = 1f
    }

    //endregion
}