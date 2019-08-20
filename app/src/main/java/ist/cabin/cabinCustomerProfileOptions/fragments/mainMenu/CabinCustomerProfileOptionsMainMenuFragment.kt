package ist.cabin.cabinCustomerProfileOptions.fragments.mainMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CabinCustomerProfileOptionsMainMenuFragment : BaseFragment(), CabinCustomerProfileOptionsMainMenuContracts.View {

    var presenter: CabinCustomerProfileOptionsMainMenuContracts.Presenter? =
        CabinCustomerProfileOptionsMainMenuPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //TODO: Inflate the layout for this fragment
        return inflater.inflate(R.layout., container, false)
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

    //TODO: Implement your View methods here

    //endregion
}