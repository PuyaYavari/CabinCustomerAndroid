package ist.cabin.cabinCustomerBase.models.adapters

import com.squareup.moshi.*
import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.APIAddress
import ist.cabin.cabinCustomerBase.models.backend.JSONAddress

class APIAddressAdapter(moshi: Moshi) : JsonAdapter<APIAddress>() {
    private val options: JsonReader.Options = JsonReader.Options.of("ADDRESS")

    private val listOfNullableJSONAddressAdapter: JsonAdapter<List<JSONAddress?>> =
        Moshi.Builder().add(JSONAddressAdapter(Moshi.Builder().build())).build()
            .adapter<List<JSONAddress?>>(Types.newParameterizedType(List::class.java,
                JSONAddress::class.java), emptySet(), "addresses")

    override fun toString(): String = "GeneratedJsonAdapter(APIAddress)"

    @FromJson
    override fun fromJson(reader: JsonReader): APIAddress? {
        var addresses: List<JSONAddress?>? = null
        reader.beginObject()
        while (reader.hasNext()) {
            try {
                when (reader.selectName(options)) {
                    0 -> addresses = listOfNullableJSONAddressAdapter.fromJson(reader)
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
            val result = APIAddress(
                addresses = addresses!!
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
    override fun toJson(writer: JsonWriter, value: APIAddress?) {
        if (value == null) {
            throw NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.beginObject()
        writer.name("ADDRESS")
        listOfNullableJSONAddressAdapter.toJson(writer, value.addresses)
        writer.endObject()
    }
}