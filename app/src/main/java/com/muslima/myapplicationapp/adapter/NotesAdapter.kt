package com.muslima.myapplicationapp.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.muslima.myapplicationapp.R
import com.muslima.myapplicationapp.databinding.ItemNotesBinding
import com.muslima.myapplicationapp.databinding.ShowDialogBinding
import com.muslima.myapplicationapp.room_database.model.Notes
import com.muslima.myapplicationapp.ui.MainActivity
import com.muslima.myapplicationapp.ui.ShowSingleNoteActivity
import com.muslima.myapplicationapp.ui.UpdateNoteActivity

class NotesAdapter(private val mainActivity: MainActivity, private var notes: List<Notes>) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    class NotesViewHolder(val binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = ItemNotesBinding.inflate(LayoutInflater.from(mainActivity), parent, false)
        return NotesViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun searchNotes(filteredName: List<Notes>) {
        notes = filteredName
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = notes[position]
        with(holder.binding) {
            notesTitle.text = note.title
            notesSubTitle.text = note.subtitle
            notesDate.text = note.date
            notesItem.text = note.notes
            when (note.priority) {
                "1" -> {
                    notesPriority.setBackgroundResource(R.drawable.green_shape)
                }

                "2" -> {
                    notesPriority.setBackgroundResource(R.drawable.yellow_shape)
                }

                "3" -> {
                    notesPriority.setBackgroundResource(R.drawable.red_shape)
                }
            }
        }
        holder.itemView.setOnClickListener {
            val builder = AlertDialog.Builder(mainActivity)
            val view = ShowDialogBinding.inflate(LayoutInflater.from(mainActivity))
            builder.setView(view.root)
            val a = builder.create()
            view.updateBtn.setOnClickListener {
                val intentUpdateData = Intent(mainActivity, UpdateNoteActivity::class.java)
                intentUpdateData.putExtra(idKey, note.id)
                intentUpdateData.putExtra(titleKey, note.title)
                intentUpdateData.putExtra(subtitleKey, note.subtitle)
                intentUpdateData.putExtra(notesKey, note.notes)
                intentUpdateData.putExtra(priorityKey, note.priority)
                mainActivity.startActivity(intentUpdateData)
                a.dismiss()
            }
            view.singleBtn.setOnClickListener {
                val intentData = Intent(mainActivity, ShowSingleNoteActivity::class.java)
                intentData.putExtra(titleKey, note.title)
                intentData.putExtra(subtitleKey, note.subtitle)
                intentData.putExtra(notesKey, note.notes)
                intentData.putExtra(priorityKey, note.priority)
                mainActivity.startActivity(intentData)
                a.dismiss()
            }
            a.show()

        }
    }

    companion object {
        const val idKey = "id"
        const val titleKey = "title"
        const val subtitleKey = "subtitle"
        const val notesKey = "notes"
        const val priorityKey = "priority"
    }
}