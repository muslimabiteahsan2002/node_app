package com.muslima.myapplicationapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.muslima.myapplicationapp.R
import com.muslima.myapplicationapp.adapter.NotesAdapter
import com.muslima.myapplicationapp.databinding.ActivityShowSingleNoteBinding

class ShowSingleNoteActivity : AppCompatActivity() {
    private val binding: ActivityShowSingleNoteBinding by lazy {
        ActivityShowSingleNoteBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val title = intent.getStringExtra(NotesAdapter.titleKey)
        val subtitle = intent.getStringExtra(NotesAdapter.subtitleKey)
        val note = intent.getStringExtra(NotesAdapter.notesKey)
        val priority = intent.getStringExtra(NotesAdapter.priorityKey)
        binding.titleTv.text = title
        binding.subtitleTv.text = subtitle
        binding.noteTv.text = note
        when (priority) {
            "1" -> {
                binding.priority.setBackgroundResource(R.color.green)
            }
            "2" -> {
                binding.priority.setBackgroundResource(R.color.yellow)
            }
            "3" -> {
                binding.priority.setBackgroundResource(R.color.red)
            }
        }
    }
}