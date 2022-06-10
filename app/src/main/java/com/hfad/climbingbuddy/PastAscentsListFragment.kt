package com.hfad.climbingbuddy
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.hfad.climbingbuddy.databinding.FragmentPastAscentsListBinding


class PastAscentsListFragment : Fragment() {

    private var _binding: FragmentPastAscentsListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = FragmentPastAscentsListBinding.inflate(inflater, container, false)
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = ClimbingUserDatabase.getInstance(application).climbingUserDao
        val viewModelFactory = ClimbingUserModelFactory(dao)
        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(ClimbingUserViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        val homeButton = view.findViewById<Button>(R.id.homeButton7)

        //Goes to the pastAscentsListFragment
        homeButton.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_pastAscentsListFragment_to_homeFragment)
        }

        val areaButton = view.findViewById<Button>(R.id.viewInfoButton)
        areaButton.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_pastAscentsListFragment_to_testFragment)
        }

        return view
    }
}