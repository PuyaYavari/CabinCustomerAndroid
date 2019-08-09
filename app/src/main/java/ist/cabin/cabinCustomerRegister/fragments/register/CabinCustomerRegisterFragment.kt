package ist.cabin.cabinCustomerRegister.fragments.register

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import ist.cabin.cabinCustomerBase.BaseFragment
import ist.cabin.cabincustomer.R
import kotlinx.android.synthetic.main.cabin_customer_register_register.*

class CabinCustomerRegisterFragment : BaseFragment(), CabinCustomerRegisterFragmentContracts.View {
    var presenter: CabinCustomerRegisterFragmentContracts.Presenter? = CabinCustomerRegisterFragmentPresenter(this)
    private lateinit var listener: RegisterFragmentListener


    //region Lifecycle
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is RegisterFragmentListener) {
            listener = context
        } else {
            throw ClassCastException(
                context.toString() + " must implement RegisterFragmentListener.")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.cabin_customer_register_register, container, false)

        setupPage(view)
        return view
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

    override fun setupPage(view: View) {
        view.findViewById<ImageButton>(R.id.register_man_button).background.alpha = 0
        view.findViewById<ImageButton>(R.id.register_man_button).setImageResource(R.drawable.man_icon_purple)
        view.findViewById<ImageButton>(R.id.register_man_button).setOnClickListener {
            presenter?.selectMan()
            presenter?.removeFocus()
            presenter?.setIcons()
            presenter?.switchContinueButton()
        }
        view.findViewById<ImageButton>(R.id.register_woman_button).background.alpha = 0
        view.findViewById<ImageButton>(R.id.register_woman_button).setImageResource(R.drawable.woman_icon_purple)
        view.findViewById<ImageButton>(R.id.register_woman_button).setOnClickListener {
            presenter?.selectWoman()
            presenter?.removeFocus()
            presenter?.setIcons()
            presenter?.switchContinueButton()
        }
        view.findViewById<TextView>(R.id.register_email_input).requestFocus()
        view.findViewById<TextView>(R.id.register_email_input).addTextChangedListener ( object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter?.setInputtedEmail(p0.toString())
                presenter?.setIcons()
                presenter?.switchContinueButton()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
        view.findViewById<TextView>(R.id.register_password_input).addTextChangedListener ( object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter?.setInputtedPassword(p0.toString())
                presenter?.setIcons()
                presenter?.switchContinueButton()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
        view.findViewById<TextView>(R.id.register_password_confirmation_input).addTextChangedListener ( object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter?.setInputtedPasswordConfirmation(p0.toString())
                presenter?.setIcons()
                presenter?.switchContinueButton()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
        view.findViewById<Button>(R.id.register_continue_button).setOnClickListener { listener.continueToAgreement(
            presenter!!.getInputtedEmail(), presenter!!.getInputtedPassword(), presenter!!.getInputtedSex()) }
        presenter?.visualizeSex(view)
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

    override fun selectMan(view: View) {
        view.findViewById<ImageButton>(R.id.register_man_button).background.alpha = 255
        view.findViewById<ImageButton>(R.id.register_man_button).setImageResource(R.drawable.man_icon_white)
    }

    override fun unselectMan(view: View) {
        view.findViewById<ImageButton>(R.id.register_man_button).background.alpha = 0
        view.findViewById<ImageButton>(R.id.register_man_button).setImageResource(R.drawable.man_icon_purple)
    }

    override fun selectWoman(view: View) {
        view.findViewById<ImageButton>(R.id.register_woman_button).background.alpha = 255
        view.findViewById<ImageButton>(R.id.register_woman_button).setImageResource(R.drawable.woman_icon_white)
    }

    override fun unselectWoman(view: View) {
        view.findViewById<ImageButton>(R.id.register_woman_button).background.alpha = 0
        view.findViewById<ImageButton>(R.id.register_woman_button).setImageResource(R.drawable.woman_icon_purple)
    }

    override fun emailStatusRemoveIcon() {
        register_email_status_icon.setImageResource(0)
    }

    override fun emailStatusEditIcon() {
        register_email_status_icon.setImageResource(R.drawable.purple_neon_dots)
    }

    override fun emailStatusCrossIcon() {
        register_email_status_icon.setImageResource(R.drawable.purple_neon_cross)
    }

    override fun emailStatusTickIcon() {
        register_email_status_icon.setImageResource(R.drawable.purple_neon_tick)
    }

    override fun passwordStatusRemoveIcon() {
        register_password_status_icon.setImageResource(0)
    }

    override fun passwordStatusEditIcon() {
        register_password_status_icon.setImageResource(R.drawable.purple_neon_dots)
    }

    override fun passwordStatusCrossIcon() {
        register_password_status_icon.setImageResource(R.drawable.purple_neon_cross)
    }

    override fun passwordStatusTickIcon() {
        register_password_status_icon.setImageResource(R.drawable.purple_neon_tick)
    }

    override fun passwordConfirmationStatusRemoveIcon() {
        register_passwordconfirmation_status_icon.setImageResource(0)
    }

    override fun passwordConfirmationStatusEditIcon() {
        register_passwordconfirmation_status_icon.setImageResource(R.drawable.purple_neon_dots)
    }

    override fun passwordConfirmationStatusCrossIcon() {
        register_passwordconfirmation_status_icon.setImageResource(R.drawable.purple_neon_cross)
    }

    override fun passwordConfirmationStatusTickIcon() {
        register_passwordconfirmation_status_icon.setImageResource(R.drawable.purple_neon_tick)
    }

    override fun setOnContinuePressedListener(listener: RegisterFragmentListener) {
        this.listener = listener
    }

    //endregion

    interface RegisterFragmentListener {
        fun continueToAgreement(email: String, password: String, sex: Int)
    }
}