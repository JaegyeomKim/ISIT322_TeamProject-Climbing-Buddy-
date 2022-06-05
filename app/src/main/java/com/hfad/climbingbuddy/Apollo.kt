package com.hfad.climbingbuddy

import android.content.Context
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

private var instance: ApolloClient? = null

fun apolloClient(): ApolloClient {
    if (instance != null) {
        return instance!!
    }

    /*val okHttpClient = OkHttpClient.Builder()
        .build()*/

    instance = ApolloClient.Builder()
        .serverUrl("https://api.openbeta.io")
        //.webSocketServerUrl("wss://apollo-fullstack-tutorial.herokuapp.com/graphql")
        //.okHttpClient(okHttpClient)
        .build()

    return instance!!
}
