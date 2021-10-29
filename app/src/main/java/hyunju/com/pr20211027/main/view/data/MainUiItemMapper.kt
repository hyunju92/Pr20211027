package hyunju.com.pr20211027.main.view.data

import hyunju.com.pr20211027.main.network.ResMainData

fun ResMainData.toMainUiItemList() : List<MainUiItem> {
    val list = mutableListOf<MainUiItem>()

    data?.forEach {
        val type = safeUnboxing(it.viewtype)
        val prodItem = it.data?:return@forEach

        when(type)  {
            "image" -> list.add(MainImageItem(prodItem))
            "productItem" -> list.add(MainProdSingleItem(prodItem))
        }
    }

    return list
}


private fun safeUnboxing(str: String?): String = str ?: ""