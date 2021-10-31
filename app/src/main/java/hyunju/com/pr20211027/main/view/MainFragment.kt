package hyunju.com.pr20211027.main.view

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import hyunju.com.pr20211027.R
import hyunju.com.pr20211027.databinding.FragmentMainBinding
import hyunju.com.pr20211027.home.vm.SharedViewModel
import hyunju.com.pr20211027.main.network.ProductItem
import hyunju.com.pr20211027.main.view.adapter.MainAdapter
import hyunju.com.pr20211027.main.vm.MainUiEvent
import hyunju.com.pr20211027.main.vm.MainViewModel
import io.reactivex.rxjava3.disposables.Disposable

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private val mainViewModel: MainViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private var eventDisposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate<FragmentMainBinding>(inflater, R.layout.fragment_main, container, false).apply {
            mainVm = mainViewModel
            sharedVm = sharedViewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        observeData()
        initAction()
    }

    private fun initView() {
        binding.mainRv.run {
            layoutManager = LinearLayoutManager(context)
            adapter = MainAdapter(mainViewModel, sharedViewModel)
        }
        sharedViewModel.setDrawerLockState(false)
    }

    private fun observeData() {
        eventDisposable = mainViewModel.uiEvent.subscribe {
            handleUiEvent(it)
        }
    }

    private fun initAction() {
        mainViewModel.getMainUiData(sharedViewModel.currentItemList)
    }

    private fun handleUiEvent(uiEvent: MainUiEvent) = when (uiEvent) {
        is MainUiEvent.MoveDetail -> moveToDetailFrag(uiEvent.data)
        MainUiEvent.OpenHomeDrawer -> openHomeDrawer()
    }

    private fun moveToDetailFrag(data: ProductItem) {
        val action = MainFragmentDirections.actionMainFragmentToDetailFragment(data)
        Navigation.findNavController(requireActivity(), (R.id.homeNavHostFrag)).navigate(action)
    }

    private fun openHomeDrawer(){
        sharedViewModel.openDrawer()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main_frag, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        mainViewModel.clickMainMenu(item.itemId)
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        eventDisposable?.dispose()
    }

}