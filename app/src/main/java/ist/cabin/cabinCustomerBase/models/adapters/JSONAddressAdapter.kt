package ist.cabin.cabinCustomerBase.models.adapters

import com.squareup.moshi.*
import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.JSONAddress
import ist.cabin.cabinCustomerBase.models.backend.JSONCity
import ist.cabin.cabinCustomerBase.models.backend.JSONDistrict

class JSONAddressAdapter(moshi: Moshi) : JsonAdapter<JSONAddress>() {
    private val options: JsonReader.Options =
        JsonReader.Options.of("ID", "BILLING", "NAME", "SURNAME", "PHONE", "CITY", "DISTRICT", "ADDRESS", "TITLE", "INDIVIDUAL", "COMPANY_NAME", "TAX_NUMBER", "TAX_AUTHORITY")

    private val intAdapter: JsonAdapter<Int> =
        moshi.adapter<Int>(Int::class.java, kotlin.collections.emptySet(), "id")

    private val booleanAdapter: JsonAdapter<Boolean> =
        moshi.adapter<Boolean>(Boolean::class.java, kotlin.collections.emptySet(), "isBilling")

    private val stringAdapter: JsonAdapter<String> =
        moshi.adapter<String>(String::class.java, kotlin.collections.emptySet(), "name")

    private val listOfJSONCityAdapter: JsonAdapter<List<JSONCity>> =
        Moshi.Builder().add(JSONCityAdapter(Moshi.Builder().build())).build()
            .adapter<List<JSONCity>>(Types.newParameterizedType(List::class.java,
                JSONCity::class.java), kotlin.collections.emptySet(), "province")

    private val listOfJSONDistrictAdapter: JsonAdapter<List<JSONDistrict>> =
        Moshi.Builder().add(JSONDistrictAdapter(Moshi.Builder().build())).build()
            .adapter<List<JSONDistrict>>(Types.newParameterizedType(List::class.java,
                JSONDistrict::class.java), kotlin.collections.emptySet(), "district")

    private val nullableStringAdapter: JsonAdapter<String?> =
        moshi.adapter<String?>(String::class.java, kotlin.collections.emptySet(), "companyName")

    override fun toString(): String = "GeneratedJsonAdapter(JSONAddress)"

    @FromJson
    override fun fromJson(reader: JsonReader): JSONAddress? {
        var id: Int? = null
        var isBilling: Boolean? = null
        var name: String? = null
        var surname: String? = null
        var phone: String? = null
        var province: List<JSONCity>? = null
        var district: List<JSONDistrict>? = null
        var address: String? = null
        var title: String? = null
        var isIndividual: Boolean? = null
        var companyName: String? = null
        var taxNumber: String? = null
        var taxAuthority: String? = null
        reader.beginObject()
        while (reader.hasNext()) {
            try {
                when (reader.selectName(options)) {
                    0 -> id = intAdapter.fromJson(reader)
                    1 -> isBilling = booleanAdapter.fromJson(reader)
                    2 -> name = stringAdapter.fromJson(reader)
                    3 -> surname = stringAdapter.fromJson(reader)
                    4 -> phone = stringAdapter.fromJson(reader)
                    5 -> province = listOfJSONCityAdapter.fromJson(reader)
                    6 -> district = listOfJSONDistrictAdapter.fromJson(reader)
                    7 -> address = stringAdapter.fromJson(reader)
                    8 -> title = stringAdapter.fromJson(reader)
                    9 -> isIndividual = booleanAdapter.fromJson(reader)
                    10 -> companyName = nullableStringAdapter.fromJson(reader)
                    11 -> taxNumber = nullableStringAdapter.fromJson(reader)
                    12 -> taxAuthority = nullableStringAdapter.fromJson(reader)
                    -1 -> {
                        // Unknown name, skip it.
                        reader.skipName()
                        reader.skipValue()
                    }
                }
            } catch (exception: Exception) {
                Logger.warn(
                    this::class.java.name, "A field is null and is being skipped.",
                    exception
                )
                reader.skipValue()
            }
        }
        reader.endObject()
        return try {
            val result = JSONAddress(
                id = id!!,
                isBilling = isBilling!!,
                name = name!!,
                surname = surname!!,
                phone = phone!!,
                province = province!!,
                district = district!!,
                address = address!!,
                title = title!!,
                isIndividual = isIndividual!!,
                companyName = companyName,
                taxNumber = taxNumber,
                taxAuthority = taxAuthority
            )
            return result
        } catch (exception: Exception) {
            Logger.warn(
                this::class.java.name,
                "A field is null, this object will be null and won't be visible in app.",
                exception
            )
            null
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: JSONAddress?) {
        if (value == null) {
            throw NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.beginObject()
        writer.name("ID")
        intAdapter.toJson(writer, value.id)
        writer.name("BILLING")
        booleanAdapter.toJson(writer, value.isBilling)
        writer.name("NAME")
        stringAdapter.toJson(writer, value.name)
        writer.name("SURNAME")
        stringAdapter.toJson(writer, value.surname)
        writer.name("PHONE")
        stringAdapter.toJson(writer, value.phone)
        writer.name("CITY")
        listOfJSONCityAdapter.toJson(writer, value.province)
        writer.name("DISTRICT")
        listOfJSONDistrictAdapter.toJson(writer, value.district)
        writer.name("ADDRESS")
        stringAdapter.toJson(writer, value.address)
        writer.name("TITLE")
        stringAdapter.toJson(writer, value.title)
        writer.name("INDIVIDUAL")
        booleanAdapter.toJson(writer, value.isIndividual)
        writer.name("COMPANY_NAME")
        nullableStringAdapter.toJson(writer, value.companyName)
        writer.name("TAX_NUMBER")
        nullableStringAdapter.toJson(writer, value.taxNumber)
        writer.name("TAX_AUTHORITY")
        nullableStringAdapter.toJson(writer, value.taxAuthority)
        writer.endObject()
    }
}