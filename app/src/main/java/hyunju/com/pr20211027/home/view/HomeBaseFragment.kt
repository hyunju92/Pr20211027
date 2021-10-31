package hyunju.com.pr20211027.home.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import hyunju.com.pr20211027.home.vm.SharedViewModel
import hyunju.com.pr20211027.main.network.ProductItem

@AndroidEntryPoint
open class HomeBaseFragment : Fragment(){

    protected val sharedViewModel: SharedViewModel by activityViewModels()

    // currentList
    protected fun addSharedCurrentList(data: ProductItem){
        sharedViewModel.addCurrentList(data)
    }

    protected fun getSharedCurrentList() = sharedViewModel.currentItemList

    // drawer
    protected fun setHomeDrawerState(isLock: Boolean) {
        sharedViewModel.setDrawerLockState(isLock)
    }

    protected fun openHomeDrawer(){
        sharedViewModel.openDrawer()
    }

    // navigation move
    protected fun navigateHomeToDetail(data: ProductItem) {
        sharedViewModel.moveDetail(data)
    }

    protected fun navigateHomeToBack() {
        sharedViewModel.moveBack()
    }


}