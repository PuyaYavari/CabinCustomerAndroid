package com.cabinInformationTechnologies.cabinCustomerBase.models.adapters

import android.content.Context
import com.squareup.moshi.*

class JSONColorAdapter (val context: Context, moshi: Moshi) : JsonAdapter<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONColor>() {
    private val options: JsonReader.Options =
        JsonReader.Options.of("ID", "NAME", "HEX_CODE", "RGB_CODE", "ISFAVORITE", "IMAGE", "SIZE")

    private val intAdapter: JsonAdapter<Int> =
        moshi.adapter<Int>(Int::class.java, emptySet(), "id")

    private val nullableStringAdapter: JsonAdapter<String?> =
        moshi.adapter<String?>(String::class.java, emptySet(), "name")

    private val stringAdapter: JsonAdapter<String> =
        moshi.adapter<String>(String::class.java, emptySet(), "hexCode")

    private val nullableBooleanAdapter: JsonAdapter<Boolean?> =
        moshi.adapter<Boolean?>(Boolean::class.javaObjectType, emptySet(), "isFavorite")

    private val listOfJSONImageAdapter: JsonAdapter<List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONImage>?> =
        Moshi.Builder().add(com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.JSONImageAdapter(context,Moshi.Builder().build())).build()
            .adapter<List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONImage>?>(Types.newParameterizedType(List::class.java, com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONImage::class.java),
                emptySet(), "images")

    private val listOfJSONSizeAdapter: JsonAdapter<List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONSize>> =
        Moshi.Builder().add(com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.JSONSizeAdapter(context,Moshi.Builder().build())).build()
            .adapter<List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONSize>>(Types.newParameterizedType(List::class.java, com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONSize::class.java),
                emptySet(), "filterSizes")

    override fun toString(): String = "GeneratedJsonAdapter(JSONColor)"

    @FromJson
    override fun fromJson(reader: JsonReader): com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONColor? {
        var id: Int? = null
        var name: String? = null
        var hexCode: String? = null
        var rgbCode: String? = null
        var isFavorite: Boolean? = null
        var images: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONImage>? = null
        var sizes: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONSize>? = null
        reader.beginObject()
        while (reader.hasNext()) {
            try {
                when (reader.selectName(options)) {
                    0 -> id = intAdapter.fromJson(reader) //?: throw JsonDataException("Non-null value 'id' was null at ${reader.path}")
                    1 -> name = nullableStringAdapter.fromJson(reader)
                    2 -> hexCode = stringAdapter.fromJson(reader) //?: throw JsonDataException("Non-null value 'hexCode' was null at ${reader.path}")
                    3 -> rgbCode = nullableStringAdapter.fromJson(reader)
                    4 -> isFavorite = nullableBooleanAdapter.fromJson(reader)
                    5 -> images = listOfJSONImageAdapter.fromJson(reader)
                    6 -> sizes =
                        listOfJSONSizeAdapter.fromJson(reader) //?: throw JsonDataException("Non-null value 'filterSizes' was null at ${reader.path}")
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
        var imagePresent = false
        var sizePresent = false
        try {
            val result = com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONColor(
                id = id!!,
                name = name,
                hexCode = hexCode!!,
                rgbCode = rgbCode,
                isFavorite = isFavorite,
                images = images,
                sizes = sizes!!
            )
            images?.forEach {
                if (it != null)
                    imagePresent = true
            }
            sizes.forEach {
                if (it != null)
                    sizePresent = true
            }
            if (imagePresent && sizePresent)
                return result
            return null
        } catch(exception: Exception){
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(
                context,
                this::class.java.name,
                "A field is null, this object will be null and won't be visible in app.",
                exception
            )
            return null
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONColor?) {
        if (value == null) {
            throw NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.beginObject()
        writer.name("ID")
        intAdapter.toJson(writer, value.id)
        writer.name("NAME")
        nullableStringAdapter.toJson(writer, value.name)
        writer.name("HEX_CODE")
        stringAdapter.toJson(writer, value.hexCode)
        writer.name("RGB_CODE")
        nullableStringAdapter.toJson(writer, value.rgbCode)
        writer.name("ISFAVORITE")
        nullableBooleanAdapter.toJson(writer, value.isFavorite)
        writer.name("IMAGE")
        listOfJSONImageAdapter.toJson(writer, value.images)
        writer.name("SIZE")
        listOfJSONSizeAdapter.toJson(writer, value.sizes)
        writer.endObject()
    }
}