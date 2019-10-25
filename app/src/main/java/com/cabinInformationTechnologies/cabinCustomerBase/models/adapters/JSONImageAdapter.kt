package com.cabinInformationTechnologies.cabinCustomerBase.models.adapters

import com.squareup.moshi.*

class JSONImageAdapter (moshi: Moshi) : JsonAdapter<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONImage>() {
    private val options: JsonReader.Options = JsonReader.Options.of("URL", "PRIORITY")

    private val stringAdapter: JsonAdapter<String> =
        moshi.adapter<String>(String::class.java, kotlin.collections.emptySet(), "url")

    private val nullableBooleanAdapter: JsonAdapter<Boolean?> =
        moshi.adapter<Boolean?>(Boolean::class.javaObjectType, kotlin.collections.emptySet(), "priority")

    override fun toString(): String = "GeneratedJsonAdapter(JSONImage)"

    @FromJson
    override fun fromJson(reader: JsonReader): com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONImage? {
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
                com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(
                    this::class.java.name, "A field is null and is being skipped.",
                    exception
                )
                reader.skipValue()
            }
        }
        reader.endObject()
        return try {
            val result = com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONImage(
                url = url!!,
                priority = priority
            )
            result
        } catch(exception: Exception){
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(
                this::class.java.name,
                "A field is null, this object will be null and won't be visible in app.",
                exception
            )
            null
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONImage?) {
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
