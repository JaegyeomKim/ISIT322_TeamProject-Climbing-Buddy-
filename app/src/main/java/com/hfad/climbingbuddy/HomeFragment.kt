package com.hfad.climbingbuddy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController


class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        //Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

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