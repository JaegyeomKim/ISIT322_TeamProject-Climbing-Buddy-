package com.hfad.climbingbuddy
import androidx.lifecycle.LiveData
import androidx.room.*
@Dao
interface ClimbingDao {
    @Insert //(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(climbing: Climbing)
    @Update
    suspend fun update(climbing: Climbing)
    @Delete
    suspend fun delete(climbing: Climbing)
    @Query("SELECT * FROM climbing_table WHERE climbID = :key")
    fun get(key: Long): LiveData<Climbing>
    @Query("SELECT * FROM climbing_table ORDER BY climbID DESC")
    fun getAll(): LiveData<List<Climbing>>
    @Query("SELECT * FROM climbing_table WHERE climbID = :key")
    fun getMine(key: Int): LiveData<List<Climbing>>




    @Query("Update climbing_table SET climbing_arealID = :pUUID WHERE climbID = :id")
    fun updateTest(pUUID: String, id: Int)

    @Query("SELECT * FROM climbing_table where climbID = :id")
    fun getClimbing(id: Int): Climbing
}