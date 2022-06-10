package com.hfad.climbingbuddy
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.hfad.climbingbuddy.databinding.FragmentClimbingUserBinding



class ClimbingUserFragment : Fragment() {

    private var _binding: FragmentClimbingUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //This code is for connect to SQLite
        _binding = FragmentClimbingUserBinding.inflate(inflater, container, false)
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = ClimbingUserDatabase.getInstance(application).climbingUserDao
        val viewModelFactory = ClimbingUserModelFactory(dao)
        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(ClimbingUserViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}