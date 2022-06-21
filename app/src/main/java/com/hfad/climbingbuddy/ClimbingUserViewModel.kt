package com.hfad.climbingbuddy
import android.os.Build
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import androidx.navigation.findNavController
import androidx.room.Room
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloException
import kotlinx.coroutines.launch
import com.hfad.climbingbuddy.MainActivity
import java.time.LocalDateTime

var myID = 0


class ClimbingUserViewModel(val dao: ClimbingUserDao) : ViewModel() {

    var newTimeSpend = ""
    var newNumFalls = ""
    var newTimeStamp = ""
    var newUUID = "0"
    var areaName = ""
    var sensor = ""
    var userSearch = ""


    @RequiresApi(Build.VERSION_CODES.O)
    val time = LocalDateTime.now().toString()


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
            val response2 = apolloClient().query(ourAreaName()).execute()


            var newUUIDFromQuery = ""
            for (i in 0..25){
                if(response.data?.areas?.elementAt(i)?.area_name.toString() == areaName){
                    newUUIDFromQuery = response.data?.areas?.elementAt(i)?.uuid.toString()
                    break
                }
            }
//            var r = response.data?.areas?.elementAt(10)?.uuid.toString()

            val climbing = ClimbingUser()
            climbing.timeSpend = newTimeSpend
            climbing.numFalls = newNumFalls
            climbing.timeStamp = newTimeStamp
            climbing.UUID = newUUIDFromQuery
            dao.insert(climbing)
        }
    }


    fun ourTest() {
        viewModelScope.launch {

        }
    }




    @RequiresApi(Build.VERSION_CODES.O)
    fun AddTimeSpend() {
        viewModelScope.launch {
            Thread {
                dao.updateTest(newTimeStamp,time,sensor, myID)
            }.start()
        }
    }

    fun formatTasks2(climbing: List<Int>): String {
        return climbing.fold("") {
                str, item -> str + '\n' + formatTask2(item)
        }
    }

    fun formatTask2(int: Int): Int {
        myID  = int
        return myID
    }


    fun deleteAll() {
        viewModelScope.launch {
            Thread {
                dao.deleteAll()
            }.start()
        }
    }

}

