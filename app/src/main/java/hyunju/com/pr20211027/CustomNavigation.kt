package hyunju.com.pr20211027

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.google.android.material.navigation.NavigationView
import hyunju.com.pr20211027.databinding.LayouCustomNavBinding

class CustomNavigation @kotlin.jvm.JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : NavigationView(context, attrs){

    private lateinit var binding: LayouCustomNavBinding

    init {
        initView()
        getAttrs(context, attrs)
    }


    private fun initView() {
        binding = LayouCustomNavBinding.inflate(LayoutInflater.from(context))
        addView(binding.root)
    }


    private fun getAttrs(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomNavigation)

        // title text
//        titleView = finnq_title_tv.apply {
//            text = typedArray.getString(R.styleable.FinnqTitleView_title)
//        }
//
//        // height
//        threshold = typedArray.getDimension(R.styleable.FinnqTitleView_threshold, 0f).toInt()
//        finnq_title_cl.layoutParams.let {
//            it.height = threshold!!
//        }
//
//        // scroll, recycler view
//        scrollViewId = typedArray.getResourceId(R.styleable.FinnqTitleView_scrollViewId, NO_ID)
//        recyclerViewId = typedArray.getResourceId(R.styleable.FinnqTitleView_recyclerViewId, NO_ID)

        typedArray.recycle()

    }


}

