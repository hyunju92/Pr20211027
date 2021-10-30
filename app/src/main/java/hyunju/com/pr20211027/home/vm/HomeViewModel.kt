package hyunju.com.pr20211027.home.vm

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hyunju.com.pr20211027.main.network.ProductItem
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel(){
    val currentItemList = ObservableField<LinkedList<ProductItem>>()

    fun addCurrentList(data: ProductItem) {
        val newList = currentItemList.get()?.apply {
            if (contains(data)) {
                remove(data)
            }
            addFirst(data)

        } ?: LinkedList<ProductItem>().apply { add(data) }

        currentItemList.set(newList)
    }

    fun removeCurrentList(data: ProductItem) {
        val newList = currentItemList.get()?.apply{ remove(data) }
        currentItemList.set(newList)

    }

}