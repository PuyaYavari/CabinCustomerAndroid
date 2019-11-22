package com.cabinInformationTechnologies.cabinCustomerRegistration.fragments.loginRegister

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.constraintlayout.motion.widget.MotionLayout
import com.cabinInformationTechnologies.cabin.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task


class CabinCustomerLoginRegisterFragment : com.cabinInformationTechnologies.cabinCustomerBase.BaseFragment(),
    CabinCustomerLoginRegisterContracts.View {

    var presenter: CabinCustomerLoginRegisterContracts.Presenter? =
        CabinCustomerLoginRegisterPresenter(
            this
        )
    private lateinit var pageView: View
    private lateinit var cardsTransitionContainer: MotionLayout
    private lateinit var headerTransitionContainer: MotionLayout

    private val G_SIGN_IN: Int = 100
    private val F_SIGN_IN: Int = 200

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_login_register, container, false)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == G_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            presenter?.handleGoogleSignInResult(this.context, task)
        }

    }

    //region View

    private fun setupPage() {
        setupLoginPage()
        setupRegisterPage()
        setupGoogleAndFacebookButtons()
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

    private fun setupGoogleAndFacebookButtons() {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("cabin-7f196")
                .requestEmail()
                .build()

        // Build a GoogleSignInClient with the options specified by gso.
        val mGoogleSignInClient = GoogleSignIn.getClient(this.activity as Activity, gso)

        pageView.findViewById<ImageView>(R.id.login_google_button).setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, G_SIGN_IN)
        }

        pageView.findViewById<ImageView>(R.id.register_google_button).setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, G_SIGN_IN)
        }
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
        presenter?.setSex(com.cabinInformationTechnologies.cabinCustomerBase.baseAbstracts.Sex.MAN)
        pageView.findViewById<Button>(R.id.register_woman_button).background = resources
            .getDrawable(R.drawable.cabin_register_woman_button, this.context?.theme)
        pageView.findViewById<Button>(R.id.register_man_button).background = resources
            .getDrawable(R.drawable.cabin_register_man_button_checked, this.context?.theme)
    }

    override fun selectWoman() {
        presenter?.setSex(com.cabinInformationTechnologies.cabinCustomerBase.baseAbstracts.Sex.WOMAN)
        pageView.findViewById<Button>(R.id.register_man_button).background = resources
            .getDrawable(R.drawable.cabin_register_man_button, this.context?.theme)
        pageView.findViewById<Button>(R.id.register_woman_button).background = resources
            .getDrawable(R.drawable.cabin_register_woman_button_checked, this.context?.theme)
    }

    override fun setActiveUser(user: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELUser) {
        (activity!! as com.cabinInformationTechnologies.cabinCustomerRegistration.CabinCustomerRegistrationActivity).setActiveUser(user)
    }

    override fun closeActivity() {
        val inputManager: InputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, InputMethodManager.SHOW_FORCED)
        activity?.onBackPressed()
    }



    //endregion
}