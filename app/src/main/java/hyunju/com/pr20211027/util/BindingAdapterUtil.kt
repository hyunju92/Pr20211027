package hyunju.com.pr20211027.util

import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hyunju.com.pr20211027.home.view.CustomNavigation
import hyunju.com.pr20211027.R
import hyunju.com.pr20211027.main.network.ProductItem

interface RecyclerAdapter<T> {
    fun replaceAll(recyclerView: RecyclerView, listItem: List<T>?)
}

@BindingAdapter("replaceAll")
fun <T> RecyclerView.replaceAll(listItem: List<T>?) {
    (this.adapter as? RecyclerAdapter<T>)?.replaceAll(this, listItem)
}

@BindingAdapter("setImgUri")
fun setImgUrl(imageView: ImageView, url: String?) {
    val loadImg = if (url.isNullOrEmpty()) R.drawable.ic_baseline_error_outline_24 else url
    val errorImg = R.drawable.ic_baseline_error_outline_24
    val placeholderImg = R.drawable.ic_baseline_collections_8

        Glide.with(imageView.rootView.context)
            .load(loadImg)
            .placeholder(placeholderImg)
            .error(errorImg)
            .into(imageView)
}

@BindingAdapter("replaceCustomNavListItem")
fun replaceCustomNavListItem(view: CustomNavigation, listItem: List<ProductItem>?) {
    view.replaceListItem(listItem?.toMutableList())
}

@BindingAdapter("setCommonCardHeight")
fun setCommonCardHeight(view: ConstraintLayout, cardCount: Int?) {
    if(cardCount == null || cardCount < 2) return

    val pxWidth = view.resources.displayMetrics.widthPixels
    val layoutParams: ViewGroup.LayoutParams = view.layoutParams
    layoutParams.height = ((pxWidth / cardCount) * 1.3).toInt()
    view.layoutParams = layoutParams
}


