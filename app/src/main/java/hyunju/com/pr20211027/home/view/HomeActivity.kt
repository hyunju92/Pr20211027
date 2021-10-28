package hyunju.com.pr20211027.home.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import hyunju.com.pr20211027.R
import hyunju.com.pr20211027.databinding.ActivityHomeBinding

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        initView()
    }

    private fun initView() {
        setSupportActionBar(binding.homeContents.toolbar)

        val navView: NavigationView = binding.homeNav
        val navController = findNavController(R.id.nav_host_fragment_container)

//        navView.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this, navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment_container).navigateUp()
    }

    private fun openDrawer() {
        binding.homeDrawer.openDrawer(Gravity.RIGHT)
    }

}