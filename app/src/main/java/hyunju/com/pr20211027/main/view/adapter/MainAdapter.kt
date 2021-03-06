package hyunju.com.pr20211027.main.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import hyunju.com.pr20211027.main.view.data.MainUiItem
import hyunju.com.pr20211027.main.view.data.MainUiItemType
import hyunju.com.pr20211027.main.vm.MainViewModel
import hyunju.com.pr20211027.util.RecyclerAdapter

class MainAdapter(
    private val mainViewModel: MainViewModel
) : RecyclerView.Adapter<MainViewHolder>(), RecyclerAdapter<MainUiItem> {

    private var mainList: ArrayList<MainUiItem>? = null
    private val rvViewPool = RecyclerView.RecycledViewPool()

    override fun replaceAll(recyclerView: RecyclerView, listItem: List<MainUiItem>?) {
        listItem?.let { newList ->
            if (mainList == null) {
                mainList?.clear()
                mainList = newList as ArrayList<MainUiItem>

                notifyDataSetChanged()
            } else {
                val diffResult = DiffUtil.calculateDiff(MainDiffUtil(mainList!!, newList))
                mainList!!.clear()
                mainList!!.addAll(newList)

                diffResult.dispatchUpdatesTo(this)
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainUiItemType.getTypeByCode(viewType).let { type ->
            MainViewHolderFactory.getViewHolder(type, parent, mainViewModel, rvViewPool)
        }
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        mainList?.let { holder.bind(it[position]) }
    }

    override fun getItemCount(): Int = mainList?.size ?: 0

    override fun getItemViewType(position: Int): Int {
        return mainList?.let { it[position].type.code } ?: -1
    }

}