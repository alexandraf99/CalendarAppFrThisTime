package com.bignerdranch.android.calendarappfrthistime.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.bignerdranch.android.calendarappfrthistime.Event
import java.util.*

@Dao
interface EventDao {
    @Query("SELECT * FROM event")
    fun getEvents(): LiveData<List<Event>>
    @Query("SELECT * FROM event WHERE id=(:id)")
    fun getEvent(id: UUID): LiveData<Event?>
    @Update
    fun updateEvent(crime: Event)
    @Insert
    fun addEvent(crime: Event)
}