package ist.cabin.cabinCustomerProfileOptions.fragments.changePassword

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import ist.cabin.cabinCustomerBase.BaseFragment
import ist.cabin.cabinCustomerBase.Constants
import ist.cabin.cabincustomer.R

class CabinCustomerChangePasswordFragment : BaseFragment(), CabinCustomerChangePasswordContracts.View {

    var presenter: CabinCustomerChangePasswordContracts.Presenter? = CabinCustomerChangePasswordPresenter(this)
    private lateinit var pageView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pageView = inflater.inflate(R.layout.cabin_customer_change_password, container, false)
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

    override fun onBackPressed() {
        activity!!.onBackPressed()
    }

    private fun setupPage() {
        pageView.findViewById<ImageButton>(R.id.change_password_back_button)
            .setOnClickListener { onBackPressed() }

        pageView.findViewById<EditText>(R.id.change_password_current_password_input).apply {
            if(presenter != null) filters = arrayOf(
                if (presenter != null) InputFilter.LengthFilter(Constants.MAX_PASSWORD_LENGTH)
                else InputFilter.LengthFilter(100))
            addTextChangedListener(object :
                TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    presenter?.setPassword(p0.toString())
                }
                override fun afterTextChanged(p0: Editable?) {}
            })
        }

        pageView.findViewById<EditText>(R.id.change_password_new_password_input).apply {
            if(presenter != null) filters = arrayOf(
                if (presenter != null) InputFilter.LengthFilter(Constants.MAX_PASSWORD_LENGTH)
                else InputFilter.LengthFilter(100))
            addTextChangedListener(object :
                TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    presenter?.setNewPassword(p0.toString())
                }
                override fun afterTextChanged(p0: Editable?) {}
            })
        }

        pageView.findViewById<EditText>(R.id.change_password_password_confirmation_input).apply {
            if(presenter != null) filters = arrayOf(
                if (presenter != null) InputFilter.LengthFilter(Constants.MAX_PASSWORD_LENGTH)
                else InputFilter.LengthFilter(100))
            addTextChangedListener(object :
                TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    presenter?.setNewPasswordConfirmation(p0.toString())
                }
                override fun afterTextChanged(p0: Editable?) {}
            })
        }

        pageView.findViewById<Button>(R.id.change_password_update_button).setOnClickListener {
            val context = this.context
            if (context != null)
                presenter?.confirmPage(context)
        }

        deactivateAddButton()
    }

    override fun activateAddButton() {
        pageView.findViewById<Button>(R.id.change_password_update_button).apply {
            isClickable = true
            isEnabled = true
            alpha = 1f
        }
    }

    override fun deactivateAddButton() {
        pageView.findViewById<Button>(R.id.change_password_update_button).apply {
            isClickable = false
            isEnabled = false
            alpha = 0.5f
        }
    }

    override fun showErrorMessage(title: String, message: String) {
        AlertDialog.Builder(this.context)
            .setTitle(title)
            .setMessage(message)
            .setNegativeButton("Tamam", null)
            .show()
    }

    override fun showErrorMessage(errorField: Int) {
        when (errorField) {
            ChangePasswordFieldIDs.PASSWORD -> {
                showErrorMessage("HATA",resources.getString(R.string.change_password_password_error))
            }
            ChangePasswordFieldIDs.NEW_PASSWORD -> {
                showErrorMessage("HATA",resources.getString(R.string.change_password_new_password_error))
            }
            ChangePasswordFieldIDs.NEW_PASSWORD_CONFIRMATION -> {
                showErrorMessage("HATA",resources.getString(R.string.change_password_new_password_confirmation_error))
            }
            else -> {
                showErrorMessage("HATA","Bilinmedik bir hata oluştu!")
            }
        }
    }

    override fun showSuccessMessage() {
        AlertDialog.Builder(this.context)
            .setTitle("TEBRİKLER")
            .setMessage("Şifresiz başarı ile güncellenmiştir")
            .setPositiveButton("Tamam") { _, _ -> onBackPressed()
            }
            .show()
    }

    //endregion
}