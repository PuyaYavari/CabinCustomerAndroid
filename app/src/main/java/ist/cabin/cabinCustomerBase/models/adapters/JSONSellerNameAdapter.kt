package ist.cabin.cabinCustomerBase.models.adapters

import com.squareup.moshi.*
import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.JSONSellerName

class JSONSellerNameAdapter (moshi: Moshi) : JsonAdapter<JSONSellerName>() {
    private val options: JsonReader.Options = JsonReader.Options.of("ID", "NAME")

    private val intAdapter: JsonAdapter<Int> =
        moshi.adapter<Int>(Int::class.java, emptySet(), "id")

    private val stringAdapter: JsonAdapter<String> =
        moshi.adapter<String>(String::class.java, emptySet(), "name")

    override fun toString(): String = "GeneratedJsonAdapter(JSONSellerName)"

    @FromJson
    override fun fromJson(reader: JsonReader): JSONSellerName? {
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
                Logger.warn(
                    this::class.java.name, "A field is null and is being skipped.",
                    exception
                )
                reader.skipValue()
            }
        }
        reader.endObject()
        return try {
            val result = JSONSellerName(
                id = id!!,
                name = name!!
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
    override fun toJson(writer: JsonWriter, value: JSONSellerName?) {
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