package ist.cabin.cabinCustomerBase.models.adapters

import com.squareup.moshi.*
import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.JSONCity

class JSONCityAdapter(moshi: Moshi) : JsonAdapter<JSONCity>() {
    private val options: JsonReader.Options = JsonReader.Options.of("ID", "NAME", "CODE")

    private val intAdapter: JsonAdapter<Int> =
        moshi.adapter<Int>(Int::class.java, kotlin.collections.emptySet(), "id")

    private val stringAdapter: JsonAdapter<String> =
        moshi.adapter<String>(String::class.java, kotlin.collections.emptySet(), "name")

    override fun toString(): String = "GeneratedJsonAdapter(JSONCity)"

    @FromJson
    override fun fromJson(reader: JsonReader): JSONCity? {
        var id: Int? = null
        var name: String? = null
        var code: String? = null
        reader.beginObject()
        while (reader.hasNext()) {
            try {
                when (reader.selectName(options)) {
                    0 -> id = intAdapter.fromJson(reader)
                        //?: throw JsonDataException("Non-null value 'id' was null at ${reader.path}")
                    1 -> name = stringAdapter.fromJson(reader)
                        //?: throw JsonDataException("Non-null value 'name' was null at ${reader.path}")
                    2 -> code = stringAdapter.fromJson(reader)
                        //?: throw JsonDataException("Non-null value 'code' was null at ${reader.path}")
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
            val result = JSONCity(
                id = id!!,
                name = name!!,
                code = code
            )
            result
        } catch (exception: Exception) {
            Logger.warn(
                this::class.java.name, "A field is null and is being skipped.",
                exception
            )
            null
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: JSONCity?) {
        if (value == null) {
            throw NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.beginObject()
        writer.name("ID")
        intAdapter.toJson(writer, value.id)
        writer.name("NAME")
        stringAdapter.toJson(writer, value.name)
        writer.name("CODE")
        stringAdapter.toJson(writer, value.code)
        writer.endObject()
    }
}