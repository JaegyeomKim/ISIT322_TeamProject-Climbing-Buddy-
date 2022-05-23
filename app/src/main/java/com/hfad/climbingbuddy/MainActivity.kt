package com.hfad.climbingbuddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Handler(Looper.getMainLooper()).postDelayed(object : Runnable {
//            override fun run() {
//                startActivity(Intent(this@MainActivity, InforButtonFragment::class.java))
//            }
//        },200)
    }
}

