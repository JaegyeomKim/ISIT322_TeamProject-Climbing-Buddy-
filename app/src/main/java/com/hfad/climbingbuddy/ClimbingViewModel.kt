package com.hfad.climbingbuddy
import android.util.Log
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloException
import kotlinx.coroutines.launch
import com.hfad.climbingbuddy.MainActivity

class ClimbingViewModel(val dao: ClimbingDao) : ViewModel() {

    var newTimeSpend = ""
    var newNumFalls = ""
    var newTimeStamp = ""
    var newUUID = "0";

    private val climbingDB = dao.getAll()
    private val climbingDB2 = dao.getMine(1)
    val testUUID = "123dd123"

    var oneData = Transformations.map(climbingDB2) {
        climbingDB2 -> formatTasks2(climbingDB2)
    }

    val tasksString = Transformations.map(climbingDB) {
            climbingDB -> formatTasks(climbingDB)
    }

    fun addClimbing() {
        viewModelScope.launch {
            val climbing = Climbing()
            climbing.timeSpend = newTimeSpend
            climbing.numFalls = newNumFalls
            climbing.timeStamp = newTimeStamp
            climbing.UUID = newUUID
            dao.insert(climbing)
        }
    }

    fun getDataAndAddUUID() {
        viewModelScope.launch {
            val response = apolloClient().query(MyQuery()).execute()
            var r = response.data?.areas?.elementAt(0)?.uuid.toString()

            Thread {
                val testUUID2 = r
                dao.updateTest(testUUID2,5)
            }.start()
        }
    }

    fun formatTasks(climbing: List<Climbing>): String {
        return climbing.fold("") {
                str, item -> str + '\n' + formatTask(item)
        }
    }
    fun formatTasks2(climbing: List<Climbing>): String {
        return climbing.fold("") {
                str, item -> str + '\n' + formatTask2(item)
        }
    }

    fun formatTask2(climbing: Climbing): String {
        climbing.UUID = testUUID
        var str = "climbID: ${climbing.climbID}"
        str += '\n' + "TimeSpend: ${climbing.timeSpend}"
        str += '\n' + "NumFalls: ${climbing.numFalls}"
        str += '\n' + "TimeStamp: ${climbing.timeStamp}"
        str += '\n' + "UUID: ${climbing.UUID}" + '\n'
        return str
    }


    fun formatTask(climbing: Climbing): String {
        var str = "climbID: ${climbing.climbID}"
        str += '\n' + "TimeSpend: ${climbing.timeSpend}"
        str += '\n' + "NumFalls: ${climbing.numFalls}"
        str += '\n' + "TimeStamp: ${climbing.timeStamp}"
        str += '\n' + "UUID: ${climbing.UUID}" + '\n'
        return str
    }
}