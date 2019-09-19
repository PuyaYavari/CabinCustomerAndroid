package ist.cabin.cabinCustomerBase.models.adapters

import android.util.Log
import com.squareup.moshi.*
import ist.cabin.cabinCustomerBase.models.backend.JSONSeller

class JSONSellerAdapter (moshi: Moshi) : JsonAdapter<JSONSeller>() {
    private val options: JsonReader.Options = JsonReader.Options.of("ID", "NAME")

    private val intAdapter: JsonAdapter<Int> =
        moshi.adapter<Int>(Int::class.java, emptySet(), "id")

    private val stringAdapter: JsonAdapter<String> =
        moshi.adapter<String>(String::class.java, emptySet(), "name")

    override fun toString(): String = "GeneratedJsonAdapter(JSONSeller)"

    @FromJson
    override fun fromJson(reader: JsonReader): JSONSeller? {
        var id: Int? = null
        var name: String? = null
        reader.beginObject()
        while (reader.hasNext()) {
            try {
                when (reader.selectName(options)) {
                    0 -> id = intAdapter.fromJson(reader)
                        //?: throw JsonDataException("Non-null value 'id' was null at ${reader.path}")
                    1 -> name = stringAdapter.fromJson(reader)
                        //?: throw JsonDataException("Non-null value 'name' was null at ${reader.path}")
                    -1 -> {
                        // Unknown name, skip it.
                        reader.skipName()
                        reader.skipValue()
                    }
                }
            } catch (exception: Exception) {
                Log.e("JSONSellerAdapter", exception.message.toString())
                reader.skipValue()
            }
        }
        reader.endObject()
        return try {
            val result = JSONSeller(
                id = id!!,
                name = name!!
            )
            result
        } catch (exception: Exception) {
            Log.e("JSONSellerAdapter", exception.message.toString())
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
        writer.endObject()
    }
}