package ist.cabin.cabinCustomerBase.models.adapters

import com.squareup.moshi.*
import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.JSONCart
import ist.cabin.cabinCustomerBase.models.backend.JSONSeller

class JSONCartAdapter(moshi: Moshi) : JsonAdapter<JSONCart>() {
    private val options: JsonReader.Options =
        JsonReader.Options.of("SELLER", "SHIPPINGPRICE", "SUBTOTAL", "TOTAL")

    private val listOfNullableJSONSellerAdapter: JsonAdapter<List<JSONSeller?>> =
        Moshi.Builder().add(JSONSellerAdapter(Moshi.Builder().build())).build()
            .adapter<List<JSONSeller?>>(Types.newParameterizedType(List::class.java,
                JSONSeller::class.java), emptySet(), "seller")

    private val nullableIntAdapter: JsonAdapter<Int?> =
        moshi.adapter<Int?>(Int::class.javaObjectType, emptySet(), "shippingPrice")

    private val intAdapter: JsonAdapter<Int> =
        moshi.adapter<Int>(Int::class.java, emptySet(), "total")

    override fun toString(): String = "GeneratedJsonAdapter(JSONCart)"

    @FromJson
    override fun fromJson(reader: JsonReader): JSONCart? {
        var seller: List<JSONSeller?>? = null
        var shippingPrice: Int? = null
        var subtotal: Int? = null
        var total: Int? = null
        reader.beginObject()
        while (reader.hasNext()) {
            try {
                when (reader.selectName(options)) {
                    0 -> seller =
                        listOfNullableJSONSellerAdapter.fromJson(reader)
                    1 -> shippingPrice = nullableIntAdapter.fromJson(reader)
                    2 -> subtotal = nullableIntAdapter.fromJson(reader)
                    3 -> total = intAdapter.fromJson(reader)
                    -1 -> {
                        // Unknown name, skip it.
                        reader.skipName()
                        reader.skipValue()
                    }
                }
            } catch (exception: Exception) {
                Logger.error(
                    this::class.java.name, "A field is null and is being skipped.",
                    exception
                )
                return  null
            }
        }
        reader.endObject()
        return try {
            val result = JSONCart(
                seller = seller!!,
                shippingPrice = shippingPrice,
                subtotal = subtotal,
                total = total!!
            )
            result
        } catch (exception: Exception) {
            Logger.error(
                this::class.java.name, "A field is null and is being skipped.",
                exception
            )
            null
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: JSONCart?) {
        if (value == null) {
            throw NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.beginObject()
        writer.name("SELLER")
        listOfNullableJSONSellerAdapter.toJson(writer, value.seller)
        writer.name("SHIPPINGPRICE")
        nullableIntAdapter.toJson(writer, value.shippingPrice)
        writer.name("SUBTOTAL")
        nullableIntAdapter.toJson(writer, value.subtotal)
        writer.name("TOTAL")
        intAdapter.toJson(writer, value.total)
        writer.endObject()
    }
}