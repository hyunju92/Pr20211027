package hyunju.com.pr20211027.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hyunju.com.pr20211027.home.view.CustomNavigation
import hyunju.com.pr20211027.R
import hyunju.com.pr20211027.main.model.ProductItemData

interface RecyclerAdapter<T> {
    fun replaceAll(recyclerView: RecyclerView, listItem: List<T>?)
}

@BindingAdapter("replaceAll")
fun <T> RecyclerView.replaceAll(listItem: List<T>?) {
    (this.adapter as? RecyclerAdapter<T>)?.replaceAll(this, listItem)
}

@BindingAdapter("setImgUri")
fun setImgUri(imageView: ImageView, uri: String?) {
    val loadImg = if (uri.isNullOrEmpty()) R.drawable.ic_baseline_error_outline_24 else uri
    val errorImg = R.drawable.ic_baseline_error_outline_24
    val placeholderImg = R.drawable.ic_baseline_collections_8

        Glide.with(imageView.rootView.context)
            .load(loadImg)
            .placeholder(placeholderImg)
            .error(errorImg)
            .into(imageView)
}

@BindingAdapter("replaceCustomNavListItem")
fun replaceCustomNavListItem(view: CustomNavigation, listItem: List<ProductItemData>?) {
    view.replaceListItem(listItem)
}
