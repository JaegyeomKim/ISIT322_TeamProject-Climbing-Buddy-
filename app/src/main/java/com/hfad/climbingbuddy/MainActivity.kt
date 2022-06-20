package com.hfad.climbingbuddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import com.hfad.climbingbuddy.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding // Kay for viewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)   // Kay for viewBinding
        val view = binding.root                                 // Kay for viewBinding
        setContentView(view)
    }
}