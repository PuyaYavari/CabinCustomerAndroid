package ist.cabin.cabinCustomerHome.fragments.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ist.cabin.cabinCustomerBase.BaseFragment
import ist.cabin.cabincustomer.R

class CabinCustomerHomeFavoriteFragment : BaseFragment(), CabinCustomerHomeFavoriteFragmentContracts.View {

    var presenter: CabinCustomerHomeFavoriteFragmentContracts.Presenter? =
        CabinCustomerHomeFavoriteFragmentPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //TODO: Inflate the layout for this fragment
        return inflater.inflate(R.layout.cabin_customer_home_favorite, container, false)
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