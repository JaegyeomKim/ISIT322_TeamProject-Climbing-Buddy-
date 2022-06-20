package com.hfad.climbingbuddy

import android.annotation.SuppressLint
import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.hfad.climbingbuddy.databinding.FragmentStartClimbBinding


class StartClimbFragment : Fragment(), SensorEventListener {


    private var _binding: FragmentStartClimbBinding? = null
    private val binding get() = _binding!!

    lateinit var TimeStart: Chronometer  //The stopwatch
    var running = false  //Is the stopwatch running?
    var offset: Long = 0

    private lateinit var square: TextView


    var fallCount: Int = 0
    private lateinit var sensorManager: SensorManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentStartClimbBinding.inflate(inflater, container, false)
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = ClimbingUserDatabase.getInstance(application).climbingUserDao
        val viewModelFactory = ClimbingUserModelFactory(dao)
        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(ClimbingUserViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setUpSensorStuff()


        val homeButton = view.findViewById<Button>(R.id.homeButton1)
        val startButton = view.findViewById<Button>(R.id.startButton)
        val stopButton = view.findViewById<Button>(R.id.stopButton)
        val saveButton = view.findViewById<Button>(R.id.saveButton)
        val resetButton = view.findViewById<Button>(R.id.resetButton)
        TimeStart = view.findViewById<Chronometer>(R.id.TimeStart)
        square = view.findViewById<TextView>(R.id.test_square)



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

    private fun setUpSensorStuff() {
        sensorManager = requireActivity().getSystemService(SENSOR_SERVICE) as SensorManager
        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.also {
            sensorManager.registerListener(this,
                it,
                SensorManager.SENSOR_DELAY_FASTEST,
                SensorManager.SENSOR_DELAY_FASTEST)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            val sides = event.values[0]
            val upDown = event.values[1]

            if (upDown < 13) {
                fallCount++
            }
            //fall count${fallCount}
            square.text = "${fallCount/80}"
        }
    }
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }
    override fun onDestroy() {
        sensorManager.unregisterListener(this)
        super.onDestroy()
    }


}