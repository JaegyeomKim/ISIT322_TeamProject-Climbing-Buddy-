package com.hfad.climbingbuddy

import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController

class StartClimbFragment : Fragment() {


    lateinit var TimeStart: Chronometer  //The stopwatch
    var running = false  //Is the stopwatch running?
    var offset: Long = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_start_climb, container, false)
        val homeButton = view.findViewById<Button>(R.id.homeButton1)
        val startButton = view.findViewById<Button>(R.id.startButton)
        val stopButton = view.findViewById<Button>(R.id.stopButton)
        val saveButton = view.findViewById<Button>(R.id.saveButton)
        val resetButton = view.findViewById<Button>(R.id.resetButton)
        TimeStart = view.findViewById<Chronometer>(R.id.TimeStart)

        homeButton.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_startClimbFragment_to_homeFragment)
        }

        startButton.setOnClickListener {

            if (!running) {
                setBaseTime()
                TimeStart.start()
                running = true
            }
        }

        stopButton.setOnClickListener {
            if (running) {
                TimeStart.stop()
                running = false
            }
        }

        saveButton.setOnClickListener {
            saveOffset()
        }


        resetButton.setOnClickListener {
            offset = 0
            setBaseTime()
        }

        //Inflate the layout for this fragment
        return view

    }


    fun setBaseTime() {
        TimeStart.base = SystemClock.elapsedRealtime() - offset
    }


    fun saveOffset() {
        offset = SystemClock.elapsedRealtime() - TimeStart.base
    }
}