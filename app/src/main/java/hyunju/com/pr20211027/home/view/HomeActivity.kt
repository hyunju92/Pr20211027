package hyunju.com.pr20211027.home.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import hyunju.com.pr20211027.R
import hyunju.com.pr20211027.databinding.ActivityHomeBinding
import hyunju.com.pr20211027.home.vm.HomeViewModel

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityHomeBinding>(this, R.layout.activity_home).apply {
            homeVm = homeViewModel
        }

        initView()
    }

    private fun initView() {
        setSupportActionBar(binding.homeContents.toolbar)

        val navView: NavigationView = binding.homeNav
        val navController = findNavController(R.id.nav_host_fragment_container)

        val appBarConfiguration = AppBarConfiguration(navController.graph)


//        navView.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment_container).navigateUp()
    }

    private fun openDrawer() {
        binding.homeDrawer.openDrawer(Gravity.RIGHT)
    }

}