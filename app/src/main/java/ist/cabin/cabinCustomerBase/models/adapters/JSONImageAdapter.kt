package ist.cabin.cabinCustomerBase.models.adapters

import com.squareup.moshi.*
import ist.cabin.cabinCustomerBase.models.backend.JSONImage

class JSONImageAdapter (moshi: Moshi) : JsonAdapter<JSONImage>() {
    private val options: JsonReader.Options = JsonReader.Options.of("URL", "PRIORITY")

    private val stringAdapter: JsonAdapter<String> =
        moshi.adapter<String>(String::class.java, kotlin.collections.emptySet(), "url")

    private val nullableBooleanAdapter: JsonAdapter<Boolean?> =
        moshi.adapter<Boolean?>(Boolean::class.javaObjectType, kotlin.collections.emptySet(), "priority")

    override fun toString(): String = "GeneratedJsonAdapter(JSONImage)"

    @FromJson
    override fun fromJson(reader: JsonReader): JSONImage? {
        var url: String? = null
        var priority: Boolean? = null
        reader.beginObject()
        while (reader.hasNext()) {
            try {
                when (reader.selectName(options)) {
                    0 -> url = stringAdapter.fromJson(reader)
                        //?: throw JsonDataException("Non-null value 'url' was null at ${reader.path}")
                    1 -> priority = nullableBooleanAdapter.fromJson(reader)
                    -1 -> {
                        // Unknown name, skip it.
                        reader.skipName()
                        reader.skipValue()
                    }
                }
            } catch (exception: Exception) {
                return null
            }
        }
        reader.endObject()
        return try {
            val result = JSONImage(
                url = url!!,
                priority = priority
            )
            result
        } catch (exception: Exception) {
            return null
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: JSONImage?) {
        if (value == null) {
            throw NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.beginObject()
        writer.name("URL")
        stringAdapter.toJson(writer, value.url)
        writer.name("PRIORITY")
        nullableBooleanAdapter.toJson(writer, value.priority)
        writer.endObject()
    }
}
