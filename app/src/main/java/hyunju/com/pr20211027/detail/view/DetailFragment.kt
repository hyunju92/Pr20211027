package hyunju.com.pr20211027.detail.view

import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import hyunju.com.pr20211027.R
import hyunju.com.pr20211027.databinding.FragmentDetailBinding
import hyunju.com.pr20211027.detail.vm.DetailUiEvent
import hyunju.com.pr20211027.detail.vm.DetailViewModel
import hyunju.com.pr20211027.home.view.HomeBaseFragment
import io.reactivex.rxjava3.disposables.Disposable

@AndroidEntryPoint
class DetailFragment : HomeBaseFragment() {

    private lateinit var binding: FragmentDetailBinding
    private var eventDisposable: Disposable? = null

    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate<FragmentDetailBinding>(inflater, R.layout.fragment_detail, container, false).apply {
            detailVm = detailViewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeData()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        detailViewModel.clickMenu(item.itemId)
        return true
    }

    private fun initData() {
        DetailFragmentArgs.fromBundle(requireArguments()).data.let {
            detailViewModel.setData(it)
        }
    }

    private fun initView() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                detailViewModel.onBackPressed()
            }
        })

        setHomeDrawerState(true)
    }

    private fun observeData() {
        eventDisposable = detailViewModel.uiEvent.subscribe {
            handleUiEvent(it)
        }
    }

    // ui Event
    private fun handleUiEvent(uiEvent: DetailUiEvent) = when(uiEvent) {
        DetailUiEvent.BackToMain -> navigateHomeToBack()
        is DetailUiEvent.AddCurrentList -> addSharedCurrentList(uiEvent.data)
    }

    // override destroy
    override fun onDestroyView() {
        super.onDestroyView()
        eventDisposable?.dispose()
    }

}