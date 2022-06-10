package com.hfad.climbingbuddy
import androidx.lifecycle.LiveData
import androidx.room.*
@Dao
interface ClimbingUserDao {
    @Insert //(onConflict = OnConflictStrategy.REPLACE)
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




    @Query("Update climbingUser_table SET climbing_arealID = :pUUID WHERE climbID = :id")
    fun updateTest(pUUID: String, id: Int)

    @Query("SELECT * FROM climbingUser_table where climbID = :id")
    fun getClimbing(id: Int): ClimbingUser
}