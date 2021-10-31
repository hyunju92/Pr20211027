package hyunju.com.pr20211027.home.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import hyunju.com.pr20211027.R
import hyunju.com.pr20211027.databinding.LayoutCustomNavBinding
import hyunju.com.pr20211027.home.vm.HomeViewModel
import hyunju.com.pr20211027.main.network.ProductItem
import hyunju.com.pr20211027.util.replaceAll

class CustomNavigation @kotlin.jvm.JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : NavigationView(context, attrs){

    private lateinit var binding: LayoutCustomNavBinding

    private lateinit var homeViewModel: HomeViewModel

    init {
        initView()
        getAttrs(context, attrs)
    }

    private fun initView() {
        binding = LayoutCustomNavBinding.inflate(LayoutInflater.from(context))
        addView(binding.root)
    }

    private fun getAttrs(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomNavigation)
        typedArray.recycle()
    }

    fun replaceListItem(listItem: List<ProductItem>?) {
        binding.customNavRv.replaceAll(listItem)
    }

    fun setViewModel(homeVm: HomeViewModel) {
        homeViewModel = homeVm
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if(parent == null) { return }

        binding.customNavRv.run{
            layoutManager = LinearLayoutManager(context)
            adapter = DrawerCurrentAdapter(homeViewModel)
        }
    }

}

