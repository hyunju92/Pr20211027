package hyunju.com.pr20211027.main.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import hyunju.com.pr20211027.R
import hyunju.com.pr20211027.databinding.*
import hyunju.com.pr20211027.home.vm.HomeViewModel
import hyunju.com.pr20211027.main.view.data.*
import hyunju.com.pr20211027.main.vm.MainViewModel

abstract class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: MainUiItem)
}

class ImageViewHolder(private val binding: SubviewMainImageBinding): MainViewHolder(binding.root) {
    override fun bind(data: MainUiItem) {
        if(data is MainImageItem) {
            binding.data = data
        }
    }
}

class ProdSingleViewHolder(private val binding: SubviewMainProdSingleBinding): MainViewHolder(binding.root) {
    override fun bind(data: MainUiItem) {
        if(data is MainProdSingleItem) {
            binding.data = data
        }
    }
}

class ProdDoubleViewHolder(private val binding: SubviewMainProdDoubleBinding): MainViewHolder(binding.root) {
    override fun bind(data: MainUiItem) {
        if(data is MainProdDoubleItem) {
            binding.data = data
        }
    }
}

class CurrentViewHolder(private val binding: SubviewMainCurrentBinding, private val sharedViewModel: HomeViewModel,private val rvViewPool: RecyclerView.RecycledViewPool): MainViewHolder(binding.root) {

    init {
        binding.subviewMainCurrentRv.run { setRecycledViewPool(rvViewPool) }
    }

    override fun bind(data: MainUiItem) {
        if(data is MainCurrentItem) {
            binding.data = data
            binding.subviewMainCurrentRv.adapter = MainCurrentAdapter(sharedViewModel)
        }
    }

    fun onCurrentDataChanged(data: MainCurrentItem) {
        binding.data = data
    }
}

object MainViewHolderFactory {
    fun getViewHolder(type: MainUiItemType, parent: ViewGroup, mainViewModel: MainViewModel, sharedViewModel: HomeViewModel, rvViewPool: RecyclerView.RecycledViewPool) : MainViewHolder {
        val layoutId = getLayoutId(type)

        return when (type) {
            MainUiItemType.Image -> getViewDataBinding<SubviewMainImageBinding>(parent, layoutId)
                .let {
                    it.mainViewModel = mainViewModel
                    it.sharedViewModel = sharedViewModel
                    ImageViewHolder(it)
                }

            MainUiItemType.ProdSingle -> getViewDataBinding<SubviewMainProdSingleBinding>(parent, layoutId)
                .let {
                    it.mainViewModel = mainViewModel
                    it.sharedViewModel = sharedViewModel
                    ProdSingleViewHolder(it)
                }

            MainUiItemType.ProdDouble-> getViewDataBinding<SubviewMainProdDoubleBinding>(parent, layoutId)
                .let {
                    it.mainViewModel = mainViewModel
                    it.sharedViewModel = sharedViewModel
                    ProdDoubleViewHolder(it)
                }

            MainUiItemType.Current-> getViewDataBinding<SubviewMainCurrentBinding>(parent, layoutId)
                .let {
                    it.mainViewModel = mainViewModel
                    it.sharedViewModel = sharedViewModel
                    CurrentViewHolder(it, sharedViewModel, rvViewPool)
                }

        }

    }

    private fun getLayoutId(type: MainUiItemType): Int = when(type) {
        MainUiItemType.Image -> R.layout.subview_main_image
        MainUiItemType.ProdSingle -> R.layout.subview_main_prod_single
        MainUiItemType.ProdDouble -> R.layout.subview_main_prod_double
        MainUiItemType.Current -> R.layout.subview_main_current
    }

    private fun <T : ViewDataBinding> getViewDataBinding(parent: ViewGroup, layoutId: Int): T {
        return DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId, parent, false)
    }
}