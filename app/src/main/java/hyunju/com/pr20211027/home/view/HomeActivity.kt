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
import hyunju.com.pr20211027.home.vm.SharedViewModel
import hyunju.com.pr20211027.main.network.ProductItem
import io.reactivex.rxjava3.disposables.Disposable

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val sharedViewModel: SharedViewModel by viewModels()
    private var eventDisposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityHomeBinding>(this, R.layout.activity_home).apply {
            sharedVm = sharedViewModel
        }

        initView()
        observeData()
    }

    override fun onSupportNavigateUp() = getNavController().navigateUp()

    private fun initView() {
        setSupportActionBar(binding.homeContents.toolbar)

        val navController = getNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
    }

    private fun observeData() {
        eventDisposable = sharedViewModel.uiEvent.subscribe {
            handleUiEvent(it)
        }
    }

    private fun getNavController() = findNavController(R.id.homeNavHostFrag)

    // ui Event
    private fun handleUiEvent(uiEvent: HomeUiEvent) = when (uiEvent) {
        is HomeUiEvent.MoveDetail -> moveToDetailFrag(uiEvent.data)
        is HomeUiEvent.SetDrawerLockState -> setDrawerLockState(uiEvent.isLock)
        HomeUiEvent.OpenDrawer -> openDrawer()
        HomeUiEvent.CloseDrawer -> closeDrawer()
        HomeUiEvent.BackPressed -> backPressed()
    }

    private fun moveToDetailFrag(data: ProductItem) {
        getNavController().run {
            navigateUp()
            navigate(R.id.detailFragment, Bundle().apply { putParcelable("data", data) })
        }
    }

    private fun openDrawer() {
        binding.homeDrawer.openDrawer(Gravity.RIGHT)
    }

    private fun closeDrawer() {
        binding.homeDrawer.closeDrawer(Gravity.RIGHT)
    }

    private fun setDrawerLockState(isLock : Boolean) {
        val mode = if (isLock) DrawerLayout.LOCK_MODE_LOCKED_CLOSED else DrawerLayout.LOCK_MODE_UNLOCKED
        binding.homeDrawer.setDrawerLockMode(mode)
    }

    private fun backPressed() {
       super.onBackPressed()
    }

    // set backprssed
    override fun onBackPressed() {
        val isDrawerOpen = binding.homeDrawer.isDrawerOpen(Gravity.RIGHT)
        sharedViewModel.homeBackPressedAction(isDrawerOpen)
    }

    // override destroy
    override fun onDestroy() {
        super.onDestroy()
        eventDisposable?.dispose()
    }
}