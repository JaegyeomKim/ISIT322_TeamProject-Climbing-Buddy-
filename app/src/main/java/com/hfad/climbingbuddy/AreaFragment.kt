package com.hfad.climbingbuddy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.apollographql.apollo3.exception.ApolloException


class AreaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //araay = [1,3,2,4]

        val view = inflater.inflate(R.layout.fragment_area, container, false)

        val beerColor = view.findViewById<Spinner>(R.id.spinner_sample)

        beerColor

        lifecycleScope.launchWhenResumed {
            //val apolloClient = ApolloClient.Builder().serverUrl("https://api.openbeta.io").build()
            //val response = apolloClient().query(TestQuery()).execute()
            val response = try {
                apolloClient().query(MyQuery()).execute()
            } catch (e: ApolloException) {
                Log.d("LaunchList", "Failure", e)
                return@launchWhenResumed
            }

            var r = response.data?.areas?.elementAt(0)?.area_name.toString()
            val areaList = view.findViewById<TextView>(R.id.areaView)
            areaList.text = r
        }
        return view
    }
}