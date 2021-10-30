package hyunju.com.pr20211027.detail.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hyunju.com.pr20211027.main.network.ProductItem
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {

    private var data : ProductItem? = null

    fun setData(data: ProductItem) { this.data = data }

    fun getData() = data

}