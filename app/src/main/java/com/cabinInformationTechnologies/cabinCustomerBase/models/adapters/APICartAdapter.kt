package com.cabinInformationTechnologies.cabinCustomerBase.models.adapters

import android.content.Context
import com.cabinInformationTechnologies.cabin.fragments.cart.CabinCustomerCartContracts
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONCart
import com.squareup.moshi.*

class APICartAdapter(val context: Context, moshi: Moshi, callback: CabinCustomerCartContracts.CartCallback?) : JsonAdapter<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APICart>() {
    private val options: JsonReader.Options = JsonReader.Options.of("BASKET")

    private val listOfNullableJSONCartAdapter: JsonAdapter<List<JSONCart?>> =
        Moshi.Builder().add(
            JSONCartAdapter(
                context,
                Moshi.Builder().build(),
                callback
            )
        ).build()
            .adapter<List<JSONCart?>>(Types.newParameterizedType(List::class.java, JSONCart::class.java)
                , kotlin.collections.emptySet(), "cart")

    override fun toString(): String = "GeneratedJsonAdapter(APICart)"

    @FromJson
    override fun fromJson(reader: JsonReader): com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APICart? {
        var cart: List<JSONCart?>? = null
        reader.beginObject()
        while (reader.hasNext()) {
            try {
                when (reader.selectName(options)) {
                    0 -> cart = listOfNullableJSONCartAdapter.fromJson(reader)
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