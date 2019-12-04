package com.cabinInformationTechnologies.cabinCustomerRegistration.fragments.resetPassword

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.BaseFragment

class CabinCustomerResetPasswordFragment : BaseFragment(),
    CabinCustomerResetPasswordContracts.View {
    private val args: CabinCustomerResetPasswordFragmentArgs by navArgs()

    var presenter: CabinCustomerResetPasswordContracts.Presenter? =
        CabinCustomerResetPasswordPresenter(this)
    private lateinit var pageView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_reset_password, container, false)
        presenter?.email = args.email
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
        pageView.apply {
            findViewById<Button>(R.id.reset_password_button).setOnClickListener {
                presenter?.buttonListener(this.context)
            }
            findViewById<EditText>(R.id.reset_password_password_input).addTextChangedListener ( object :
                TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    presenter?.password = p0.toString()
                }
                override fun afterTextChanged(p0: Editable?) {}
            })
            findViewById<EditText>(R.id.reset_password_password_confirmation_input).addTextChangedListener ( object :
                TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    presenter?.passwordConfirmation = p0.toString()
                }
                override fun afterTextChanged(p0: Editable?) {}
            })
            findViewById<EditText>(R.id.reset_password_code_input).addTextChangedListener ( object :
                TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    presenter?.code = p0.toString()
                }
                override fun afterTextChanged(p0: Editable?) {}
            })
        }
    }

    override fun enableButton() {
        pageView.findViewById<Button>(R.id.reset_password_button).apply {
            isEnabled = true
            isClickable = true
            alpha = 1f
        }
    }

    override fun disableButton() {
        pageView.findViewById<Button>(R.id.reset_password_button).apply {
            isEnabled = false
            isClickable = false
            alpha = 0.5f
        }
    }

    override fun success() {
        findNavController().popBackStack()
    }

    //endregion
}