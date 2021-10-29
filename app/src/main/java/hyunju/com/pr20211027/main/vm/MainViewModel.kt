package hyunju.com.pr20211027.main.vm

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hyunju.com.pr20211027.main.model.MainRepository
import hyunju.com.pr20211027.main.network.ResMainData
import hyunju.com.pr20211027.main.view.data.MainUiItem
import hyunju.com.pr20211027.main.view.data.toMainUiItemList
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    private var disposable = CompositeDisposable()
    val mainItemList = ObservableField<List<MainUiItem>>()


    fun getMainData(){
        disposable.add(mainRepository.getMainData()
            .subscribeOn(Schedulers.computation())
            .map { it.toMainUiItemList() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                mainItemList.set(it)
            }

        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}