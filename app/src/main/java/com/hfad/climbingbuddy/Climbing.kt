package com.hfad.climbingbuddy
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "climbing_table")
data class Climbing(
    @PrimaryKey(autoGenerate = true)
    var climbID: Int = 0,   // outo
    @ColumnInfo(name = "climbing_TimeSpend")
    var timeSpend: String = "",   // on the app
    @ColumnInfo(name ="climbing_numFalls")
    var numFalls: String = "",    // on the app
    @ColumnInfo(name = "climbing_timeStamp")
    var timeStamp: String = "",  // current time value in  app

    @ColumnInfo(name = "climbing_arealID")
    var UUID: String = "",    //UUID from API - allow nullable
)