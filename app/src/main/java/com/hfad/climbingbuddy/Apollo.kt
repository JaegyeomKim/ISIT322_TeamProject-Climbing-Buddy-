package com.hfad.climbingbuddy

import com.apollographql.apollo3.ApolloClient

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
