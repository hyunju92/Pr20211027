package hyunju.com.pr20211027.home.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import hyunju.com.pr20211027.home.vm.HomeViewModel
import hyunju.com.pr20211027.main.network.ProductItem

@AndroidEntryPoint
open class HomeBaseFragment : Fragment(){

    protected val homeViewModel: HomeViewModel by activityViewModels()

    // currentList
    protected fun addSharedCurrentList(data: ProductItem){
        homeViewModel.addCurrentList(data)
    }

    protected fun getSharedCurrentList() = homeViewModel.currentItemList

    // drawer
    protected fun setHomeDrawerState(isLock: Boolean) {
        homeViewModel.setDrawerLockState(isLock)
    }

    protected fun openHomeDrawer(){
        homeViewModel.openDrawer()
    }

    // navigation move
    protected fun navigateHomeToDetail(data: ProductItem) {
        homeViewModel.moveDetail(data)
    }

    protected fun navigateHomeToBack() {
        homeViewModel.moveBack()
    }


}