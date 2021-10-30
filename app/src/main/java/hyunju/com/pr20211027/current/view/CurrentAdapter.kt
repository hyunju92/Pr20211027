package hyunju.com.pr20211027.current.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import hyunju.com.pr20211027.R
import hyunju.com.pr20211027.databinding.ItemCurrentBinding
import hyunju.com.pr20211027.main.network.ProductItem
import hyunju.com.pr20211027.util.RecyclerAdapter
import java.util.*

class CurrentAdapter() : RecyclerView.Adapter<CurrentAdapter.CurrentViewHolder>(),
    RecyclerAdapter<ProductItem> {
    private var productList: LinkedList<ProductItem>? = null

    override fun replaceAll(recyclerView: RecyclerView, listItem: List<ProductItem>?) {
        listItem?.let { newList ->
            if (productList == null) {
                productList?.clear()
                productList = listItem as LinkedList<ProductItem>

                notifyDataSetChanged()
            } else {
                val diffResult = DiffUtil.calculateDiff(CurrentDiffUtil(productList!!, newList))
                productList!!.clear()
                productList!!.addAll(newList)

                diffResult.dispatchUpdatesTo(this)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentViewHolder {
        return DataBindingUtil.inflate<ItemCurrentBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_current,
            parent,
            false
        ).let {
            CurrentViewHolder(it)
        }
    }

    override fun onBindViewHolder(holder: CurrentViewHolder, position: Int) {
        productList?.let { holder.bind(it[position]) }
    }

    override fun getItemCount(): Int {
        return productList?.size ?: 0
    }

    class CurrentViewHolder(private val binding: ItemCurrentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ProductItem) {
            binding.data = data
        }
    }

    class CurrentDiffUtil(
        private val oldList: List<ProductItem>,
        private val newList: List<ProductItem>
    ) : DiffUtil.Callback() {
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