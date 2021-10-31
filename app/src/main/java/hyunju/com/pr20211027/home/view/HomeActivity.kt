package hyunju.com.pr20211027.home.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import dagger.hilt.android.AndroidEntryPoint
import hyunju.com.pr20211027.R
import hyunju.com.pr20211027.databinding.ActivityHomeBinding
import hyunju.com.pr20211027.home.vm.HomeUiEvent
import hyunju.com.pr20211027.home.vm.HomeViewModel
import hyunju.com.pr20211027.main.network.ProductItem
import io.reactivex.rxjava3.disposables.Disposable

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private var eventDisposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityHomeBinding>(this, R.layout.activity_home).apply {
            homeVm = homeViewModel
        }

        initView()
        observeData()
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment_container).navigateUp()
    }

    private fun initView() {
        setSupportActionBar(binding.homeContents.toolbar)

        val navController = getNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
    }

    private fun observeData() {
        eventDisposable = homeViewModel.uiEvent.subscribe {
            handleUiEvent(it)
        }
    }

    private fun handleUiEvent(uiEvent: HomeUiEvent) = when(uiEvent) {
        is HomeUiEvent.MoveDetail -> moveToDetailFrag(uiEvent.data)
        HomeUiEvent.OpenDrawer -> openDrawer()
        HomeUiEvent.CloseDrawer -> closeDrawer()
        HomeUiEvent.LockDrawer -> lockedDrawer()
        HomeUiEvent.UnlockDrawer -> unLockedDrawer()
    }

    private fun moveToDetailFrag(data: ProductItem) {
        getNavController().run {
            navigateUp()
            val args = Bundle().apply { putParcelable("data", data) }
            navigate(R.id.detailFragment, args)
        }
    }

    private fun openDrawer() {
        binding.homeDrawer.openDrawer(Gravity.RIGHT)
    }

    private fun closeDrawer() {
        binding.homeDrawer.closeDrawer(Gravity.RIGHT)
    }

    private fun lockedDrawer() {
        binding.homeDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    private fun unLockedDrawer(){
        binding.homeDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    private fun getNavController() = findNavController(R.id.nav_host_fragment_container)

    private fun isDrawerOpen() = binding.homeDrawer.isDrawerOpen(Gravity.RIGHT)

    override fun onBackPressed() {
        if(isDrawerOpen()) {
            closeDrawer()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        eventDisposable?.dispose()
    }
}