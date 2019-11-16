package com.example.pagingsample.usecases.database.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.pagingsample.usecases.database.entity.User

/**
 * Database Access Object for the User database.
 */
@Dao
interface UserDao {
    /**
     * Room knows how to return a LivePagedListProvider, from which we can get a LiveData and serve
     * it back to UI via ViewModel.
     */
    @Query("SELECT * FROM User ORDER BY name COLLATE NOCASE ASC")
    fun allUsersByName(): DataSource.Factory<Int, User>

    @Insert
    fun insert(users: List<User>)

    @Insert
    fun insert(user: User)

    @Delete
    fun delete(user: User)
}