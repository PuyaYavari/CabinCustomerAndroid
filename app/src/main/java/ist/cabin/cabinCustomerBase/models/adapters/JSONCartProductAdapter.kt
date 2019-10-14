package ist.cabin.cabinCustomerBase.models.adapters

import com.squareup.moshi.*
import ist.cabin.cabinCustomerBase.BaseContracts
import ist.cabin.cabinCustomerBase.Constants
import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.NetworkManager
import ist.cabin.cabinCustomerBase.models.backend.*
import ist.cabin.cabinCustomerBase.models.local.MODELCarts
import ist.cabin.cabincustomer.fragments.cart.CabinCustomerCartContracts

class JSONCartProductAdapter (moshi: Moshi, val callback: CabinCustomerCartContracts.CartCallback?) : JsonAdapter<JSONProduct>() {
    private val options: JsonReader.Options =
        JsonReader.Options.of("ID", "CODE", "TITLE", "PRICE", "AMOUNT", "SELLER", "SHIPPING_DURATION", "SHIPPING_TYPE", "COLOR")

    private val intAdapter: JsonAdapter<Int> =
        moshi.adapter<Int>(Int::class.java, emptySet(), "id")

    private val stringAdapter: JsonAdapter<String> =
        moshi.adapter<String>(String::class.java, emptySet(), "code")

    private val doubleAdapter: JsonAdapter<Double> =
        moshi.adapter<Double>(Double::class.java, emptySet(), "price")

    private val listOfJSONSellerNameAdapter: JsonAdapter<List<JSONSellerName>> =
        Moshi.Builder().add(JSONSellerNameAdapter(Moshi.Builder().build())).build()
            .adapter<List<JSONSellerName>>(
                Types.newParameterizedType(List::class.java, JSONSellerName::class.java),
                emptySet(), "sellerName")

    private val nullableListOfJSONShippingDurationAdapter: JsonAdapter<List<JSONShippingDuration>?> =
        Moshi.Builder().add(JSONShippingDurationAdapter(Moshi.Builder().build())).build()
            .adapter(
                Types.newParameterizedType(List::class.java, JSONShippingDuration::class.java),
                emptySet(), "shippingDuration")

    private val listOfJSONShippingTypeAdapter: JsonAdapter<List<JSONShippingType>> =
        Moshi.Builder().add(JSONShippingTypeAdapter(Moshi.Builder().build())).build()
            .adapter<List<JSONShippingType>>(
                Types.newParameterizedType(List::class.java, JSONShippingType::class.java),
                emptySet(), "shippingType")

    private val listOfJSONColorAdapter: JsonAdapter<List<JSONColor>> =
        Moshi.Builder().add(JSONCartProductColorAdapter(Moshi.Builder().build())).build()
            .adapter<List<JSONColor>>(
                Types.newParameterizedType(List::class.java, JSONColor::class.java),
                emptySet(), "colors")

    override fun toString(): String = "GeneratedJsonAdapter(JSONProduct)"

