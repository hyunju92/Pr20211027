package hyunju.com.pr20211027.home.vm

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hyunju.com.pr20211027.main.network.ProductItem
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class SharedViewModel @Inject constructor(): ViewModel(){

    val currentItemList = ObservableField<List<ProductItem>>()
    val uiEvent = PublishSubject.create<HomeUiEvent>()

    // currentItemList
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


    //  drawer
    fun clickDrawerCurrentProduct(data: ProductItem){
        moveDetail(data)
        uiEvent.onNext(HomeUiEvent.CloseDrawer)
    }

    fun openDrawer() {
        uiEvent.onNext(HomeUiEvent.OpenDrawer)
    }

    fun setDrawerLockState(isLock: Boolean){
        if(isLock) {
            uiEvent.onNext(HomeUiEvent.SetDrawerLockState(true))
        } else{
            uiEvent.onNext(HomeUiEvent.SetDrawerLockState(false))
        }
    }

    // move
    fun moveDetail(data: ProductItem){
        uiEvent.onNext(HomeUiEvent.MoveDetail(data))
    }

    fun moveBack(){
        uiEvent.onNext(HomeUiEvent.MoveBack)
    }

    // home backpressed
    fun homeBackPressedAction(isDrawerOpen: Boolean) {
        if(isDrawerOpen) {
            uiEvent.onNext(HomeUiEvent.CloseDrawer)
        } else {
            uiEvent.onNext(HomeUiEvent.BackPressed)
        }
    }

}


sealed class HomeUiEvent {
    data class MoveDetail(val data: ProductItem) : HomeUiEvent()
    data class SetDrawerLockState(val isLock: Boolean) : HomeUiEvent()
    object MoveBack : HomeUiEvent()
    object OpenDrawer : HomeUiEvent()
    object CloseDrawer : HomeUiEvent()
    object BackPressed : HomeUiEvent()
}
