package hyunju.com.pr20211027.home.vm

import android.system.Os.remove
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hyunju.com.pr20211027.main.network.ProductItem
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel(){
    val currentItemList = ObservableField<List<ProductItem>>()

    fun addCurrentList(data: ProductItem) {
        val newList = currentItemList.get()?.toMutableList()?.apply {
            if (contains(data)) { remove(data) }
            add(0, data)

        } ?: ArrayList<ProductItem>().apply { add(data) }

        refreshCurrentList(newList)
    }

    fun removeCurrentList(data: ProductItem) {
        val newList = currentItemList.get()?.toMutableList()?.apply{
            remove(data)
        }?:return

        refreshCurrentList(newList)
    }

    private fun refreshCurrentList(newList: List<ProductItem>) {
        currentItemList.set(newList)
        currentItemList.notifyChange()
    }

}