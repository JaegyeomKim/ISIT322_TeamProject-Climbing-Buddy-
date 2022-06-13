package com.hfad.climbingbuddy
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.*
import androidx.room.Room
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloException
import kotlinx.coroutines.launch
import com.hfad.climbingbuddy.MainActivity


class ClimbingUserViewModel(val dao: ClimbingUserDao) : ViewModel() {
    var newTimeSpend = ""
    var newNumFalls = ""
    var newTimeStamp = ""
    var newUUID = "0"
    var myTestValue = ""

    private val climbingDB = dao.getAll()
    private val climbingDB_getOne = dao.getOne()

    val tasksString = Transformations.map(climbingDB) {
            climbingDB -> formatTasks(climbingDB)
    }

    val oneString = Transformations.map(climbingDB_getOne) {
            climbingDB -> formatTasks2(climbingDB)
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

    fun createDBAndAddUUID() {
        viewModelScope.launch {
            val response = apolloClient().query(MyQuery()).execute()
            var r = response.data?.areas?.elementAt(0)?.uuid.toString()
            val climbing = ClimbingUser()
            climbing.timeSpend = newTimeSpend
            climbing.numFalls = newNumFalls
            climbing.timeStamp = newTimeStamp
            climbing.UUID = r
            dao.insert(climbing)
        }
    }

    fun AddTimeSpend() {
        viewModelScope.launch {
            Thread {
                dao.updateTest(newTimeStamp,32)
            }.start()
        }
    }

    fun formatTasks2(climbing: List<Int>): String {
        return climbing.fold("") {
                str, item -> str + '\n' + formatTask2(item)
        }
    }

    fun formatTask2(int: Int): String {
        var str = "climbID: ${int}" + '\n'
        return str
    }

    fun getViewValue() : String{
        return myTestValue
    }

}
