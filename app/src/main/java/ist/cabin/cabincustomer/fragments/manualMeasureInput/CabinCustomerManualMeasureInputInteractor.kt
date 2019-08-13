package ist.cabin.cabincustomer.fragments.manualMeasureInput

import ist.cabin.cabincustomer.models.Measure
import ist.cabin.cabincustomer.models.UserResponseMapper

class CabinCustomerManualMeasureInputInteractor(var output: CabinCustomerManualMeasureInputContracts.InteractorOutput?) :
    CabinCustomerManualMeasureInputContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    var json = "{\"KULLANICI\":[{\"ID\":1,\"AD\":\"BÜNYAMİN\",\"SOYAD\":\"HATİPOĞLU\",\"OLCU\":[{\"ID\":1,\"AD\":\"OMUZ\",\"DEGER\":null},{\"ID\":2,\"AD\":\"GÖĞÜS\",\"DEGER\":null},{\"ID\":3,\"AD\":\"BEL\",\"DEGER\":null},{\"ID\":4,\"AD\":\"KALÇA\",\"DEGER\":null},{\"ID\":5,\"AD\":\"İÇ BACAK GENİŞLİĞİ\",\"DEGER\":null},{\"ID\":6,\"AD\":\"İÇ BACAK BOYU\",\"DEGER\":null},{\"ID\":7,\"AD\":\"BALDIR\",\"DEGER\":null},{\"ID\":8,\"AD\":\"BOY\",\"DEGER\":null},{\"ID\":9,\"AD\":\"ABCDEFGH\",\"DEGER\":null},{\"ID\":10,\"AD\":\"ABCDEFGHIJK\",\"DEGER\":null},{\"ID\":11,\"AD\":\"ABCDEFGHIJKLMN\",\"DEGER\":null},{\"ID\":12,\"AD\":\"ABCDEFGHIJKLMNOPQ\",\"DEGER\":null},{\"ID\":13,\"AD\":\"ABCDEFGHIJKLMNOPQRST\",\"DEGER\":null},{\"ID\":14,\"AD\":\"ABCDEFGHIJKLMNOPQRSTUVW\",\"DEGER\":null}]}]}"

    override fun getMeasures(): List<Measure>? {
        val userResponseMapper = UserResponseMapper()
        return userResponseMapper.getMeasuresNamesAndIds(json)
    }

    //endregion
}