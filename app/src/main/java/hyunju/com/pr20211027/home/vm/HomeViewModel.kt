package hyunju.com.pr20211027.home.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hyunju.com.pr20211027.main.network.ProductItem
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel(){
    val currentItemList = LinkedList<ProductItem>()

    fun addCurrentList(data: ProductItem) {
        currentItemList.let {
            if(it.contains(data)) {
                it.remove(data)
            }
            it.addFirst(data)
            Log.d("testCurrentList", "addCurrentList size ${it.size} ")
        }

    }

    fun removeCurrentList(data: ProductItem) {
        currentItemList.remove(data)
    }

}