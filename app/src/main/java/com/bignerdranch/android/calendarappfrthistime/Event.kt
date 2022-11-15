package com.bignerdranch.android.calendarappfrthistime

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Event(@PrimaryKey val id: UUID = UUID.randomUUID(),
                 var title: String = "",
                 var startDate: Date = Date(),
                 var endDate: Date = Date(),
                 var location: String = "",
                 var details: String = "",
                 var reminder: Date = Date())