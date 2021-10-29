package hyunju.com.pr20211027.main.view

import android.os.Bundle
import android.util.Log
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
import hyunju.com.pr20211027.home.vm.HomeViewModel
import hyunju.com.pr20211027.main.network.ResMainData
import hyunju.com.pr20211027.main.view.adapter.MainAdapter
import hyunju.com.pr20211027.main.vm.MainViewModel

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private val sharedViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate<FragmentMainBinding>(inflater,
            R.layout.fragment_main, container, false).apply {}
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main_frag, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }


    private fun initView() {
        binding.mainFragBtn.setOnClickListener {
            mainViewModel.getMainData()
        }

        binding.mainRv.run {
            layoutManager = LinearLayoutManager(context)
            adapter = MainAdapter(mainViewModel, sharedViewModel)
        }
    }


    private fun moveToDetailFrag() {
        val action =
            MainFragmentDirections.actionMainFragmentToDetailFragment()
//        requireActivity().findNavController(R.id.nav_host_fragment_container).navigate(action)

        Navigation.findNavController(requireActivity(), (R.id.nav_host_fragment_container)).navigate(action)
    }

}