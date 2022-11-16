package com.bignerdranch.android.calendarappfrthistime
import androidx.lifecycle.ViewModel

class EventListViewModel : ViewModel() {
    private val eventRepository = EventRepository.get()
    val eventListLiveData = eventRepository.getEvents()

    fun addEvent(event: Event) {
        eventRepository.addEvent(event)
    }
}