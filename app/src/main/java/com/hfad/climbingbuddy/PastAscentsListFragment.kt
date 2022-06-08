package com.hfad.climbingbuddy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController


class PastAscentsListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_past_ascents_list, container, false)
        val homeButton = view.findViewById<Button>(R.id.homeButton7)
        val areaButton = view.findViewById<Button>(R.id.viewInfoButton)

        //Goes to the pastAscentsListFragment
        homeButton.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_pastAscentsListFragment_to_homeFragment)
        }

        areaButton.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_pastAscentsListFragment_to_pastAscentInfoFragment)
        }
        //Inflate the layout for this fragment
        return view
    }
}