package hyunju.com.pr20211027.detail.view

import android.os.Bundle
import android.provider.ContactsContract
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import hyunju.com.pr20211027.R
import hyunju.com.pr20211027.databinding.FragmentDetailBinding
import hyunju.com.pr20211027.detail.vm.DetailUiEvent
import hyunju.com.pr20211027.detail.vm.DetailViewModel
import hyunju.com.pr20211027.home.vm.HomeViewModel
import hyunju.com.pr20211027.main.network.ProductItem
import io.reactivex.rxjava3.disposables.Disposable

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private var eventDisposable: Disposable? = null

    private val detailViewModel: DetailViewModel by viewModels()
    private val sharedViewModel: HomeViewModel by activityViewModels()

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_detail_frag, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> detailViewModel.onBackPressed()
        }
        return true
    }

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
        setBackPressed()
        observeLiveData()
    }

    private fun initData() {
        DetailFragmentArgs.fromBundle(requireArguments()).data.let {
            detailViewModel.setData(it)
        }
    }

    private fun setBackPressed() {
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true ) {
            override fun handleOnBackPressed() {
                detailViewModel.onBackPressed()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun observeLiveData() {
        eventDisposable = detailViewModel.uiEvent.subscribe {
            handleUiEvent(it)
        }
    }

    private fun handleUiEvent(uiEvent: DetailUiEvent) = when(uiEvent) {
        DetailUiEvent.BackToMain -> backToMainFragment()
        is DetailUiEvent.AddCurrentList -> addCurrentList(uiEvent.data)
    }

    private fun backToMainFragment() {
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container).navigateUp()
    }

    private fun addCurrentList(data: ProductItem){
        sharedViewModel.addCurrentList(data)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        eventDisposable?.dispose()
    }


}