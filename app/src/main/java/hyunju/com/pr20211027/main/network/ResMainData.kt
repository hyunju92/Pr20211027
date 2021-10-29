package hyunju.com.pr20211027.main.network

data class ResMainData(
    val result: String?,
    val data: List<Item>?
)

data class Item(
    val viewtype: String?,
    val data: ProductItem?
)

data class ProductItem(
    val image: String?,
    val price: String?,
    val name: String?,
    val detail: String?
)