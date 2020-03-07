package com.example.coffeetracker

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import org.threeten.bp.OffsetDateTime

@Dao
interface CoffeeEventDao {
    @Query("SELECT * FROM coffeeevent")
    fun getAll(): LiveData<List<CoffeeEvent>>

    @Query(
        "SELECT * FROM coffeeevent " +
        "WHERE datetime(timestamp) >= datetime(:start) AND datetime(timestamp) < datetime(:end)"
    )
    fun loadAllByTimestamp(start: OffsetDateTime, end: OffsetDateTime): LiveData<List<CoffeeEvent>>

    @Insert
    suspend fun insertAll(vararg coffeeEvents: CoffeeEvent)

    @Delete
    suspend fun delete(coffeeEvent: CoffeeEvent)
}
