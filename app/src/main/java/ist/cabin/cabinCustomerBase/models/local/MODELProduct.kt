package ist.cabin.cabinCustomerBase.models.local

import android.os.Parcelable
import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.JSONProduct
import kotlinx.android.parcel.Parcelize

@Parcelize
class MODELProduct : LocalDataModel, Parcelable{

    private var id: Int = -1
    private lateinit var sellerName: String
    private lateinit var productName: String
    private lateinit var productId: String
    private var price: Double? = null
    private var amount: Int? = null
    private var cargoDurationId: Int? = null
    private lateinit var cargoDuration: String
    private var cargoTypeId: Int? = null
    private lateinit var cargoType: String
    private var colors: MutableList<MODELColor> = mutableListOf()

    @Throws(java.lang.Exception::class)
    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonModel = modelData as JSONProduct
            val seller = MODELSellerName()
            id = jsonModel.id
            if (seller.mapFrom(jsonModel.sellerName[0]))
                sellerName = seller.name
            productName = jsonModel.title
            productId = jsonModel.code
            price = jsonModel.price
            amount = jsonModel.amount

            val cargoDurationData = jsonModel.shippingDuration?.get(0) ?: throw java.lang.Exception("Cargo Duration Not Mapped.")
            cargoDurationId = cargoDurationData.id
            cargoDuration = cargoDurationData.name

            val cargoTypeData = jsonModel.shippingType[0] ?: throw java.lang.Exception("Cargo Type Not Mapped.")
            cargoTypeId = cargoTypeData.id
            cargoType = cargoTypeData.name

            jsonModel.colors.forEach {
                val color = MODELColor()
                if (color.mapFrom(it))
                    colors.add(color)
            }
            true
        } catch (exception: Exception){
            Logger.warn(this::class.java.name, "A problem occurred while mapping Product.", exception)
            false
        }

    }

    fun incAmount(count: Int){
        var amount = this.amount
        if (amount != null)
            amount += count
        this.amount = amount
    }

    fun decAmount(count: Int){
        var amount = this.amount
        if (amount != null)
            amount -= count
        this.amount = amount
    }

    fun getId() = this.id
    fun getSellerName() = this.sellerName
    fun getProductName() = this.productName
    fun getProductId() = this.productId
    fun getPrice() = this.price
    fun getAmount() = this.amount
    fun getCargoDurationId() = this.cargoDurationId
    fun getCargoDuration() = this.cargoDuration
    fun getCargoTypeId() = this.cargoTypeId
    fun getCargoType() = this.cargoType
    fun getColors() = this.colors

    fun setAmount(amount: Int?) { this.amount = amount }
}
