package com.cabinInformationTechnologies.cabin.fragments.home

import android.app.Activity
import android.os.Bundle
import com.cabinInformationTechnologies.cabin.fragments.home.adapters.CabinCustomerHomeAdapter
import com.cabinInformationTechnologies.cabin.fragments.home.adapters.CabinCustomerHomeHeaderAdapter
import com.cabinInformationTechnologies.cabinCustomerBase.GlobalData
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELBannerGroup
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELHeader
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELHeaders

class CabinCustomerHomePresenter(var view: CabinCustomerHomeContracts.View?) : CabinCustomerHomeContracts.Presenter,
    CabinCustomerHomeContracts.InteractorOutput {

    var interactor: CabinCustomerHomeContracts.Interactor? =
        CabinCustomerHomeInteractor(this)
    var router: CabinCustomerHomeContracts.Router? = null

    override var headers: MutableList<MODELHeader> = mutableListOf()
        set(value) {
            field = value
            headerAdapter.selectedPosition = 0
            headerAdapter.notifyDataSetChanged()
            myDataset = field[0].getSubBanners() ?: mutableListOf()
        }
    override var myDataset: MutableList<MODELBannerGroup> = mutableListOf()
        set(value) {
            field = value
            homeAdapter.notifyDataSetChanged()
        }
    override var headerAdapter: CabinCustomerHomeHeaderAdapter = CabinCustomerHomeHeaderAdapter(this)
    override var homeAdapter: CabinCustomerHomeAdapter = CabinCustomerHomeAdapter(this)

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerHomeRouter(activity)

        if (GlobalData.loggedIn)
            view?.unlockDrawer()
        else
            view?.lockDrawer()
    }

    override fun onDestroy() {
        view = null
        interactor?.unregister()
        interactor = null
        router?.unregister()
        router = null
    }

    //endregion

    //region Presenter

    override fun moveToRegistration() {
        router?.moveToRegistration()
    }

    //endregion

    //region InteractorOutput

    override fun setHeaderData(data: MODELHeaders) {
        headers = data.getHeaders()
    }

    //endregion
}