package com.hfad.climbingbuddy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.hfad.climbingbuddy.databinding.FragmentHomeBinding
import com.hfad.climbingbuddy.databinding.FragmentSearchAreaBinding

class SearchAreaFragment : Fragment() {

    private var _binding: FragmentSearchAreaBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentSearchAreaBinding.inflate(inflater, container, false)
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = ClimbingUserDatabase.getInstance(application).climbingUserDao
        val viewModelFactory = ClimbingUserModelFactory(dao)
        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(ClimbingUserViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        val homeButton = view.findViewById<Button>(R.id.homeButton8)
        val areaSearchButton = view.findViewById<Button>(R.id.button4)
        val climbStartButton = view.findViewById<Button>(R.id.newClimbButton)


        homeButton.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_searchAreaFragment_to_homeFragment)
        }

        areaSearchButton.setOnClickListener {

        }

        climbStartButton.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_searchAreaFragment_to_startClimbFragment)
        }


        return view
    }
}