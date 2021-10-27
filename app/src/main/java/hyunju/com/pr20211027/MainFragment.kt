package hyunju.com.pr20211027

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import hyunju.com.pr20211027.databinding.FragmentMainBinding
import androidx.navigation.findNavController


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate<FragmentMainBinding>(inflater, R.layout.fragment_main, container, false).apply {}
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainFragBtn.setOnClickListener {
            moveToDetailFrag()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main_frag, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    private fun moveToDetailFrag() {
        val action = MainFragmentDirections.actionMainFragmentToDetailFragment()
//        requireActivity().findNavController(R.id.nav_host_fragment_container).navigate(action)

        Navigation.findNavController(requireActivity(), (R.id.nav_host_fragment_container)).navigate(action)
    }

}