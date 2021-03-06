package hyunju.com.pr20211027.detail.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hyunju.com.pr20211027.main.network.ProductItem
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {

    val uiEvent = PublishSubject.create<DetailUiEvent>()
    private lateinit var data : ProductItem

    // detail data
    fun setData(data: ProductItem) { this.data = data }

    fun getData() = data

    // event
    fun onBackPressed() {
        uiEvent.onNext(DetailUiEvent.AddCurrentList(data))
        uiEvent.onNext(DetailUiEvent.BackToMain)
    }

    fun clickMenu(menuItemId: Int) {
        when(menuItemId) {
            android.R.id.home -> onBackPressed()
        }
    }

}

sealed class DetailUiEvent {
    object BackToMain : DetailUiEvent()
    data class AddCurrentList(val data: ProductItem) : DetailUiEvent()
}