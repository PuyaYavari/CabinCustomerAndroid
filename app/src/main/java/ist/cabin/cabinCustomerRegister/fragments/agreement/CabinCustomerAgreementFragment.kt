package ist.cabin.cabinCustomerRegister.fragments.agreement

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import ist.cabin.cabinCustomerBase.BaseFragment
import ist.cabin.cabincustomer.R

class CabinCustomerAgreementFragment : BaseFragment(), CabinCustomerAgreementFragmentContracts.View {

    var presenter: CabinCustomerAgreementFragmentContracts.Presenter? = CabinCustomerAgreementFragmentPresenter(this)
    private lateinit var listener: AgreementFragmentListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is AgreementFragmentListener) {
            listener = context
        } else {
            throw ClassCastException(
                context.toString() + " must implement AgreementFragmentListener.")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.cabin_customer_register_agreement, container, false)
        setupPage(view)
        return view
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

    override fun setupPage(view: View) {
        view.findViewById<Button>(R.id.agreement_back_button).setOnClickListener { listener.moveBackToRegisteration() }
        view.findViewById<Button>(R.id.agreement_confirmation_button).setOnClickListener { presenter?.accept() }
    }

    //endregion

    interface AgreementFragmentListener {
        fun moveBackToRegisteration()
    }
}