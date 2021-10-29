package hyunju.com.pr20211027.main.view.data

import java.lang.RuntimeException


open class MainUiItem(open val type: MainUiItemType)

sealed class MainUiItemType(val code: Int) {
    object Image: MainUiItemType(1)
    object ProdSingle: MainUiItemType(2)
    object ProdDouble: MainUiItemType(3)
    object Current: MainUiItemType(4)

    companion object {
        fun getTypeByCode(code: Int): MainUiItemType = when(code) {
            Image.code -> Image
            ProdSingle.code -> ProdSingle
            ProdDouble.code -> ProdDouble
            Current.code -> Current
            else -> throw RuntimeException("MainUiItem getTypeByCode error")
        }
    }
}