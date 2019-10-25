package com.cabinInformationTechnologies.cabinCustomerBase.models.adapters

import com.squareup.moshi.*
import com.cabinInformationTechnologies.cabin.fragments.cart.CabinCustomerCartContracts

class JSONSellerAdapter(moshi: Moshi, callback: CabinCustomerCartContracts.CartCallback?) : JsonAdapter<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONSeller>() {
    private val sellerCallback = object : CabinCustomerCartContracts.CartCallback {
        override fun setSellerId(id: Int?) {
            callback?.setSellerId(id)
        }

        override fun setProductId(id: Int?) {
            callback?.setProductId(id)
        }

        override fun setColorId(id: Int?) {
            callback?.setColorId(id)
        }

        override fun feedback(message: String) {
            callback?.feedback(message)
        }

        override fun removeItems() {
            callback?.removeItems()
        }
    }

    private val options: JsonReader.Options =
        JsonReader.Options.of("ID", "NAME", "PRODUCT", "SELLERSHIPPINGPRICE", "SELLERSUBTOTAL", "SELLERTOTAL")

    private val intAdapter: JsonAdapter<Int> =
        moshi.adapter<Int>(Int::class.java, kotlin.collections.emptySet(), "id")

    private val stringAdapter: JsonAdapter<String> =
        moshi.adapter<String>(String::class.java, kotlin.collections.emptySet(), "name")

    private val listOfNullableJSONProductAdapter: JsonAdapter<List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONProduct?>> =
        Moshi.Builder().add(
            com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.JSONCartProductAdapter(
                Moshi.Builder().build(),
                sellerCallback
            )
        ).build()
            .adapter<List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONProduct?>>(Types.newParameterizedType(List::class.java,
                com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONProduct::class.java), kotlin.collections.emptySet(), "products")

    private val nullableDoubleAdapter: JsonAdapter<Double?> =
        moshi.adapter<Double?>(Double::class.javaObjectType, kotlin.collections.emptySet(), "shippingPrice")

    private val doubleAdapter: JsonAdapter<Double> =
        moshi.adapter<Double>(Double::class.java, kotlin.collections.emptySet(), "total")

    override fun toString(): String = "GeneratedJsonAdapter(JSONSeller)"

    @FromJson
    override fun fromJson(reader: JsonReader): com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONSeller? {
        var id: Int? = null
        var name: String? = null
        var products: List<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONProduct?>? = null
        var shippingPrice: Double? = null
        var subtotal: Double? = null
        var total: Double? = null
        sellerCallback?.setProductId(null)
        sellerCallback?.setColorId(null)
        reader.beginObject()
        while (reader.hasNext()) {
            try {
                when (reader.selectName(options)) {
                    0 -> {
                        id = intAdapter.fromJson(reader)
                        sellerCallback?.setProductId(id)
                    }
                    1 -> name = stringAdapter.fromJson(reader)
                    2 -> products = listOfNullableJSONProductAdapter.fromJson(reader)
                    3 -> shippingPrice = nullableDoubleAdapter.fromJson(reader)
                    4 -> subtotal = nullableDoubleAdapter.fromJson(reader)
                    5 -> total = doubleAdapter.fromJson(reader)
                    -1 -> {
                        // Unknown name, skip it.
                        reader.skipName()
                        reader.skipValue()
                    }
                }
            } catch (exception: Exception) {
                com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                    this::class.java.name, "A field is null and is being skipped.",
                    exception
                )
                return null
            }
        }
        reader.endObject()
        return try {
            val result = com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONSeller(
                id = id!!,
                name = name!!,
                products = products!!,
                shippingPrice = shippingPrice,
                subtotal = subtotal,
                total = total!!
            )
            result
        } catch (exception: java.lang.Exception) {
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                this::class.java.name, "A field is null and is being skipped.",
                exception
            )
            if (id != null) {
                sellerCallback?.feedback("Products of seller with id $id removed due to a serious problem!")
                sellerCallback?.removeItems()
            } else {
                sellerCallback?.feedback("Basket removed due to a serious problem!")
                sellerCallback?.removeItems()
            }

            null
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONSeller?) {
        if (value == null) {
            throw NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.beginObject()
        writer.name("ID")
        intAdapter.toJson(writer, value.id)
        writer.name("NAME")
        stringAdapter.toJson(writer, value.name)
        writer.name("PRODUCT")
        listOfNullableJSONProductAdapter.toJson(writer, value.products)
        writer.name("SELLERSHIPPINGPRICE")
        nullableDoubleAdapter.toJson(writer, value.shippingPrice)
        writer.name("SELLERSUBTOTAL")
        nullableDoubleAdapter.toJson(writer, value.subtotal)
        writer.name("SELLERTOTAL")
        doubleAdapter.toJson(writer, value.total)
        writer.endObject()
    }
}