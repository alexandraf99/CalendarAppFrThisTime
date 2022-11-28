package com.bignerdranch.android.calendarappfrthistime

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import java.text.SimpleDateFormat
import java.util.*

private const val ARG_CRIME_ID = "event_id"
private const val TAG = "EventFragment"
private const val DIALOG_DATE = "DialogDate"
private const val REQUEST_DATE = 0
class EventFragment : Fragment(), DatePickerFragment.Callbacks{
    private lateinit var event: Event
    private lateinit var titleField: EditText
    private lateinit var startDateButton: Button
    private lateinit var endDateButton: Button
    private lateinit var reminderButton: Button
    private lateinit var notesField: EditText

    private val eventDetailViewModel: EventDetailViewModel by lazy {
        ViewModelProviders.of(this).get(EventDetailViewModel::class.java)
    }
    private var dateFormatter: SimpleDateFormat =
        SimpleDateFormat("EEEEEEEEE, MMM dd, yyyy", Locale.US)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        event = Event()
        val eventId: UUID = arguments?.getSerializable(ARG_CRIME_ID) as UUID
        eventDetailViewModel.loadEvent(eventId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        titleField = view.findViewById(R.id.event_name) as EditText
        startDateButton = view.findViewById(R.id.event_start_date) as Button
        endDateButton = view.findViewById(R.id.event_end_date) as Button
        reminderButton = view.findViewById(R.id.event_reminder) as Button
        notesField = view.findViewById(R.id.event_notes) as EditText

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventDetailViewModel.eventLiveData.observe(
            viewLifecycleOwner,
            Observer { event ->
                event?.let {
                    this.event = event
                    updateUI()
                }
            })
    }

    override fun onStart() {
        super.onStart()
        val titleWatcher = object : TextWatcher {
            override fun beforeTextChanged(
                sequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
// This space intentionally left blank
            }

            override fun onTextChanged(
                sequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                event.title = sequence.toString()
            }

            override fun afterTextChanged(sequence: Editable?) {
// This one too
            }
        }
        val notesWatcher = object : TextWatcher {
            override fun beforeTextChanged(
                sequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
// This space intentionally left blank
            }

            override fun onTextChanged(
                sequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                event.title = sequence.toString()
            }

            override fun afterTextChanged(sequence: Editable?) {
// This one too
            }
        }

        titleField.addTextChangedListener(titleWatcher)
        startDateButton.setOnClickListener {
            DatePickerFragment.newInstance(event.startDate).apply  {
                setTargetFragment(this@EventFragment, REQUEST_DATE)
                show(this@EventFragment.requireFragmentManager(), DIALOG_DATE)
            }
        }
        endDateButton.setOnClickListener {
            DatePickerFragment.newInstance(event.startDate).apply  {
                setTargetFragment(this@EventFragment, REQUEST_DATE)
                show(this@EventFragment.requireFragmentManager(), DIALOG_DATE)
            }
        }
        reminderButton.setOnClickListener {
            DatePickerFragment.newInstance(event.startDate).apply  {
                setTargetFragment(this@EventFragment, REQUEST_DATE)
                show(this@EventFragment.requireFragmentManager(), DIALOG_DATE)
            }
        }
        notesField.addTextChangedListener(notesWatcher)


    }

    override fun onStop() {
        super.onStop()
        eventDetailViewModel.saveEvent(event)
    }

    override fun onDateSelected(date: Date) {

    }


    private fun updateUI() {
        titleField.setText(event.title)
    }
    companion object {
        fun newInstance(eventId: UUID): EventFragment {
            val args = Bundle().apply {
                putSerializable(ARG_CRIME_ID, eventId)
            }
            return EventFragment().apply {
                arguments = args
            }
        }
    }
}