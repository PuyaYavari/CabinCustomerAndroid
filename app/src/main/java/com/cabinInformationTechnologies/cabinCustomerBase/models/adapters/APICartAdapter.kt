package com.cabinInformationTechnologies.cabinCustomerBase.models.adapters

import android.content.Context
import com.cabinInformationTechnologies.cabin.fragments.cart.CabinCustomerCartContracts
import com.squareup.moshi.*

class APICartAdapter(val context: Context, moshi: Moshi, callback: CabinCustomerCartContracts.CartCallback?) : JsonAdapter<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APICart>() {
    private val options: JsonReader.Options = JsonReader.Options.of("BASKET")

    private val listOfNullableJSONCartAdapter: JsonAdapter<List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONCart?>> =
        Moshi.Builder().add(
            com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.JSONCartAdapter(
                context,
                Moshi.Builder().build(),
                callback
            )
        ).build()
            .adapter<List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONCart?>>(Types.newParameterizedType(List::class.java,
                com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONCart::class.java), kotlin.collections.emptySet(), "cart")

    override fun toString(): String = "GeneratedJsonAdapter(APICart)"

    @FromJson
    override fun fromJson(reader: JsonReader): com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APICart? {
        var cart: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONCart?>? = null
        reader.beginObject()
        while (reader.hasNext()) {
            try {
                when (reader.selectName(options)) {
                    0 -> cart = listOfNullableJSONCartAdapter.fromJson(reader)
                        //?: throw JsonDataException("Non-null value 'cart' was null at ${reader.path}")
                    -1 -> {
                        // Unknown name, skip it.
                        reader.skipName()
                        reader.skipValue()
                    }
                }
            } catch (exception: Exception) {
                com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(
                    context,
                    this::class.java.name, "A field is null and is being skipped.",
                    exception
                )
                reader.skipValue()
            }
        }
        reader.endObject()
        return try {
            val result = com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APICart(
                cart = cart!!
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
    override fun toJson(writer: JsonWriter, value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APICart?) {
        if (value == null) {
            throw NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.beginObject()
        writer.name("BASKET")
        listOfNullableJSONCartAdapter.toJson(writer, value.cart)
        writer.endObject()
    }
}