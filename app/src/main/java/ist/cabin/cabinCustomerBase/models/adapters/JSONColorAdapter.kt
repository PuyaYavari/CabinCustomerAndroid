package ist.cabin.cabinCustomerBase.models.adapters

import android.util.Log
import com.squareup.moshi.*
import ist.cabin.cabinCustomerBase.models.backend.JSONColor
import ist.cabin.cabinCustomerBase.models.backend.JSONImage
import ist.cabin.cabinCustomerBase.models.backend.JSONSize

class JSONColorAdapter (moshi: Moshi) : JsonAdapter<JSONColor>() {
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

    private val listOfJSONImageAdapter: JsonAdapter<List<JSONImage>> =
        moshi.adapter<List<JSONImage>>(Types.newParameterizedType(List::class.java, JSONImage::class.java), emptySet(), "images")

    private val listOfJSONSizeAdapter: JsonAdapter<List<JSONSize>> =
        moshi.adapter<List<JSONSize>>(Types.newParameterizedType(List::class.java, JSONSize::class.java), emptySet(), "sizes")

    override fun toString(): String = "GeneratedJsonAdapter(JSONColor)"

    @FromJson
    override fun fromJson(reader: JsonReader): JSONColor? {
        var id: Int? = null
        var name: String? = null
        var hexCode: String? = null
        var rgbCode: String? = null
        var isFavorite: Boolean? = null
        var images: List<JSONImage>? = null
        var sizes: List<JSONSize>? = null
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
                        listOfJSONSizeAdapter.fromJson(reader) //?: throw JsonDataException("Non-null value 'sizes' was null at ${reader.path}")
                    -1 -> {
                        // Unknown name, skip it.
                        reader.skipName()
                        reader.skipValue()
                    }
                }
            } catch (exception: Exception) {
                Log.e("JSONColorAdapter", exception.toString())
                reader.skipValue()
            }
        }
        reader.endObject()
        var imagePresent = false
        var sizePresent = false
        return try {
            val result = JSONColor(
                id = id!!,
                name = name,
                hexCode = hexCode!!,
                rgbCode = rgbCode,
                isFavorite = isFavorite,
                images = images!!,
                sizes = sizes!!
            )
            images.forEach {
                if (it != null)
                    imagePresent = true
            }
            sizes.forEach {
                if (it != null)
                    sizePresent = true
            }
            if (imagePresent && sizePresent)
                return result
            null
        } catch(exception: Exception){
            Log.e("JSONColorAdapter", exception.message.toString())
            null
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: JSONColor?) {
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