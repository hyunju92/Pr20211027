package hyunju.com.pr20211027.home.vm

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hyunju.com.pr20211027.main.network.ProductItem
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel(){

    val currentItemList = ObservableField<List<ProductItem>>()
    val uiEvent = PublishSubject.create<HomeUiEvent>()

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

    fun clickProductView(data: ProductItem){
        uiEvent.onNext(HomeUiEvent.MoveDetail(data))
        uiEvent.onNext(HomeUiEvent.CloseDrawer)
    }

    fun openDrawer() {
        uiEvent.onNext(HomeUiEvent.OpenDrawer)
    }

    private fun refreshCurrentList(newList: List<ProductItem>) {
        currentItemList.set(newList)
        currentItemList.notifyChange()
    }
}


sealed class HomeUiEvent {
    data class MoveDetail(val data: ProductItem) : HomeUiEvent()
    object OpenDrawer : HomeUiEvent()
    object CloseDrawer : HomeUiEvent()
    object LockDrawer : HomeUiEvent()
    object UnlockDrawer : HomeUiEvent()
}
