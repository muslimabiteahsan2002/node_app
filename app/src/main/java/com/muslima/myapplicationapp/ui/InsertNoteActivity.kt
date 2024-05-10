package com.muslima.myapplicationapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.muslima.myapplicationapp.R
import com.muslima.myapplicationapp.databinding.ActivityInsertNoteBinding
import com.muslima.myapplicationapp.room_database.model.Notes
import com.muslima.myapplicationapp.view_model.NotesViewModel
import java.text.SimpleDateFormat
import java.util.Calendar


class InsertNoteActivity : AppCompatActivity() {
    private val binding: ActivityInsertNoteBinding by lazy {
        ActivityInsertNoteBinding.inflate(layoutInflater)
    }
    private var title = ""
    private var subtitle = ""
    private var notes = ""
    private var priority = "1"
    private lateinit var notesViewModel: NotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.greenPriority.setOnClickListener {
            binding.greenPriority.setImageResource(R.drawable.baseline_done_24)
            binding.yellowPriority.setImageResource(0)
            binding.redPriority.setImageResource(0)
            priority="1"
        }
        binding.yellowPriority.setOnClickListener {
            binding.greenPriority.setImageResource(0)
            binding.redPriority.setImageResource(0)
            binding.yellowPriority.setImageResource(R.drawable.baseline_done_24)
            priority="2"
        }
        binding.redPriority.setOnClickListener {
            binding.greenPriority.setImageResource(0)
            binding.yellowPriority.setImageResource(0)
            binding.redPriority.setImageResource(R.drawable.baseline_done_24)
            priority="3"
        }
        notesViewModel = ViewModelProvider(this)[NotesViewModel::class.java]
        binding.notesAddBtn.setOnClickListener {
            title = binding.notesTitle.text.toString()
            subtitle = binding.notesSubtitle.text.toString()
            notes = binding.notesData.text.toString()
            createNotes(title, subtitle, notes)
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun createNotes(title: String, subtitle: String, notes: String) {
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy")
        val dateTime = simpleDateFormat.format(calendar.time).toString()
        val notes1 = Notes()
        notes1.title = title
        notes1.subtitle = subtitle
        notes1.notes = notes
        notes1.date = dateTime
        notes1.priority = priority
        notesViewModel.insertNotes(notes1)
        Toast.makeText(this,"insert data",Toast.LENGTH_SHORT).show()
        finish()
    }
}