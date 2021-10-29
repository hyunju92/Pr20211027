package hyunju.com.pr20211027.main.view.data

import hyunju.com.pr20211027.main.network.ProductItem
import hyunju.com.pr20211027.main.network.ResMainData

fun ResMainData.toMainUiItemList(): List<MainUiItem> {
    val list = mutableListOf<MainUiItem>()
    var countProductItem = 0

    data?.forEach {
        val type = safeUnboxing(it.viewtype)
        val prodItem = it.data ?: return@forEach

        when (type) {
            "image" -> list.add(MainImageItem(prodItem))
            "productItem" -> {
                getItemFromProductItem(prodItem, ++countProductItem)?.let{ mainUiItem ->
                    list.add(mainUiItem)
                }
            }

        }
    }

    return list
}

// viewtype이 productItem일 경우,
// 3번째까지는 싱글로 (1줄에1개)
// 4개부터는 더블로 표시하도록 함 (1줄에2개)
var prod1: ProductItem? = null
private fun getItemFromProductItem(prodItem: ProductItem, countProductItem: Int): MainUiItem? {
    return if (countProductItem < 4) {
        MainProdSingleItem(prodItem)

    } else {
        if (prod1 == null) {
            prod1 = prodItem
            null
        } else {
            val temp = prod1
            prod1 = null
            MainProdDoubleItem(temp!!, prodItem)
        }

    }

}


private fun safeUnboxing(str: String?): String = str ?: ""