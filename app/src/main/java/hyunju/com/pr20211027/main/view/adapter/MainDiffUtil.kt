package hyunju.com.pr20211027.main.view.adapter

import androidx.recyclerview.widget.DiffUtil
import hyunju.com.pr20211027.main.view.data.*

class MainDiffUtil(
    private val oldList: List<MainUiItem>,
    private val newList: List<MainUiItem>
) : DiffUtil.Callback() {


    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].type == newList[newItemPosition].type
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].type == newList[newItemPosition].type && when (newList[newItemPosition].type) {

            MainUiItemType.Image ->
                (oldList[oldItemPosition] as MainImageItem) == (newList[newItemPosition] as MainImageItem)
            MainUiItemType.ProdSingle ->
                (oldList[oldItemPosition] as MainProdSingleItem) == (newList[newItemPosition] as MainProdSingleItem)
            MainUiItemType.ProdDouble ->
                (oldList[oldItemPosition] as MainProdDoubleItem) == (newList[newItemPosition] as MainProdDoubleItem)
            MainUiItemType.Current ->
                (oldList[oldItemPosition] as MainCurrentItem) == (newList[newItemPosition] as MainCurrentItem)
        }
    }
}