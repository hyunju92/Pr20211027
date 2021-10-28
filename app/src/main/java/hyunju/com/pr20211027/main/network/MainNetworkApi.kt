package hyunju.com.pr20211027.main.network

import io.reactivex.Observable
import retrofit2.http.GET

interface MainNetworkApi {

    @GET("/ui/app/test/homework.json")
    fun getMainData(): Observable<ResMainData>
}