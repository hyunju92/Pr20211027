package hyunju.com.pr20211027.util

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hyunju.com.pr20211027.home.view.CustomNavigation
import hyunju.com.pr20211027.R
import hyunju.com.pr20211027.home.vm.SharedViewModel
import hyunju.com.pr20211027.main.network.ProductItem

// 레이아웃 공통 사용
@BindingAdapter("setImgUri")
fun setImgUrl(imageView: ImageView, url: String?) {
    val loadImg = if (url.isNullOrEmpty()) R.drawable.ic_baseline_error_outline_24 else url
    val errorImg = R.drawable.ic_baseline_error_outline_24
    val placeholderImg = R.drawable.ic_baseline_collections_8

    Glide.with(imageView.rootView.context)
        .load(loadImg)
//        .placeholder(placeholderImg)
        .error(errorImg)
        .into(imageView)
}

@BindingAdapter("setViewVisibility")
fun setViewVisibility(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}


// RecyclerView
interface RecyclerAdapter<T> {
    fun replaceAll(recyclerView: RecyclerView, listItem: List<T>?)
}

@BindingAdapter("replaceAll")
fun <T> RecyclerView.replaceAll(listItem: List<T>?) {
    (this.adapter as? RecyclerAdapter<T>)?.replaceAll(this, listItem)
}


// CustomNavigation
@BindingAdapter("replaceCustomNavListItem")
fun replaceCustomNavListItem(view: CustomNavigation, listItem: List<ProductItem>?) {
    view.replaceListItem(listItem)
}

@BindingAdapter("setCustomNavViewModel")
fun setCustomNavViewModel(view: CustomNavigation, sharedViewModel: SharedViewModel) {
    view.setViewModel(sharedViewModel)
}


// common card item
@BindingAdapter("setCommonCardHeightWidth")
fun setCommonCardHeightWidth(view: ConstraintLayout, cardCount: Int?) {
    if (cardCount == null) return

    val disWidthPx = view.resources.displayMetrics.widthPixels
    val layoutParams: ViewGroup.LayoutParams = view.layoutParams

    val width = if (cardCount > 1) {
        disWidthPx / cardCount * 0.95
    } else {
        disWidthPx * 0.95
    }

    val height = if(cardCount > 1) {
        width * 1.5
    } else {
        width * 0.65
    }


    layoutParams.width = width.toInt()
    layoutParams.height = height.toInt()
    view.layoutParams = layoutParams
}

// main image type view
@BindingAdapter("setMainImageHeigth")
fun setMainImageHeigth(view: View, boolean: Boolean){
    val disWidthPx = view.resources.displayMetrics.widthPixels
    val layoutParams: ViewGroup.LayoutParams = view.layoutParams

    layoutParams.height = (disWidthPx * 0.65 * 0.7).toInt()
    view.layoutParams = layoutParams
}


