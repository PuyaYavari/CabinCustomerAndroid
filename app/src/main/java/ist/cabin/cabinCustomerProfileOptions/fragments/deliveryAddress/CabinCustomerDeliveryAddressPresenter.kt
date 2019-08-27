package ist.cabin.cabinCustomerProfileOptions.fragments.deliveryAddress

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import ist.cabin.cabincustomer.Constants

class CabinCustomerDeliveryAddressPresenter(var view: CabinCustomerDeliveryAddressContracts.View?) :
    CabinCustomerDeliveryAddressContracts.Presenter, CabinCustomerDeliveryAddressContracts.InteractorOutput {

    var interactor: CabinCustomerDeliveryAddressContracts.Interactor? = CabinCustomerDeliveryAddressInteractor(this)
    var router: CabinCustomerDeliveryAddressContracts.Router? = null

    private lateinit var name: String
    private lateinit var surname: String
    private lateinit var address: String
    private lateinit var addressHeader: String
    private lateinit var countryCode: String
    private var phone: String = ""
    private var province: String = "istanbul" //TODO: SAVE SOMEWHERE AND USE IDS
    private var district: String = "Adalar" //TODO: INITIAL DATA MUST MATCH DATA SHOWN IN UI

    private var nameFilled = false
    private var surnameFilled = false
    private var phoneFilled = false
    private var addressFilled = false
    private var addressHeaderFilled = false

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerDeliveryAddressRouter(activity)

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

    override fun setName(inputtedName: String) {
        if (inputtedName.length <= Constants.MAX_NAME_LENGTH) {
            this.name = inputtedName
            nameFilled = inputtedName.isNotEmpty()
        } else {
            nameFilled = false
            Log.e("input error", "name too long!")
        }

        validatePage()
    }

    override fun setSurname(inputtedSurname: String) {
        if (inputtedSurname.length <= Constants.MAX_SURNAME_LENGTH) {
            this.surname = inputtedSurname
            surnameFilled = inputtedSurname.isNotEmpty()
        } else {
            surnameFilled = false
            Log.e("input error", "surname too long!")
        }

        validatePage()
    }

    override fun setPhone(inputtedPhone: String){
        countryCode = "0090" //TODO: Define somewhere
        if(inputtedPhone.isNotEmpty())
        {
            phone = countryCode
            for (char in inputtedPhone) if (char.isDigit()) phone = "$phone$char"
            phoneFilled = phone.length == Constants.MAX_PHONE_LENGTH
        } else {
            phone = ""
            phoneFilled = false
        }

        validatePage()
    }

    override fun setProvince(inputtedProvince: String) {
        this.province = inputtedProvince

        validatePage()
    }

    override fun setDistrict(inputtedDistrict: String) {
        this.district = inputtedDistrict

        validatePage()
    }

    override fun setAddress(inputtedAddress: String) {
        if(inputtedAddress.length <= Constants.MAX_ADDRESS_LENGTH) {
            this.address = inputtedAddress
            addressFilled = inputtedAddress.isNotEmpty()
        } else {
            addressFilled = false
            Log.e("input error", "address too long!")
        }

        validatePage()
    }

    override fun setAddressHeader(inputtedAddressHeader: String) {
        if(inputtedAddressHeader.length <= Constants.MAX_ADDRESS_HEADER_LENGTH) {
            this.addressHeader = inputtedAddressHeader
            addressHeaderFilled = inputtedAddressHeader.isNotEmpty()
        } else {
            addressHeaderFilled = false
            Log.e("input error", "address header too long!")
        }

        validatePage()
    }

    override fun saveData() {
        // TODO: save data
    }

    private fun validatePage() {
        if (addressHeaderFilled)
            Toast.makeText(view!!.getActivityContext(), "nameFilled $nameFilled\nsurnameFilled $surnameFilled\n" +
                    "phoneFilled $phoneFilled\naddressFilled $addressFilled\naddressHeaderFilled $addressHeaderFilled"
                , Toast.LENGTH_SHORT).show()

        if (nameFilled && surnameFilled && phoneFilled && addressFilled && addressHeaderFilled) view!!.enableAddButton()
        else view!!.disableAddButton()
    }

    //endregion

    //region InteractorOutput

    //endregion
}