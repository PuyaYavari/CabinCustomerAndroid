package ist.cabin.cabinCustomerBase.models.adapters

import com.squareup.moshi.*
import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.APICity
import ist.cabin.cabinCustomerBase.models.backend.JSONCity

class APICityAdapter(moshi: Moshi) : JsonAdapter<APICity>() {
    private val options: JsonReader.Options = JsonReader.Options.of("CITY")

    private val listOfNullableJSONCityAdapter: JsonAdapter<List<JSONCity?>> =
        Moshi.Builder().add(JSONCityAdapter(Moshi.Builder().build())).build()
            .adapter<List<JSONCity?>>(Types.newParameterizedType(List::class.java,
                JSONCity::class.java), emptySet(), "cities")

    override fun toString(): String = "GeneratedJsonAdapter(APICity)"

    @FromJson
    override fun fromJson(reader: JsonReader): APICity {
        var cities: List<JSONCity?>? = null
        reader.beginObject()
        while (reader.hasNext()) {
            try {
                when (reader.selectName(options)) {
                    0 -> cities = listOfNullableJSONCityAdapter.fromJson(reader)
                        //?: throw JsonDataException("Non-null value 'cities' was null at ${reader.path}")
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
        val result = APICity(
            cities = cities ?: throw JsonDataException("Required property 'cities' missing at ${reader.path}")
        )
        return result
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: APICity?) {
        if (value == null) {
            throw NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.beginObject()
        writer.name("CITY")
        listOfNullableJSONCityAdapter.toJson(writer, value.cities)
        writer.endObject()
    }
}