package com.hfad.climbingbuddy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController

class StartClimbFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_start_climb, container, false)
        val homeButton = view.findViewById<Button>(R.id.homeButton1)
        val startButton = view.findViewById<Button>(R.id.startButton)
        val stopButton = view.findViewById<Button>(R.id.stopButton)
        val saveButton = view.findViewById<Button>(R.id.saveButton)
        val resetButton = view.findViewById<Button>(R.id.resetButton)


        homeButton.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_startClimbFragment_to_homeFragment)
        }

        startButton.setOnClickListener {

        }

        stopButton.setOnClickListener {

        }

        saveButton.setOnClickListener {

        }

        resetButton.setOnClickListener {

        }
        //Inflate the layout for this fragment
        return view
    }
}