package com.hfad.climbingbuddy

import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< Updated upstream

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StartClimbFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StartClimbFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
=======
import android.widget.Button
import android.widget.Chronometer
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController

class StartClimbFragment : Fragment() {


    lateinit var TimeStart: Chronometer  //The stopwatch
    var running = false  //Is the stopwatch running?
    var offset: Long = 0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

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
>>>>>>> Stashed changes
        }
    }

<<<<<<< Updated upstream
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start_climb, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StartClimbFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StartClimbFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
=======
        resetButton.setOnClickListener {
            offset = 0
            setBaseTime()
        }

        //Inflate the layout for this fragment
        return view
>>>>>>> Stashed changes
    }


    fun setBaseTime() {
        TimeStart.base = SystemClock.elapsedRealtime() - offset
    }


    fun saveOffset() {
        offset = SystemClock.elapsedRealtime() - TimeStart.base
    }
}