package com.muslima.myapplicationapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.muslima.myapplicationapp.R
import com.muslima.myapplicationapp.adapter.NotesAdapter.Companion.idKey
import com.muslima.myapplicationapp.adapter.NotesAdapter.Companion.notesKey
import com.muslima.myapplicationapp.adapter.NotesAdapter.Companion.priorityKey
import com.muslima.myapplicationapp.adapter.NotesAdapter.Companion.subtitleKey
import com.muslima.myapplicationapp.adapter.NotesAdapter.Companion.titleKey
import com.muslima.myapplicationapp.databinding.ActivityUpdateNoteBinding
import com.muslima.myapplicationapp.room_database.model.Notes
import com.muslima.myapplicationapp.view_model.NotesViewModel
import java.text.SimpleDateFormat
import java.util.Calendar


class UpdateNoteActivity : AppCompatActivity() {
    private val binding: ActivityUpdateNoteBinding by lazy {
        ActivityUpdateNoteBinding.inflate(layoutInflater)
    }
    private var idData = 0
    private var titleData: String? = null
    private var subtitleData: String? = null
    private var notesData: String? = null
    private var priorityData: String? = null
    private var priority = "1"
    private lateinit var notesViewModel: NotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        idData = intent.getIntExtra(idKey, 0)
        titleData = intent.getStringExtra(titleKey)
        subtitleData = intent.getStringExtra(subtitleKey)
        notesData = intent.getStringExtra(notesKey)
        priorityData = intent.getStringExtra(priorityKey)
        when (priorityData) {
            "1" -> {
                binding.greenPriority.setImageResource(R.drawable.baseline_done_24)
            }
            "2" -> {
                binding.yellowPriority.setImageResource(R.drawable.baseline_done_24)
            }
            "3" -> {
                binding.redPriority.setImageResource(R.drawable.baseline_done_24)
            }
        }
        notesViewModel = ViewModelProvider(this)[NotesViewModel::class.java]
        binding.greenPriority.setOnClickListener {
            binding.greenPriority.setImageResource(R.drawable.baseline_done_24)
            binding.yellowPriority.setImageResource(0)
            binding.redPriority.setImageResource(0)
            priority = "1"
        }
        binding.yellowPriority.setOnClickListener {
            binding.greenPriority.setImageResource(0)
            binding.yellowPriority.setImageResource(R.drawable.baseline_done_24)
            binding.redPriority.setImageResource(0)
            priority = "2"
        }
        binding.redPriority.setOnClickListener {
            binding.greenPriority.setImageResource(0)
            binding.yellowPriority.setImageResource(0)
            binding.redPriority.setImageResource(R.drawable.baseline_done_24)
            priority = "3"
        }
        binding.updateTitle.setText(titleData)
        binding.updateSubtitle.setText(subtitleData)
        binding.notesData.setText(notesData)
        binding.notesAddBtn.setOnClickListener {
            val title = binding.updateTitle.text.toString()
            val subtitle = binding.updateSubtitle.text.toString()
            val n=binding.notesData.text.toString()
            updateNotes(title, subtitle,n)
        }
    }



    @SuppressLint("SimpleDateFormat")
    private fun updateNotes(title: String, subtitle: String,notesData:String?) {
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy")
        val dateTime = simpleDateFormat.format(calendar.time).toString()
        val notesUpdate = Notes()
        notesUpdate.id=idData
        notesUpdate.title = title
        notesUpdate.subtitle = subtitle
        notesUpdate.date = dateTime
        if (notesData != null) {
            notesUpdate.notes=notesData
        }
        notesUpdate.priority = priority
        notesViewModel.updateNotes(notesUpdate)
        Toast.makeText(this, "update data", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.delete_menu, menu)
        return true
    }

    @SuppressLint("ResourceType")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.ic_delete){
            val sheetDialog = BottomSheetDialog( this@UpdateNoteActivity)
            val view = LayoutInflater.from(this@UpdateNoteActivity)
                .inflate(R.layout.detele_bottom_sheet, findViewById(R.id.bottomSheet))
            sheetDialog.setContentView(view)
            val yes = view.findViewById<TextView>(R.id.deleteYes)
            val no = view.findViewById<TextView>(R.id.deleteNo)
            yes.setOnClickListener {
                notesViewModel.deleteNotes(idData)
                Toast.makeText(this@UpdateNoteActivity, "delete Note", Toast.LENGTH_SHORT).show()
                finish()
            }
            no.setOnClickListener {
                sheetDialog.dismiss()
            }
            sheetDialog.show()
        }
        return true
    }
}