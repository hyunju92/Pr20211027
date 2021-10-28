package hyunju.com.pr20211027.main.model

import hyunju.com.pr20211027.main.network.MainNetworkApi
import hyunju.com.pr20211027.main.network.ResMainData
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val mainNetworkApi: MainNetworkApi
) {

    fun getMainData() : Observable<ResMainData> {
        return mainNetworkApi.getMainData()
            .subscribeOn(Schedulers.io())
    }


}