package hyunju.com.pr20211027.detail.view

import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import hyunju.com.pr20211027.R
import hyunju.com.pr20211027.databinding.FragmentDetailBinding
import hyunju.com.pr20211027.home.vm.HomeViewModel

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private val sharedViewModel: HomeViewModel by activityViewModels()

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_detail_frag, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> backToMainFragment()
        }
        return true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate<FragmentDetailBinding>(inflater, R.layout.fragment_detail, container, false).apply {}
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        setBackPressed()
    }

    private fun setBackPressed() {
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true ) {
            override fun handleOnBackPressed() { backToMainFragment() }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun backToMainFragment() {
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container).navigateUp()
    }


}