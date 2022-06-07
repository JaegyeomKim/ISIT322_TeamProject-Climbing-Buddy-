package com.hfad.climbingbuddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.apollographql.apollo3.ApolloClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.apollographql.apollo3.api.Optional
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.retryWhen
import kotlinx.coroutines.launch
import kotlinx.coroutines.channels.Channel
import com.apollographql.apollo3.exception.ApolloException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launchWhenResumed {
            //val apolloClient = ApolloClient.Builder().serverUrl("https://api.openbeta.io").build()
            //val response = apolloClient().query(TestQuery()).execute()
            val response = try {
                apolloClient().query(TestQuery()).execute()
            } catch (e: ApolloException) {
                Log.d("LaunchList", "Failure", e)
                return@launchWhenResumed
            }
            val r = response
        }
    }




}