package com.cabinInformationTechnologies.cabin.fragments.cart

import android.content.Context
import com.squareup.moshi.Moshi

class CabinCustomerCartInteractor(val context: Context?,
                                  var output: CabinCustomerCartContracts.InteractorOutput?) :
    CabinCustomerCartContracts.Interactor {

    override fun unregister() {
        output = null
    }

    //region Interactor

    private fun removeItemFromCart(sellerId: Int?, productId: Int?, colorId: Int?) {
        try {
            val carts: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELCarts =
                com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELCarts()
            var data: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAPISeller? = null
            if (sellerId != null && productId != null && colorId != null) {
                data = com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAPISeller(
                    listOf(
                        com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTSeller(
                            sellerId,
                            listOf(
                                com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTProduct(
                                    productId,
                                    null,
                                    listOf(
                                        com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTColor(
                                            colorId,
                                            null
                                        )
                                    )
                                )
                            )
                        )
                    )
                )
            } else if (sellerId != null && productId != null) {
                data = com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAPISeller(
                    listOf(
                        com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTSeller(
                            sellerId,
                            listOf(
                                com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTProduct(
                                    productId,
                                    null,
                                    null
                                )
                            )
                        )
                    )
                )
            } else if (sellerId != null) {
                data = com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAPISeller(
                    listOf(
                        com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTSeller(
                            sellerId,
                            null
                        )
                    )
                )
            }
            if (context != null) {
                com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager.requestFactory(
                    context,
                    com.cabinInformationTechnologies.cabinCustomerBase.Constants.CART_REMOVE_ALL_URL,
                    null,
                    null,
                    data,
                    carts,
                    com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.APICartAdapter(
                        context,
                        Moshi.Builder().build(),
                        null
                    ),
                    object :
                        com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.ResponseCallbacks {
                        override fun onSuccess(value: Any?) {
                            com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                                context,
                                this::class.java.name,
                                "Product removed from cart.",
                                null
                            )
                        }

                        override fun onIssue(value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue) {
                            com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(
                                context,
                                this::class.java.name,
                                "Product not removed from cart.\n" +
                                        "ISSUE: ${value.message}",
                                null
                            )
                        }

                        override fun onError(value: String, url: String?) {
                            com.cabinInformationTechnologies.cabinCustomerBase.Logger.warn(
                                context,
                                this::class.java.name,
                                "Product not removed from cart.\n" +
                                        "ERROR: $value",
                                null
                            )
                        }

                        override fun onFailure(throwable: Throwable) {
                            com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                                context,
                                this::class.java.name,
                                "FAILURE: Product not removed from cart.",
                                throwable
                            )
                        }

                        override fun onServerDown() {
                            com.cabinInformationTechnologies.cabinCustomerBase.Logger.failure(
                                context,
                                this::class.java.name,
                                "SERVER DOWN!!",
                                null
                            )
                        }

                        override fun onException(exception: Exception) {
                            com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                                context,
                                this::class.java.name,
                                "EXCEPTION: Product not removed from cart.",
                                exception
                            )
                        }

                    }
                )
                output?.setCart(carts.getCarts()[0])
            }
        } catch (exception: Exception) {
            if (context != null)
                com.cabinInformationTechnologies.cabinCustomerBase.Logger.failure(
                    context,
                    this::class.java.name,
                    "Error while sending request to remove problematic product from cart.",
                    exception
                )
        }
    }

    override fun getCart(context: Context) {
        var sellerId: Int? = null
        var productId: Int? = null
        var colorId: Int? = null
        val carts: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELCarts =
            com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELCarts()
        com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager.requestFactory(
            context,
            com.cabinInformationTechnologies.cabinCustomerBase.Constants.CART_LIST_ALL_URL,
            null,
            null,
            null,
            carts,
            //null,
            com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.APICartAdapter(
                context,
                Moshi.Builder().build(),
                object : CabinCustomerCartContracts.CartCallback {
                    override fun setSellerId(id: Int?) {
                        sellerId = id
                    }

                    override fun setProductId(id: Int?) {
                        productId = id
                    }

                    override fun setColorId(id: Int?) {
                        colorId = id
                    }

                    override fun feedback(message: String) {
                        output?.feedback(message)
                    }

                    override fun removeItems() {
                        removeItemFromCart(sellerId, productId, colorId)
                    }
                }
            ),
            object : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                        context,
                        this::class.java.name,
                        "Success ${value.toString()}",
                        null)
                    val cart = carts.getCarts()[0]
                    output?.setCart(cart)
                }

                override fun onIssue(value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                        context,
                        this::class.java.name,
                        "Issue ${value.message}",
                        null)
                }

                override fun onError(value: String, url: String?) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                        context,
                        this::class.java.name,
                        "Error $value",
                        null)
                }

                override fun onFailure(throwable: Throwable) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                        context,
                        this::class.java.name,
                        "Failure ${throwable.message}",
                        null)
                }

                override fun onServerDown() {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                        context,
                        this::class.java.name,
                        "Server Down",
                        null)
                }

                override fun onException(exception: Exception) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                        context,
                        this::class.java.name,
                        "Exception",
                        exception)
                }

            }
        )
    }

    override fun updateProduct(context: Context, product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct) {
        val carts: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELCarts =
            com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELCarts()
        var data: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAPIProduct? = null
        val amount = product.getAmount()
        if (amount != null) {
            data = com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTAPIProduct(
                listOf(
                    com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTProduct(
                        product.getId(),
                        amount,
                        listOf(
                            com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTColor(
                                product.getColors()[0].id,
                                listOf(
                                    com.cabinInformationTechnologies.cabinCustomerBase.models.backend.REQUESTSize(
                                        product.getColors()[0].sizes[0].id
                                    )
                                )
                            )
                        )
                    )
                )
            )
        }
        com.cabinInformationTechnologies.cabinCustomerBase.NetworkManager.requestFactory<com.cabinInformationTechnologies.cabinCustomerBase.models.backend.APICart>(
            context,
            com.cabinInformationTechnologies.cabinCustomerBase.Constants.CART_UPDATE_URL,
            null,
            null,
            data,
            carts,
            //null,
            com.cabinInformationTechnologies.cabinCustomerBase.models.adapters.APICartAdapter(
                context,
                Moshi.Builder().build(),
                null
            ),
            object : com.cabinInformationTechnologies.cabinCustomerBase.BaseContracts.ResponseCallbacks {
                override fun onSuccess(value: Any?) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                        context,
                        this::class.java.name,
                        "Success ${value.toString()}",
                        null)
                    var cart: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELCart? = null
                    if (carts.getCarts().isNotEmpty())
                         cart = carts.getCarts()[0]
                    output?.setCart(cart)
                }

                override fun onIssue(value: com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONIssue) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                        context,
                        this::class.java.name,
                        "Issue ${value.message}",
                        null)
                }

                override fun onError(value: String, url: String?) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                        context,
                        this::class.java.name,
                        "Error $value",
                        null)
                }

                override fun onFailure(throwable: Throwable) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                        context,
                        this::class.java.name,
                        "Failure ${throwable.message}",
                        null)
                }

                override fun onServerDown() {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.info(
                        context,
                        this::class.java.name,
                        "Server Down",
                        null)
                }

                override fun onException(exception: Exception) {
                    com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                        context,
                        this::class.java.name,
                        "Exception",
                        exception)
                }
            }
        )
    }

    //endregion
}