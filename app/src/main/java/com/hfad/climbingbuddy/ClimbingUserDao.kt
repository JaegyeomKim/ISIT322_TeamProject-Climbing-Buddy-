package com.hfad.climbingbuddy
import androidx.lifecycle.LiveData
import androidx.room.*
@Dao
interface ClimbingUserDao {
    @Insert
    suspend fun insert(climbing: ClimbingUser)
    @Update
    suspend fun update(climbing: ClimbingUser)
    @Delete
    suspend fun delete(climbing: ClimbingUser)
    @Query("SELECT * FROM climbingUser_table WHERE climbID = :key")
    fun get(key: Long): LiveData<ClimbingUser>
    @Query("SELECT * FROM climbingUser_table ORDER BY climbID DESC")
    fun getAll(): LiveData<List<ClimbingUser>>
    @Query("SELECT * FROM climbingUser_table WHERE climbID = :key")
    fun getMine(key: Int): LiveData<List<ClimbingUser>>


    @Query("Update climbingUser_table SET climbing_TimeSpend = :pTimeSpend WHERE climbID = :id")
    fun updateTest(pTimeSpend: String, id: Int)

    @Query("SELECT climbingUser_table.climbID FROM climbingUser_table ORDER BY climbID DESC LIMIT 1")
    fun getOne(): LiveData<List<Int>>




    @Query("SELECT * FROM climbingUser_table")
    fun getTotal(): LiveData<List<ClimbingUser>>
}