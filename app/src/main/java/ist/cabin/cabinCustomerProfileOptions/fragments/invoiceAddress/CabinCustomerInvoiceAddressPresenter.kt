package ist.cabin.cabinCustomerProfileOptions.fragments.invoiceAddress

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import ist.cabin.cabinCustomerBase.Constants

class CabinCustomerInvoiceAddressPresenter(var view: CabinCustomerInvoiceAddressContracts.View?) :
    CabinCustomerInvoiceAddressContracts.Presenter, CabinCustomerInvoiceAddressContracts.InteractorOutput {

    var interactor: CabinCustomerInvoiceAddressContracts.Interactor? = CabinCustomerInvoiceAddressInteractor(this)
    var router: CabinCustomerInvoiceAddressContracts.Router? = null

    private lateinit var name: String
    private lateinit var surname: String
    private lateinit var address: String
    private lateinit var addressHeader: String
    private lateinit var countryCode: String
    private var phone: String = ""
    private var province: String = "istanbul" //TODO: SAVE SOMEWHERE AND USE IDS
    private var district: String = "Adalar" //TODO: INITIAL DATA MUST MATCH DATA SHOWN IN UI
    private var isCorporate: Boolean = false
    private lateinit var corporationName: String
    private lateinit var taxNumber: String
    private lateinit var taxAdministration: String

    private var nameFilled = false
    private var surnameFilled = false
    private var phoneFilled = false
    private var addressFilled = false
    private var addressHeaderFilled = false
    private var corporationNameFilled = false
    private var taxNumberFilled = false
    private var taxAdministrationFilled = false

    //region Lifecycle

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        //the view can be a activity or a fragment, that's why this getActivityContext method is needed
        val activity = view?.getActivityContext() as? Activity ?: return
        router = CabinCustomerInvoiceAddressRouter(activity)

        bundle?.let {
            //you can delete this if there's no need to get extras from the intent
            //TODO: Do something
        }
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
            nameFilled = name.isNotEmpty()
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

    override fun setPhone(inputtedPhone: String) {
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

    override fun setInvoiceType(isCorporate: Boolean) {
        if(isCorporate) view!!.showCorporateInvoiceData()
        else view!!.hideCorporateInvoiceData()

        validatePage()
    }

    override fun setCorporationName(inputtedCorporationName: String) {
        if (inputtedCorporationName.length <= Constants.MAX_CORPORATION_NAME_LENGTH) {
            this.corporationName = inputtedCorporationName
            corporationNameFilled = inputtedCorporationName.isNotEmpty()
        } else {
            corporationNameFilled = false
            Log.e("input error", "corporation name too long!")
        }

        validatePage()
    }

    override fun setTaxNumber(inputtedTaxNumber: String) {
        if (inputtedTaxNumber.length <= Constants.MAX_TAX_NUMBER_LENGTH) {
            this.taxNumber = inputtedTaxNumber
            taxNumberFilled = inputtedTaxNumber.isNotEmpty()
        } else {
            taxNumberFilled = false
            Log.e("input error", "tax number too long!")
        }

        validatePage()
    }

    override fun setTaxAdministration(inputtedTaxAdministration: String) {
        if (inputtedTaxAdministration.length <= Constants.MAX_TAX_ADMINISTRATION_LENGTH) {
            this.taxAdministration = inputtedTaxAdministration
            taxAdministrationFilled = inputtedTaxAdministration.isNotEmpty()
        } else {
            taxAdministrationFilled = false
            Log.e("input error", "tax administration too long!")
        }

        validatePage()
    }

    override fun isCorporate(isCorporate: Boolean) {
        this.isCorporate = isCorporate
        if (isCorporate) view!!.showCorporateInvoiceData()
        else view!!.hideCorporateInvoiceData()

        validatePage()
    }

    override fun saveData() {
        interactor?.saveData() //TODO: SEND DATA TO BE SAVED
        view!!.onBackPressed()
    }

    private fun validatePage() {
        if (addressHeaderFilled)
            Toast.makeText(view!!.getActivityContext(), "nameFilled $nameFilled\nsurnameFilled $surnameFilled\n" +
                    "phoneFilled $phoneFilled\naddressFilled $addressFilled\naddressHeaderFilled $addressHeaderFilled\n" +
                    "isCorporate $isCorporate\ncorporationNameFilled $corporationNameFilled\ntaxNumberFilled $taxNumberFilled\n" +
                    "taxAdministrationFilled $taxAdministrationFilled", Toast.LENGTH_SHORT).show()

        if (isCorporate) {
            if (nameFilled && surnameFilled && phoneFilled && addressFilled && addressHeaderFilled &&
                corporationNameFilled && taxNumberFilled && taxAdministrationFilled) view!!.enableAddButton()
            else view!!.disableAddButton()
        } else {
            if (nameFilled && surnameFilled && phoneFilled && addressFilled && addressHeaderFilled) view!!.enableAddButton()
            else view!!.disableAddButton()
        }
    }

    //endregion

    //region InteractorOutput

    //TODO: Implement your InteractorOutput methods here

    //endregion
}