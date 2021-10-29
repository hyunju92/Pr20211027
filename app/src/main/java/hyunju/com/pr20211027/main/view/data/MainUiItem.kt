package hyunju.com.pr20211027.main.view.data

import androidx.databinding.ObservableField
import hyunju.com.pr20211027.main.network.ProductItem
import java.lang.RuntimeException


open class MainUiItem(open val type: MainUiItemType)

data class MainImageItem(
    val prod: ProductItem
) : MainUiItem(MainUiItemType.Image)

data class MainProdSingleItem(
    val prod: ProductItem
) : MainUiItem(MainUiItemType.ProdSingle)

data class MainProdDoubleItem(
    val prod1: ProductItem,
    val prod2: ProductItem
) : MainUiItem(MainUiItemType.ProdDouble)

data class MainCurrentItem(
    val prod: ObservableField<List<ProductItem>>
) : MainUiItem(MainUiItemType.Current)

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