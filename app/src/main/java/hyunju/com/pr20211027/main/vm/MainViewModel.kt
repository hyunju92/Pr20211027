package hyunju.com.pr20211027.main.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hyunju.com.pr20211027.main.model.MainRepository
import hyunju.com.pr20211027.main.network.ResMainData
import io.reactivex.Observable
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository): ViewModel(){

    fun getMainData() : Observable<ResMainData>{
        return mainRepository.getMainData()
    }
}