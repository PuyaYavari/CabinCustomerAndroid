package ist.cabin.cabinCustomerBase.models.adapters

import android.util.Log
import com.squareup.moshi.*
import ist.cabin.cabinCustomerBase.models.backend.APIProduct
import ist.cabin.cabinCustomerBase.models.backend.JSONProduct

class APIProductAdapter (moshi: Moshi) : JsonAdapter<APIProduct>() {
    private val options: JsonReader.Options = JsonReader.Options.of("PRODUCT")

    private val type = Types.newParameterizedType(List::class.java, JSONProduct::class.java)
    private val listOfNullableJSONProductAdapter: JsonAdapter<List<JSONProduct?>> =
        moshi.adapter<List<JSONProduct?>>(type, emptySet(), "products")

    override fun toString(): String = "GeneratedJsonAdapter(APIProduct)"

    override fun fromJson(reader: JsonReader): APIProduct {
        var products: List<JSONProduct?>? = null
        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.selectName(options)) {
                0 -> products = listOfNullableJSONProductAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'products' was null at ${reader.path}")
                -1 -> {
                    // Unknown name, skip it.
                    reader.skipName()
                    reader.nextString()
                }
            }
        }
        reader.endObject()
        val result = APIProduct(
            products = products ?: throw JsonDataException("Required property 'products' missing at ${reader.path}")
        )
        Log.d("Products", products.toString())
        Log.d("Result", result.toString())
        return result
    }

    override fun toJson(writer: JsonWriter, value: APIProduct?) {
        if (value == null) {
            throw NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.beginObject()
        writer.name("PRODUCT")
        listOfNullableJSONProductAdapter.toJson(writer, value.products)
        writer.endObject()
    }
}