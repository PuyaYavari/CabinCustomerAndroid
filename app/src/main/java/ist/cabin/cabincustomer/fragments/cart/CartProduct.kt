package ist.cabin.cabincustomer.fragments.cart

class CartProduct(val id: String, override var count: Int): CabinCustomerCartContracts.Product {

    /* Fields, Getters and Setter *///TODO

    override fun getID(): String {
        return id
    }
}