package com.hfad.climbingbuddy
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Climbing::class], version = 1, exportSchema = false)
abstract class ClimbingDatabase : RoomDatabase() {
    abstract val climbingDao: ClimbingDao
    companion object{
        @Volatile
        private var INSTANCE: ClimbingDatabase? = null
        fun getInstance(context: Context): ClimbingDatabase{
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ClimbingDatabase::class.java,
                        "climbing_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}