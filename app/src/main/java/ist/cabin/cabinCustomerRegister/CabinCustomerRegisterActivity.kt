package ist.cabin.cabinCustomerRegister

import android.os.Bundle
import ist.cabin.cabinCustomerBase.BaseActivity
import ist.cabin.cabinCustomerRegister.fragments.agreement.CabinCustomerAgreementFragment
import ist.cabin.cabinCustomerRegister.fragments.register.CabinCustomerRegisterFragment
import ist.cabin.cabincustomer.R


class CabinCustomerRegisterActivity : BaseActivity(),
    CabinCustomerRegisterContracts.View, CabinCustomerRegisterFragment.RegisterFragmentListener,
    CabinCustomerAgreementFragment.AgreementFragmentListener {
    var presenter: CabinCustomerRegisterContracts.Presenter? = CabinCustomerRegisterPresenter(this)

    //region Lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cabin_customer_register_root)
        presenter?.onCreate(intent.extras)

        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left)

        showRegisteration()
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

    override fun showRegisteration() {
        val registerFragment = CabinCustomerRegisterFragment()
        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(R.id.customer_register_frame, registerFragment)
        transaction.commit()
    }

    override fun showAgreement() {
        val agreementFragment = CabinCustomerAgreementFragment()
        val transaction = supportFragmentManager.beginTransaction()

        transaction.setCustomAnimations(R.anim.slide_in_from_bottom, R.anim.slide_out_to_top)
        transaction.addToBackStack(null)

        transaction.replace(R.id.customer_register_frame, agreementFragment)
        transaction.commit()
    }


    //endregion

    //region fragment listeners

    override fun continueToAgreement(email: String, password: String, sex: Int) {
        presenter?.setFinalEmail(email)
        presenter?.setFinalPassword(password)
        presenter?.setFinalSex(sex)
        presenter?.continueToAgreement()
    }

    override fun moveBackToRegisteration() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.slide_in_from_top, R.anim.slide_out_to_bottom)

        supportFragmentManager.popBackStack()
    }

    //endregion
}