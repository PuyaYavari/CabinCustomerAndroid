package ist.cabin.cabinCustomerRegistration.fragments.loginRegister

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.motion.widget.MotionLayout
import ist.cabin.cabinCustomerBase.BaseFragment
import ist.cabin.cabinCustomerBase.baseAbstracts.Sex
import ist.cabin.cabinCustomerBase.models.local.MODELUser
import ist.cabin.cabinCustomerRegistration.CabinCustomerRegistrationActivity
import ist.cabin.cabincustomer.R

class CabinCustomerLoginRegisterFragment : BaseFragment(),
    CabinCustomerLoginRegisterContracts.View {

    var presenter: CabinCustomerLoginRegisterContracts.Presenter? =
        CabinCustomerLoginRegisterPresenter(this)
    private lateinit var pageView: View
    private lateinit var cardsTransitionContainer: MotionLayout
    private lateinit var headerTransitionContainer: MotionLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_login_register, container, false)
        //FIXME: CHANGE GOOGLE AND FACEBOOK ICONS TO PNG
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
        setupLoginPage()
        setupRegisterPage()
        cardsTransitionContainer = pageView.findViewById(R.id.login_register_card_motion_layout)
        cardsTransitionContainer.setTransition(R.id.loginRegisterNothingVisible, R.id.loginVisible)
        cardsTransitionContainer.transitionToEnd()

        headerTransitionContainer = pageView.findViewById(R.id.login_register_header_motion_layout)
        headerTransitionContainer.setTransition(R.id.loginRegisterClearHeader, R.id.loginRegisterVisibleHeader)
        headerTransitionContainer.transitionToEnd()
    }

    private fun setupLoginPage() {
        pageView.findViewById<Button>(R.id.login_login_button).setOnClickListener { presenter?.login() }
        pageView.findViewById<TextView>(R.id.login_forget_password_text).setOnClickListener { presenter?.forgetPassword() }
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
        pageView.findViewById<TextView>(R.id.login_card_view_register_text).setOnClickListener { switchToRegister() }
    }

    private fun setupRegisterPage() {
        pageView.findViewById<Button>(R.id.register_register_button).setOnClickListener { presenter?.register() }
        pageView.findViewById<EditText>(R.id.register_email_input).addTextChangedListener ( object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter?.setInputtedEmail(p0.toString())
                presenter?.switchRegisterButton()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
        pageView.findViewById<EditText>(R.id.register_password_input).addTextChangedListener ( object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter?.setInputtedPassword(p0.toString())
                presenter?.switchRegisterButton()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
        pageView.findViewById<EditText>(R.id.register_password_confirmation_input).addTextChangedListener ( object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter?.setInputtedPasswordConfirmation(p0.toString())
                presenter?.switchRegisterButton()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
        pageView.findViewById<Button>(R.id.register_man_button).setOnClickListener { selectMan() }
        pageView.findViewById<Button>(R.id.register_woman_button).setOnClickListener { selectWoman() }
        pageView.findViewById<CheckBox>(R.id.register_email_permit_checkbox)
            .setOnCheckedChangeListener { _, isChecked ->
                presenter?.setEmailPermit(isChecked)
            }
        pageView.findViewById<TextView>(R.id.register_card_view_login_text).setOnClickListener { switchToLogin() }
    }

    private fun switchToRegister() {
        cardsTransitionContainer.setTransition(R.id.loginVisible, R.id.registerVisible)
        cardsTransitionContainer.transitionToEnd()
    }

    private fun switchToLogin() {
        cardsTransitionContainer.setTransition(R.id.registerVisible, R.id.loginVisible)
        cardsTransitionContainer.transitionToEnd()
    }

    override fun enableLoginButton() {
        pageView.findViewById<Button>(R.id.login_login_button).isEnabled = true
        pageView.findViewById<Button>(R.id.login_login_button).isClickable = true
        pageView.findViewById<Button>(R.id.login_login_button).alpha = 1.0F
    }

    override fun disableLoginbutton() {
        pageView.findViewById<Button>(R.id.login_login_button).isEnabled = false
        pageView.findViewById<Button>(R.id.login_login_button).isClickable = false
        pageView.findViewById<Button>(R.id.login_login_button).alpha = 0.5F
    }

    override fun enableRegisterButton() {
        pageView.findViewById<Button>(R.id.register_register_button).isEnabled = true
        pageView.findViewById<Button>(R.id.register_register_button).isClickable = true
        pageView.findViewById<Button>(R.id.register_register_button).alpha = 1.0F
    }

    override fun disableRegisterButton() {
        pageView.findViewById<Button>(R.id.register_register_button).isEnabled = false
        pageView.findViewById<Button>(R.id.register_register_button).isClickable = false
        pageView.findViewById<Button>(R.id.register_register_button).alpha = 0.5F
    }

    override fun selectMan() {
        presenter?.setSex(Sex.MAN)
        pageView.findViewById<Button>(R.id.register_woman_button).background = resources
            .getDrawable(R.drawable.cabin_register_woman_button, this.context?.theme)
        pageView.findViewById<Button>(R.id.register_man_button).background = resources
            .getDrawable(R.drawable.cabin_register_man_button_checked, this.context?.theme)
    }

    override fun selectWoman() {
        presenter?.setSex(Sex.WOMAN)
        pageView.findViewById<Button>(R.id.register_man_button).background = resources
            .getDrawable(R.drawable.cabin_register_man_button, this.context?.theme)
        pageView.findViewById<Button>(R.id.register_woman_button).background = resources
            .getDrawable(R.drawable.cabin_register_woman_button_checked, this.context?.theme)
    }

    override fun setActiveUser(user: MODELUser) {
        (activity!! as CabinCustomerRegistrationActivity).setActiveUser(user)
    }

    override fun closeActivity() {
        val inputManager: InputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, InputMethodManager.SHOW_FORCED)
        activity?.onBackPressed()
    }



    //endregion
}