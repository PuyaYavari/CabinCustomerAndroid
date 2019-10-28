package com.cabinInformationTechnologies.cabinCustomerBase.models.adapters

import android.content.Context
import com.squareup.moshi.*

class APICityAdapter(val context: Context, moshi: Moshi) : JsonAdapter<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APICity>() {
    private val options: JsonReader.Options = JsonReader.Options.of("CITY")

    private val listOfNullableJSONCityAdapter: JsonAdapter<List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONCity?>> =
        Moshi.Builder().add(com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.JSONCityAdapter(context,Moshi.Builder().build())).build()
            .adapter<List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONCity?>>(Types.newParameterizedType(List::class.java,
                com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONCity::class.java), emptySet(), "cities")

    override fun toString(): String = "GeneratedJsonAdapter(APICity)"

    @FromJson
    override fun fromJson(reader: JsonReader): com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APICity {
        var cities: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONCity?>? = null
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
                com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(
                    context,
                    this::class.java.name,
                    "A field is null and is being skipped.",
                    exception
                )
                reader.skipValue()
            }
        }
        reader.endObject()
        val result = com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APICity(
            cities = cities
                ?: throw JsonDataException("Required property 'cities' missing at ${reader.path}")
        )
        return result
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APICity?) {
        if (value == null) {
            throw NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.beginObject()
        writer.name("CITY")
        listOfNullableJSONCityAdapter.toJson(writer, value.cities)
        writer.endObject()
    }
}