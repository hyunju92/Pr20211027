package hyunju.com.pr20211027.main.vm

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hyunju.com.pr20211027.R
import hyunju.com.pr20211027.main.model.MainRepository
import hyunju.com.pr20211027.main.network.ProductItem
import hyunju.com.pr20211027.main.view.data.MainCurrentItem
import hyunju.com.pr20211027.main.view.data.MainUiItem
import hyunju.com.pr20211027.main.view.data.toMainUiItemList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    private var disposable = CompositeDisposable()
    val mainItemList = ObservableField<List<MainUiItem>>()

    val uiEvent = PublishSubject.create<MainUiEvent>()

    // mainItemList
    fun getMainUiData(currentItemList: ObservableField<List<ProductItem>>){
        disposable.add(mainRepository.getMainData()
            .subscribeOn(Schedulers.computation())
            .map {
                it.toMainUiItemList().toMutableList().apply {           // api response 값 파싱
                    add(1, MainCurrentItem(currentItemList))      // 최근본상품 리스트 추가
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                mainItemList.set(it)
            }
        )
    }

    // click event
    fun clickProductView(data: ProductItem){
        uiEvent.onNext(MainUiEvent.MoveDetail(data))
    }

    fun clickMenu(menuItemId: Int) {
        when(menuItemId) {
            R.id.mainMenuCurrent -> uiEvent.onNext(MainUiEvent.OpenHomeDrawer)
        }
    }

    // onCleared
    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}

sealed class MainUiEvent {
    data class MoveDetail(val data: ProductItem) : MainUiEvent()
    object OpenHomeDrawer : MainUiEvent()
}