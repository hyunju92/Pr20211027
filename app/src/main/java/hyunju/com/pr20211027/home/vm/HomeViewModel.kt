package hyunju.com.pr20211027.home.vm

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hyunju.com.pr20211027.main.network.ProductItem
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel(){
    val currentItemList = ObservableField<List<ProductItem>>()

    fun addCurrentList(data: ProductItem) {
        val newList = currentItemList.get()?.apply {
            this as LinkedList
            if (contains(data)) {
                remove(data)
            }
            addFirst(data)

        } ?: LinkedList<ProductItem>().apply { add(data) }

        refreshCurrentList(newList)
    }

    fun removeCurrentList(data: ProductItem) {
        val newList = currentItemList.get()?.apply{
            this as LinkedList
            remove(data)
        }?:return

        refreshCurrentList(newList)
    }

    private fun refreshCurrentList(newList: List<ProductItem>) {
        currentItemList.set(newList)
        currentItemList.notifyChange()
    }

}