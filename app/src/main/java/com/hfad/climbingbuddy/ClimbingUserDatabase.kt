package com.hfad.climbingbuddy
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ClimbingUser::class], version = 1, exportSchema = false)
abstract class ClimbingUserDatabase : RoomDatabase() {
    abstract val climbingUserDao: ClimbingUserDao
    companion object{
        @Volatile
        private var INSTANCE: ClimbingUserDatabase? = null
        fun getInstance(context: Context): ClimbingUserDatabase{
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ClimbingUserDatabase::class.java,
                        "climbingUser_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}