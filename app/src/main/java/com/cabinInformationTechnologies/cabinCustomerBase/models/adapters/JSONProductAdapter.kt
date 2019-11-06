package com.cabinInformationTechnologies.cabinCustomerBase.models.adapters

import android.content.Context
import com.squareup.moshi.*

class JSONProductAdapter (val context: Context, moshi: Moshi) : JsonAdapter<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONProduct>() {
    private val options: JsonReader.Options =
        JsonReader.Options.of("ID", "CODE", "TITLE", "PRICE", "AMOUNT", "SELLER", "SHIPPING_DURATION", "SHIPPING_TYPE", "COLOR")

    private val intAdapter: JsonAdapter<Int> =
        moshi.adapter<Int>(Int::class.java, emptySet(), "id")

    private val stringAdapter: JsonAdapter<String> =
        moshi.adapter<String>(String::class.java, emptySet(), "code")

    private val doubleAdapter: JsonAdapter<Double> =
        moshi.adapter<Double>(Double::class.java, emptySet(), "price")

    private val listOfJSONSellerNameAdapter: JsonAdapter<List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONSellerName>> =
        Moshi.Builder().add(com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.JSONSellerNameAdapter(context,Moshi.Builder().build())).build()
            .adapter<List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONSellerName>>(Types.newParameterizedType(List::class.java, com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONSellerName::class.java),
                emptySet(), "sellerName")

    private val nullableListOfJSONShippingDurationAdapter: JsonAdapter<List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONShippingDuration>?> =
        Moshi.Builder().add(
            com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.JSONShippingDurationAdapter(
                context,
                Moshi.Builder().build()
            )
        ).build()
            .adapter(Types.newParameterizedType(List::class.java, com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONShippingDuration::class.java),
                emptySet(), "shippingDuration")

    private val listOfJSONShippingTypeAdapter: JsonAdapter<List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONShippingType>> =
        Moshi.Builder().add(
            com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.JSONShippingTypeAdapter(
                context,
                Moshi.Builder().build()
            )
        ).build()
            .adapter<List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONShippingType>>(Types.newParameterizedType(List::class.java, com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONShippingType::class.java),
                emptySet(), "shippingType")

    private val listOfJSONColorAdapter: JsonAdapter<List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONColor>> =
        Moshi.Builder().add(com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.JSONColorAdapter(context,Moshi.Builder().build())).build()
            .adapter<List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONColor>>(Types.newParameterizedType(List::class.java, com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONColor::class.java),
                emptySet(), "colors")

    override fun toString(): String = "GeneratedJsonAdapter(JSONProduct)"

    @FromJson
    override fun fromJson(reader: JsonReader): com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONProduct? {
        var id: Int? = null
        var code: String? = null
        var title: String? = null
        var price: Double? = null
        var amount: Int? = null
        var sellerName: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONSellerName>? = null
        var shippingDuration: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONShippingDuration>? = null
        var shippingType: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONShippingType>? = null
        var colors: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONColor>? = null
        reader.beginObject()
        while (reader.hasNext()) {
            try {
                when (reader.selectName(options)) {
                        0 -> id = intAdapter.fromJson(reader) //?: throw JsonDataException("Non-null value 'id' was null at ${reader.path}")
                        1 -> code = stringAdapter.fromJson(reader) //?: throw JsonDataException("Non-null value 'code' was null at ${reader.path}")
                        2 -> title = stringAdapter.fromJson(reader) //?: throw JsonDataException("Non-null value 'title' was null at ${reader.path}")
                        3 -> price = doubleAdapter.fromJson(reader) //?: throw JsonDataException("Non-null value 'price' was null at ${reader.path}")
                        4 -> amount = intAdapter.fromJson(reader) //?: throw JsonDataException("Non-null value 'amount' was null at ${reader.path}")
                        5 -> sellerName = listOfJSONSellerNameAdapter.fromJson(reader) //?: throw JsonDataException("Non-null value 'sellerName' was null at ${reader.path}")
                        6 -> shippingDuration = nullableListOfJSONShippingDurationAdapter.fromJson(reader)
                        7 -> shippingType = listOfJSONShippingTypeAdapter.fromJson(reader) //?: throw JsonDataException("Non-null value 'shippingType' was null at ${reader.path}")
                        8 -> colors = listOfJSONColorAdapter.fromJson(reader) //?: throw JsonDataException("Non-null value 'colors' was null at ${reader.path}")
                        -1 -> {
                            // Unknown name, skip it.
                            reader.skipName()
                            reader.skipValue()
                        }
                }
            } catch (exception: Exception){
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
            val result = com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONProduct(
                id = id!!,
                code = code!!,
                title = title!!,
                price = price!!,
                amount = amount,
                sellerName = sellerName!!,
                shippingDuration = shippingDuration!!,
                shippingType = shippingType!!,
                colors = colors!!
            )
            colors.forEach {
                if (it != null)
                    return result
            }
            null
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
    override fun toJson(writer: JsonWriter, value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONProduct?) {
        if (value == null) {
            throw NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.beginObject()
        writer.name("ID")
        intAdapter.toJson(writer, value.id)
        writer.name("CODE")
        stringAdapter.toJson(writer, value.code)
        writer.name("TITLE")
        stringAdapter.toJson(writer, value.title)
        writer.name("PRICE")
        doubleAdapter.toJson(writer, value.price)
        writer.name("AMOUNT")
        intAdapter.toJson(writer, value.amount)
        writer.name("SELLER")
        listOfJSONSellerNameAdapter.toJson(writer, value.sellerName)
        writer.name("SHIPPING_DURATION")
        nullableListOfJSONShippingDurationAdapter.toJson(writer, value.shippingDuration)
        writer.name("SHIPPING_TYPE")
        listOfJSONShippingTypeAdapter.toJson(writer, value.shippingType)
        writer.name("COLOR")
        listOfJSONColorAdapter.toJson(writer, value.colors)
        writer.endObject()
    }
}