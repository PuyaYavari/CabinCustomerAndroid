package com.cabinInformationTechnologies.cabinCustomerBase.models.adapters

import com.squareup.moshi.*

class JSONSellerNameAdapter (moshi: Moshi) : JsonAdapter<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONSellerName>() {
    private val options: JsonReader.Options = JsonReader.Options.of("ID", "NAME")

    private val intAdapter: JsonAdapter<Int> =
        moshi.adapter<Int>(Int::class.java, emptySet(), "id")

    private val stringAdapter: JsonAdapter<String> =
        moshi.adapter<String>(String::class.java, emptySet(), "name")

    override fun toString(): String = "GeneratedJsonAdapter(JSONSellerName)"

    @FromJson
    override fun fromJson(reader: JsonReader): com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONSellerName? {
        var id: Int? = null
        var name: String? = null
        reader.beginObject()
        while (reader.hasNext()) {
            try {
                when (reader.selectName(options)) {
                    0 -> id = intAdapter.fromJson(reader)
                    1 -> name = stringAdapter.fromJson(reader)
                    -1 -> {
                        // Unknown name, skip it.
                        reader.skipName()
                        reader.skipValue()
                    }
                }
            } catch (exception: Exception) {
                com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(
                    this::class.java.name, "A field is null and is being skipped.",
                    exception
                )
                reader.skipValue()
            }
        }
        reader.endObject()
        return try {
            val result = com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONSellerName(
                id = id!!,
                name = name!!
            )
            result
        } catch (exception: Exception) {
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(
                this::class.java.name,
                "A field is null, this object will be null and won't be visible in app.",
                exception
            )
            null
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONSellerName?) {
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