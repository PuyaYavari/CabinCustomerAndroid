package ist.cabin.cabinCustomerBase.models.adapters

import com.squareup.moshi.*
import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.JSONProduct
import ist.cabin.cabinCustomerBase.models.backend.JSONSeller

class JSONSellerAdapter(moshi: Moshi) : JsonAdapter<JSONSeller>() {
    private val options: JsonReader.Options =
        JsonReader.Options.of("ID", "NAME", "PRODUCT", "SELLERSHIPPINGPRICE", "SELLERSUBTOTAL", "SELLERTOTAL")

    private val intAdapter: JsonAdapter<Int> =
        moshi.adapter<Int>(Int::class.java, kotlin.collections.emptySet(), "id")

    private val stringAdapter: JsonAdapter<String> =
        moshi.adapter<String>(String::class.java, kotlin.collections.emptySet(), "name")

    private val listOfNullableJSONProductAdapter: JsonAdapter<List<JSONProduct?>> =
        Moshi.Builder().add(JSONProductAdapter(Moshi.Builder().build())).build()
            .adapter<List<JSONProduct?>>(Types.newParameterizedType(List::class.java,
                JSONProduct::class.java), kotlin.collections.emptySet(), "products")

    private val nullableIntAdapter: JsonAdapter<Int?> =
        moshi.adapter<Int?>(Int::class.javaObjectType, kotlin.collections.emptySet(), "shippingPrice")

    override fun toString(): String = "GeneratedJsonAdapter(JSONSeller)"

    @FromJson
    override fun fromJson(reader: JsonReader): JSONSeller? {
        var id: Int? = null
        var name: String? = null
        var products: List<JSONProduct?>? = null
        var shippingPrice: Int? = null
        var subtotal: Int? = null
        var total: Int? = null
        reader.beginObject()
        while (reader.hasNext()) {
            try {
                when (reader.selectName(options)) {
                    0 -> id = intAdapter.fromJson(reader)
                        //?: throw JsonDataException("Non-null value 'id' was null at ${reader.path}")
                    1 -> name = stringAdapter.fromJson(reader)
                        //?: throw JsonDataException("Non-null value 'name' was null at ${reader.path}")
                    2 -> products = listOfNullableJSONProductAdapter.fromJson(reader)
                        //?: throw JsonDataException("Non-null value 'products' was null at ${reader.path}")
                    3 -> shippingPrice = nullableIntAdapter.fromJson(reader)
                    4 -> subtotal = nullableIntAdapter.fromJson(reader)
                    5 -> total = intAdapter.fromJson(reader)
                        //?: throw JsonDataException("Non-null value 'total' was null at ${reader.path}")
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
                return null
            }
        }
        reader.endObject()
        return try {
            val result = JSONSeller(
                id = id
                    ?: throw JsonDataException("Required property 'id' missing at ${reader.path}"),
                name = name
                    ?: throw JsonDataException("Required property 'name' missing at ${reader.path}"),
                products = products
                    ?: throw JsonDataException("Required property 'products' missing at ${reader.path}"),
                shippingPrice = shippingPrice,
                subtotal = subtotal,
                total = total
                    ?: throw JsonDataException("Required property 'total' missing at ${reader.path}")
            )
            result
        } catch (exception: java.lang.Exception) {
            Logger.error(
                this::class.java.name, "A field is null and is being skipped.",
                exception
            )
            null
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: JSONSeller?) {
        if (value == null) {
            throw NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.beginObject()
        writer.name("ID")
        intAdapter.toJson(writer, value.id)
        writer.name("NAME")
        stringAdapter.toJson(writer, value.name)
        writer.name("PRODUCT")
        listOfNullableJSONProductAdapter.toJson(writer, value.products)
        writer.name("SELLERSHIPPINGPRICE")
        nullableIntAdapter.toJson(writer, value.shippingPrice)
        writer.name("SELLERSUBTOTAL")
        nullableIntAdapter.toJson(writer, value.subtotal)
        writer.name("SELLERTOTAL")
        intAdapter.toJson(writer, value.total)
        writer.endObject()
    }
}