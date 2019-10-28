package com.cabinInformationTechnologies.cabinCustomerBase.models.adapters

import android.content.Context
import com.squareup.moshi.*

class APIDistrictAdapter(val context: Context, moshi: Moshi) : JsonAdapter<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIDistrict>() {
    private val options: JsonReader.Options = JsonReader.Options.of("DISTRICT")

    private val listOfNullableJSONDistrictAdapter: JsonAdapter<List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONDistrict?>> =
        Moshi.Builder().add(com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.JSONDistrictAdapter(context,Moshi.Builder().build())).build()
            .adapter<List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONDistrict?>>(Types.newParameterizedType(List::class.java,
                com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONDistrict::class.java), emptySet(), "districts")

    override fun toString(): String = "GeneratedJsonAdapter(APIDistrict)"

    @FromJson
    override fun fromJson(reader: JsonReader): com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIDistrict {
        var districts: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONDistrict?>? = null
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
        var result = com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIDistrict(
            districts = districts
                ?: throw JsonDataException("Required property 'districts' missing at ${reader.path}")
        )
        return result
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIDistrict?) {
        if (value == null) {
            throw NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.beginObject()
        writer.name("DISTRICT")
        listOfNullableJSONDistrictAdapter.toJson(writer, value.districts)
        writer.endObject()
    }
}