package com.hfad.climbingbuddy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.hfad.climbingbuddy.databinding.FragmentClimbingBinding


class ClimbingFragment : Fragment() {

    private var _binding: FragmentClimbingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //This code is for connect to SQLite
        _binding = FragmentClimbingBinding.inflate(inflater, container, false)
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = ClimbingDatabase.getInstance(application).climbingDao
        val viewModelFactory = ClimbingModelFactory(dao)
        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(ClimbingViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        //val view = inflater.inflate(R.layout.fragment_, container, false)


        //This code is for the navigate to Area
        val startButton = view.findViewById<Button>(R.id.nextButton)
        startButton.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_climbingFragment_to_areaFragment)
        }



        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}