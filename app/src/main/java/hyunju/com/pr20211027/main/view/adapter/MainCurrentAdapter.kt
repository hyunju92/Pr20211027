package hyunju.com.pr20211027.main.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import hyunju.com.pr20211027.R
import hyunju.com.pr20211027.databinding.ItemMainCurrentBinding
import hyunju.com.pr20211027.home.vm.HomeViewModel
import hyunju.com.pr20211027.main.network.ProductItem
import hyunju.com.pr20211027.util.RecyclerAdapter

class MainCurrentAdapter(private val homeViewModel: HomeViewModel) : RecyclerView.Adapter<MainCurrentAdapter.MainCurrentViewHolder>(),
    RecyclerAdapter<ProductItem> {

    private var productList: ArrayList<ProductItem>? = null

    override fun replaceAll(recyclerView: RecyclerView, listItem: List<ProductItem>?) {
        listItem?.let { newList ->
            if (productList == null) {
                productList?.clear()
                productList = newList as ArrayList<ProductItem>

                notifyDataSetChanged()
            } else {
                val diffResult = DiffUtil.calculateDiff(CurrentDiffUtil(productList!!, newList))
                productList!!.clear()
                productList!!.addAll(newList)

                diffResult.dispatchUpdatesTo(this)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainCurrentViewHolder {
        return DataBindingUtil.inflate<ItemMainCurrentBinding>(LayoutInflater.from(parent.context), R.layout.item_main_current, parent, false).let {
            it.homeVm = homeViewModel
            MainCurrentViewHolder(it)
        }
    }

    override fun onBindViewHolder(holder: MainCurrentViewHolder, position: Int) {
        productList?.let { holder.bind(it[position]) }
    }

    override fun getItemCount(): Int {
        return productList?.size ?: 0
    }

    class MainCurrentViewHolder(private val binding: ItemMainCurrentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ProductItem) {
            binding.data = data
        }
    }

    class CurrentDiffUtil(private val oldList: List<ProductItem>, private val newList: List<ProductItem>) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].name == newList[newItemPosition].name
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    } 
}