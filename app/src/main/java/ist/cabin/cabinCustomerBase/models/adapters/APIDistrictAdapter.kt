package ist.cabin.cabinCustomerBase.models.adapters

import com.squareup.moshi.*
import ist.cabin.cabinCustomerBase.models.backend.APIDistrict
import ist.cabin.cabinCustomerBase.models.backend.JSONDistrict

class APIDistrictAdapter(moshi: Moshi) : JsonAdapter<APIDistrict>() {
    private val options: JsonReader.Options = JsonReader.Options.of("DISTRICT")

    private val listOfNullableJSONDistrictAdapter: JsonAdapter<List<JSONDistrict?>> =
        Moshi.Builder().add(JSONDistrictAdapter(Moshi.Builder().build())).build()
            .adapter<List<JSONDistrict?>>(Types.newParameterizedType(List::class.java,
                JSONDistrict::class.java), emptySet(), "districts")

    override fun toString(): String = "GeneratedJsonAdapter(APIDistrict)"

    @FromJson
    override fun fromJson(reader: JsonReader): APIDistrict {
        var districts: List<JSONDistrict?>? = null
        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.selectName(options)) {
                0 -> districts = listOfNullableJSONDistrictAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'districts' was null at ${reader.path}")
                -1 -> {
                    // Unknown name, skip it.
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }
        reader.endObject()
        var result = APIDistrict(
            districts = districts ?: throw JsonDataException("Required property 'districts' missing at ${reader.path}")
        )
        return result
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: APIDistrict?) {
        if (value == null) {
            throw NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.beginObject()
        writer.name("DISTRICT")
        listOfNullableJSONDistrictAdapter.toJson(writer, value.districts)
        writer.endObject()
    }
}