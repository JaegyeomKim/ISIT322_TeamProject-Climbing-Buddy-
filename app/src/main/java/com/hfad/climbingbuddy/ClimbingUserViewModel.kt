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

class ClimbingUserViewModel(val dao: ClimbingUserDao) : ViewModel() {

    var newTimeSpend = ""
    var newNumFalls = ""
    var newTimeStamp = ""
    var newUUID = "0";

    private val climbingDB = dao.getAll()

    val tasksString = Transformations.map(climbingDB) {
            climbingDB -> formatTasks(climbingDB)
    }

    fun addClimbing() {
        viewModelScope.launch {
            val climbing = ClimbingUser()
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

    fun formatTasks(climbing: List<ClimbingUser>): String {
        return climbing.fold("") {
                str, item -> str + '\n' + formatTask(item)
        }
    }


    fun formatTask(climbing: ClimbingUser): String {
        var str = "climbID: ${climbing.climbID}"
        str += '\n' + "TimeSpend: ${climbing.timeSpend}"
        str += '\n' + "NumFalls: ${climbing.numFalls}"
        str += '\n' + "TimeStamp: ${climbing.timeStamp}"
        str += '\n' + "UUID: ${climbing.UUID}" + '\n'
        return str
    }
}