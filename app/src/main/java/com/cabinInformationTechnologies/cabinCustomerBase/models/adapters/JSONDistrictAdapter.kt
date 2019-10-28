package com.cabinInformationTechnologies.cabinCustomerBase.models.adapters

import android.content.Context
import com.squareup.moshi.*

class JSONDistrictAdapter(val context: Context, moshi: Moshi) : JsonAdapter<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONDistrict>() {
    private val options: JsonReader.Options =
        JsonReader.Options.of("ID", "NAME", "CODE", "CITY_CODE")

    private val intAdapter: JsonAdapter<Int> =
        moshi.adapter<Int>(Int::class.java, kotlin.collections.emptySet(), "id")

    private val stringAdapter: JsonAdapter<String> =
        moshi.adapter<String>(String::class.java, kotlin.collections.emptySet(), "name")

    private val nullableStringAdapter: JsonAdapter<String?> =
        moshi.adapter<String?>(String::class.java, kotlin.collections.emptySet(), "cityCode")

    override fun toString(): String = "GeneratedJsonAdapter(JSONDistrict)"

    @FromJson
    override fun fromJson(reader: JsonReader): com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONDistrict? {
        var id: Int? = null
        var name: String? = null
        var code: String? = null
        var cityCode: String? = null
        reader.beginObject()
        while (reader.hasNext()) {
            try {
                when (reader.selectName(options)) {
                    0 -> id =
                        intAdapter.fromJson(reader) //?: throw JsonDataException("Non-null value 'id' was null at ${reader.path}")
                    1 -> name =
                        stringAdapter.fromJson(reader) //?: throw JsonDataException("Non-null value 'name' was null at ${reader.path}")
                    2 -> code =
                        stringAdapter.fromJson(reader) //?: throw JsonDataException("Non-null value 'code' was null at ${reader.path}")
                    3 -> cityCode = nullableStringAdapter.fromJson(reader)
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
        return try {
            val result = com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONDistrict(
                id = id!!,
                name = name!!,
                code = code,
                cityCode = cityCode
            )
            result
        } catch (exception: Exception) {
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(
                context,
                this::class.java.name,
                "A field is null, this object will be null and won't be visible in app.",
                exception
            )
            null
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONDistrict?) {
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
        writer.name("CITY_CODE")
        nullableStringAdapter.toJson(writer, value.cityCode)
        writer.endObject()
    }
}