package hyunju.com.pr20211027.main.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class ResMainData(
    val result: String?,
    val data: List<Item>?
)

data class Item(
    val viewtype: String?,
    val data: ProductItem?
)

@Parcelize
data class ProductItem(
    val image: String?,
    val price: String?,
    val name: String?,
    val detail: String?
) : Parcelable