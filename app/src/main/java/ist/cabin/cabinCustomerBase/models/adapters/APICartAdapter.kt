package ist.cabin.cabinCustomerBase.models.adapters

import com.squareup.moshi.*
import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.APICart
import ist.cabin.cabinCustomerBase.models.backend.JSONCart

class APICartAdapter(moshi: Moshi) : JsonAdapter<APICart>() {
    private val options: JsonReader.Options = JsonReader.Options.of("BASKET")

    private val listOfNullableJSONCartAdapter: JsonAdapter<List<JSONCart?>> =
        Moshi.Builder().add(JSONCartAdapter(Moshi.Builder().build())).build()
            .adapter<List<JSONCart?>>(Types.newParameterizedType(List::class.java,
                JSONCart::class.java), kotlin.collections.emptySet(), "cart")

    override fun toString(): String = "GeneratedJsonAdapter(APICart)"

    @FromJson
    override fun fromJson(reader: JsonReader): APICart? {
        var cart: List<JSONCart?>? = null
        reader.beginObject()
        while (reader.hasNext()) {
            try {
                when (reader.selectName(options)) {
                    0 -> cart = listOfNullableJSONCartAdapter.fromJson(reader)
                        //?: throw JsonDataException("Non-null value 'cart' was null at ${reader.path}")
                    -1 -> {
                        // Unknown name, skip it.
                        reader.skipName()
                        reader.skipValue()
                    }
                }
            } catch (exception: Exception) {
                Logger.warn(
                    this::class.java.name, "A field is null and is being skipped.",
                    exception
                )
                reader.skipValue()
            }
        }
        reader.endObject()
        return try {
            val result = APICart(
                cart = cart!!
            )
            result
        } catch (exception: Exception) {
            Logger.warn(
                this::class.java.name,
                "A field is null, this object will be null and won't be visible in app.",
                exception
            )
            null
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: APICart?) {
        if (value == null) {
            throw NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.beginObject()
        writer.name("BASKET")
        listOfNullableJSONCartAdapter.toJson(writer, value.cart)
        writer.endObject()
    }
}