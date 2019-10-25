package com.cabinInformationTechnologies.cabinCustomerBase.models.adapters

import com.squareup.moshi.*

class APIProductAdapter (moshi: Moshi) : JsonAdapter<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIProduct>() {
    private val options: JsonReader.Options = JsonReader.Options.of("PRODUCT")

    private val type = Types.newParameterizedType(List::class.java, com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONProduct::class.java)
    private val listOfNullableJSONProductAdapter: JsonAdapter<List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONProduct?>> =
        moshi.adapter<List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONProduct?>>(type, emptySet(), "products")

    override fun toString(): String = "GeneratedJsonAdapter(APIProduct)"

    @FromJson
    override fun fromJson(reader: JsonReader): com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIProduct {
        var products: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONProduct?>? = null
        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.selectName(options)) {
                0 -> products = listOfNullableJSONProductAdapter.fromJson(reader) ?: throw JsonDataException("Non-null value 'products' was null at ${reader.path}")
                -1 -> {
                    // Unknown name, skip it.
                    reader.skipName()
                    reader.nextString()
                }
            }
        }
        reader.endObject()
        return com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIProduct(
            products = products
                ?: throw JsonDataException("Required property 'products' missing at ${reader.path}")
        )
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APIProduct?) {
        if (value == null) {
            throw NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.beginObject()
        writer.name("PRODUCT")
        listOfNullableJSONProductAdapter.toJson(writer, value.products)
        writer.endObject()
    }
}