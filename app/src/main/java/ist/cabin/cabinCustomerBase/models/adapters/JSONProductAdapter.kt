package ist.cabin.cabinCustomerBase.models.adapters

import android.util.Log
import com.squareup.moshi.*
import ist.cabin.cabinCustomerBase.models.backend.*

class JSONProductAdapter (moshi: Moshi) : JsonAdapter<JSONProduct>() {
    private val options: JsonReader.Options =
        JsonReader.Options.of("ID", "CODE", "TITLE", "PRICE", "SELLER", "SHIPPING_DURATION", "SHIPPING_TYPE", "COLOR")

    private val intAdapter: JsonAdapter<Int> =
        moshi.adapter<Int>(Int::class.java, emptySet(), "id")

    private val stringAdapter: JsonAdapter<String> =
        moshi.adapter<String>(String::class.java, emptySet(), "code")

    private val doubleAdapter: JsonAdapter<Double> =
        moshi.adapter<Double>(Double::class.java, emptySet(), "price")

    private val listOfJSONSellerAdapter: JsonAdapter<List<JSONSeller>> =
        Moshi.Builder().add(JSONSellerAdapter(Moshi.Builder().build())).build()
            .adapter<List<JSONSeller>>(Types.newParameterizedType(List::class.java, JSONSeller::class.java),
                emptySet(), "seller")

    private val nullableListOfJSONShippingDurationAdapter: JsonAdapter<List<JSONShippingDuration>?> =
        Moshi.Builder().add(JSONShippingDurationAdapter(Moshi.Builder().build())).build()
            .adapter(Types.newParameterizedType(List::class.java, JSONShippingDuration::class.java),
                emptySet(), "shippingDuration")

    private val listOfJSONShippingTypeAdapter: JsonAdapter<List<JSONShippingType>> =
        Moshi.Builder().add(JSONShippingTypeAdapter(Moshi.Builder().build())).build()
            .adapter<List<JSONShippingType>>(Types.newParameterizedType(List::class.java, JSONShippingType::class.java),
                emptySet(), "shippingType")

    private val listOfJSONColorAdapter: JsonAdapter<List<JSONColor>> =
        Moshi.Builder().add(JSONColorAdapter(Moshi.Builder().build())).build()
            .adapter<List<JSONColor>>(Types.newParameterizedType(List::class.java, JSONColor::class.java),
                emptySet(), "colors")

    override fun toString(): String = "GeneratedJsonAdapter(JSONProduct)"

    @FromJson
    override fun fromJson(reader: JsonReader): JSONProduct? {
        var id: Int? = null
        var code: String? = null
        var title: String? = null
        var price: Double? = null
        var seller: List<JSONSeller>? = null
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
                        4 -> seller = listOfJSONSellerAdapter.fromJson(reader) //?: throw JsonDataException("Non-null value 'seller' was null at ${reader.path}")
                        5 -> shippingDuration = nullableListOfJSONShippingDurationAdapter.fromJson(reader)
                        6 -> shippingType = listOfJSONShippingTypeAdapter.fromJson(reader) //?: throw JsonDataException("Non-null value 'shippingType' was null at ${reader.path}")
                        7 -> colors = listOfJSONColorAdapter.fromJson(reader) //?: throw JsonDataException("Non-null value 'colors' was null at ${reader.path}")
                        -1 -> {
                            // Unknown name, skip it.
                            reader.skipName()
                            reader.skipValue()
                        }
                }
            } catch (exception: Exception){
                Log.e("JSONProductAdapter", exception.message.toString())
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
                seller = seller!!,
                shippingDuration = shippingDuration!!,
                shippingType = shippingType!!,
                colors = colors!!
            )
            colors.forEach {
                if (it != null)
                    return result
            }
            null
        } catch (exception: Exception) {
            Log.e("JSONProductAdapter", exception.message.toString())
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
        writer.name("SELLER")
        listOfJSONSellerAdapter.toJson(writer, value.seller)
        writer.name("SHIPPING_DURATION")
        nullableListOfJSONShippingDurationAdapter.toJson(writer, value.shippingDuration)
        writer.name("SHIPPING_TYPE")
        listOfJSONShippingTypeAdapter.toJson(writer, value.shippingType)
        writer.name("COLOR")
        listOfJSONColorAdapter.toJson(writer, value.colors)
        writer.endObject()
    }
}