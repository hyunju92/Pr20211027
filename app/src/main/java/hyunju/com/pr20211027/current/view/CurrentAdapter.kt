package hyunju.com.pr20211027.current.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import hyunju.com.pr20211027.R
import hyunju.com.pr20211027.databinding.ItemCustomNavRvBinding
import hyunju.com.pr20211027.main.model.ProductItemData
import hyunju.com.pr20211027.util.RecyclerAdapter

class CurrentAdapter() : RecyclerView.Adapter<CurrentAdapter.CurrentViewHolder>(),
    RecyclerAdapter<ProductItemData> {
    private var productList: ArrayList<ProductItemData>? = null

    override fun replaceAll(recyclerView: RecyclerView, listItem: List<ProductItemData>?) {
        listItem?.let { newList ->
            if (productList == newList) {
                productList?.clear()
                productList = listItem as ArrayList<ProductItemData>

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
        return DataBindingUtil.inflate<ItemCustomNavRvBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_custom_nav_rv,
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

    class CurrentViewHolder(private val binding: ItemCustomNavRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ProductItemData) {
            binding.data = data
        }
    }

    class CurrentDiffUtil(
        private val oldList: List<ProductItemData>,
        private val newList: List<ProductItemData>
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