    @FromJson
    override fun fromJson(reader: JsonReader): JSONProduct? {
        var id: Int? = null
        var code: String? = null
        var title: String? = null
        var price: Double? = null
        var amount: Int? = null
        var sellerName: List<JSONSellerName>? = null
        var shippingDuration: List<JSONShippingDuration>? = null
        var shippingType: List<JSONShippingType>? = null
        var colors: List<JSONColor>? = null
        reader.beginObject()
        while (reader.hasNext()) {
            try {
                when (reader.selectName(options)) {
                    0 -> id = intAdapter.fromJson(reader) //?: throw JsonDataException("Non-null value 'id' was null at ${reader.path}")
                    1 -> code = stringAdapter.fromJson(reader) //?: throw JsonDataException("Non-null value 'code' was null at ${reader.path}")
                    2 -> title = stringAdapter.fromJson(reader) //?: throw JsonDataException("Non-null value 'title' was null at ${reader.path}")
                    3 -> price = doubleAdapter.fromJson(reader) //?: throw JsonDataException("Non-null value 'price' was null at ${reader.path}")
                    4 -> amount = intAdapter.fromJson(reader) //?: throw JsonDataException("Non-null value 'amount' was null at ${reader.path}")
                    5 -> sellerName = listOfJSONSellerNameAdapter.fromJson(reader) //?: throw JsonDataException("Non-null value 'sellerName' was null at ${reader.path}")
                    6 -> shippingDuration = nullableListOfJSONShippingDurationAdapter.fromJson(reader)
                    7 -> shippingType = listOfJSONShippingTypeAdapter.fromJson(reader) //?: throw JsonDataException("Non-null value 'shippingType' was null at ${reader.path}")
                    8 -> colors = listOfJSONColorAdapter.fromJson(reader) //?: throw JsonDataException("Non-null value 'colors' was null at ${reader.path}")
                    -1 -> {
                        // Unknown name, skip it.
                        reader.skipName()
                        reader.skipValue()
                    }
                }
            } catch (exception: Exception){
                Logger.warn(
                    this::class.java.name, "A field is null and is being skipped.",
                    exception
                )
                reader.skipValue()
            }
        }
        reader.endObject()
        return try {
            val result = JSONProduct(
                id = id!!,
                code = code!!,
                title = title!!,
                price = price!!,
                amount = amount,
                sellerName = sellerName!!,
                shippingDuration = shippingDuration!!,
                shippingType = shippingType!!,
                colors = colors!!
            )
            colors.forEach {
                if (it != null) {
                    return result
                } else {
                    //FIXME: WHAT TO DO IF NO COLOR
                    removeItemFromCart(id, colors)
                }
            }
            null
        } catch (exception: Exception) {
            Logger.error(
                this::class.java.name,
                "A field is null, this object will not be visible in cart and also a request" +
                        "will be sent to backend to remove this object from the cart if it's possible " +
                        "if not a request to clear entire cart will be sent to backend. The cart will" +
                        "be updated as soon as possible.",
                exception
            )
            removeItemFromCart(id, colors)
            null
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: JSONProduct?) {
        if (value == null) {
            throw NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.beginObject()
        writer.name("ID")
        intAdapter.toJson(writer, value.id)
        writer.name("CODE")
        stringAdapter.toJson(writer, value.code)
        writer.name("TITLE")
        stringAdapter.toJson(writer, value.title)
        writer.name("PRICE")
        doubleAdapter.toJson(writer, value.price)
        writer.name("AMOUNT")
        intAdapter.toJson(writer, value.amount)
        writer.name("SELLER")
        listOfJSONSellerNameAdapter.toJson(writer, value.sellerName)
        writer.name("SHIPPING_DURATION")
        nullableListOfJSONShippingDurationAdapter.toJson(writer, value.shippingDuration)
        writer.name("SHIPPING_TYPE")
        listOfJSONShippingTypeAdapter.toJson(writer, value.shippingType)
        writer.name("COLOR")
        listOfJSONColorAdapter.toJson(writer, value.colors)
        writer.endObject()
    }

    private fun removeItemFromCart(id: Int?, colors: List<JSONColor>?) {
        try {
            val carts: MODELCarts = MODELCarts()
            var data: REQUESTAPIProduct? = null
            if (id != null && colors != null) {
                data = REQUESTAPIProduct(
                    listOf(
                        REQUESTProduct(
                            id,
                            0,
                            listOf(
                                REQUESTColor(
                                    colors[0].id,
                                    listOf(
                                        REQUESTSize(
                                            colors[0].sizes[0].id
                                        )
                                    )
                                )
                            )
                        )
                    )
                )
            }
            NetworkManager.requestFactory(
                null,
                Constants.CART_UPDATE_URL,
                null,
                null,
                data,
                carts,
                APICartAdapter(Moshi.Builder().build(), null),
                object : BaseContracts.ResponseCallbacks {
                    override fun onSuccess(value: Any?) {
                        Logger.info(
                            this::class.java.name,
                            "Product removed from cart.",
                            null
                        )
                    }

                    override fun onIssue(value: JSONIssue) {
                        Logger.warn(
                            this::class.java.name,
                            "Product not removed from cart.\n" +
                                    "ISSUE: ${value.message}",
                            null
                        )
                    }

                    override fun onError(value: String, url: String?) {
                        Logger.warn(
                            this::class.java.name,
                            "Product not removed from cart.\n" +
                                    "ERROR: $value",
                            null
                        )
                    }

                    override fun onFailure(throwable: Throwable) {
                        Logger.error(
                            this::class.java.name,
                            "FAILURE: Product not removed from cart.",
                            throwable
                        )
                    }

                    override fun onServerDown() {
                        Logger.failure(
                            this::class.java.name,
                            "SERVER DOWN!!",
                            null
                        )
                    }

                    override fun onException(exception: Exception) {
                        Logger.error(
                            this::class.java.name,
                            "EXCEPTION: Product not removed from cart.",
                            exception
                        )
                    }

                }
            )
            callback?.updateCart(carts.getCarts()[0])
        } catch (exception: Exception) {
            Logger.failure(
                this::class.java.name,
                "Error while sending request to remove problematic product from cart.",
                exception
                )
        }
    }
}