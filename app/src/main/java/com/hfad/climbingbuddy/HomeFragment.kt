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


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {



        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = ClimbingUserDatabase.getInstance(application).climbingUserDao
        val viewModelFactory = ClimbingUserModelFactory(dao)
        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(ClimbingUserViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner





        //Inflate the layout for this fragment

        val viewAscentButton = view.findViewById<Button>(R.id.viewAscentsButton)
        val newAscentButton = view.findViewById<Button>(R.id.newAscentButton)

        //Goes to the pastAscentsListFragment
        viewAscentButton.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_homeFragment_to_pastAscentsListFragment)
        }
        //Goes to the searchAreaFragment
        newAscentButton.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_homeFragment_to_searchAreaFragment)
        }

        return view
    }
}