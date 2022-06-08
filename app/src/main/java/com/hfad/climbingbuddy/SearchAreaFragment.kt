package com.hfad.climbingbuddy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController

class SearchAreaFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search_area, container, false)

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