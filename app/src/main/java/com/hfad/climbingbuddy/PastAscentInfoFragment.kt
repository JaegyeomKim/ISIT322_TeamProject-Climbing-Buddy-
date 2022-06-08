package com.hfad.climbingbuddy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController

class PastAscentInfoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_past_ascent_info, container, false)
        val homeButton = view.findViewById<Button>(R.id.homeButton6)
        val backButton = view.findViewById<Button>(R.id.backButton1)

        //Goes to the pastAscentsListFragment
        homeButton.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_pastAscentInfoFragment_to_homeFragment)
        }

        backButton.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_pastAscentInfoFragment_to_pastAscentsListFragment)
        }
        //Inflate the layout for this fragment
        return view
    }
}