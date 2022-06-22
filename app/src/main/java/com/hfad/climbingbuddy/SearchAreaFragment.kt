package com.hfad.climbingbuddy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.apollographql.apollo3.exception.ApolloException
import com.hfad.climbingbuddy.databinding.FragmentHomeBinding
import com.hfad.climbingbuddy.databinding.FragmentSearchAreaBinding
import android.widget.*
import androidx.appcompat.app.AppCompatActivity



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
        val areaView = view.findViewById<TextView>(R.id.AreaView)

        val AreaViewList = view.findViewById<TextView>(R.id.AreaViewList)
        val searchButton = view.findViewById<Button>(R.id.search_button)
        val userInput = view.findViewById<TextView>(R.id.userInputArea)

        val myArray = ArrayList<String>()
        lifecycleScope.launchWhenResumed {
            val response = apolloClient().query(MyQuery()).execute()
            for (i in 0..15){
                val r = response.data?.areas?.elementAt(i)?.area_name.toString()
                myArray.add(r)
            }
            areaView.text = "Ready to search!"
        }


        searchButton.setOnClickListener {
            AreaViewList.setText("")

            var input= userInput.text.toString()
            var inputLength= userInput.text.toString().length

            var check = true
            for (i in 0..15){

                if (myArray[i].length < inputLength) {
                    inputLength = myArray[i].length
                }

                for (j in 0..inputLength-1){
                    if (input.get(j) == myArray[i].get(j)){

                    }
                    else{
                        check = false
                    }
                }
                if (check){
                    AreaViewList.append(myArray[i])
                    AreaViewList.append("\n")
                }
                check = true
            }

        }

        var mycheck = false
        val confirmButton = view.findViewById<Button>(R.id.confirm_button)
        confirmButton.setOnClickListener {
            var input= userInput.text.toString()

            for (i in 0..15){
                if(input == myArray[i]){
                    mycheck = true
                    viewModel.createDBAndAddUUID()
                    areaView.text = "Saved"
                }
            }

            if(mycheck == false){
                areaView.text = "Sorry, Can't find the area name."
            }
        }

        val homeButton = view.findViewById<Button>(R.id.homeButton8)
        val climbStartButton = view.findViewById<Button>(R.id.newClimbButton)

        homeButton.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_searchAreaFragment_to_homeFragment)
        }

        climbStartButton.setOnClickListener {

            view.findNavController()
                .navigate(R.id.action_searchAreaFragment_to_startClimbFragment)
        }

        return view
    }

}