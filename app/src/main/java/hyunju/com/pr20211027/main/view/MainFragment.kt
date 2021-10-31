package hyunju.com.pr20211027.main.view

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import hyunju.com.pr20211027.R
import hyunju.com.pr20211027.databinding.FragmentMainBinding
import hyunju.com.pr20211027.home.view.HomeBaseFragment
import hyunju.com.pr20211027.main.view.adapter.MainAdapter
import hyunju.com.pr20211027.main.vm.MainUiEvent
import hyunju.com.pr20211027.main.vm.MainViewModel
import io.reactivex.rxjava3.disposables.Disposable

@AndroidEntryPoint
class MainFragment : HomeBaseFragment() {

    private lateinit var binding: FragmentMainBinding

    private val mainViewModel: MainViewModel by viewModels()

    private var eventDisposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate<FragmentMainBinding>(inflater, R.layout.fragment_main, container, false).apply {
            mainVm = mainViewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        observeData()
        requestMainData()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main_frag, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        mainViewModel.clickMenu(item.itemId)
        return true
    }

    private fun initView() {
        binding.mainRv.run {
            layoutManager = LinearLayoutManager(context)
            adapter = MainAdapter(mainViewModel)
        }

        setHomeDrawerState(isLock = false)
    }

    private fun observeData() {
        eventDisposable = mainViewModel.uiEvent.subscribe {
            handleUiEvent(it)
        }
    }

    private fun requestMainData() {
        mainViewModel.getMainUiData(getSharedCurrentList())
    }


    // ui Event
    private fun handleUiEvent(uiEvent: MainUiEvent) = when (uiEvent) {
        is MainUiEvent.MoveDetail -> navigateHomeToDetail(uiEvent.data)
        MainUiEvent.OpenHomeDrawer -> openHomeDrawer()
    }

    // override destroy
    override fun onDestroyView() {
        super.onDestroyView()
        eventDisposable?.dispose()
    }

}