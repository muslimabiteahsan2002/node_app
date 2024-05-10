package com.muslima.myapplicationapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.muslima.myapplicationapp.R
import com.muslima.myapplicationapp.adapter.NotesAdapter
import com.muslima.myapplicationapp.databinding.ActivityMainBinding
import com.muslima.myapplicationapp.room_database.model.Notes
import com.muslima.myapplicationapp.view_model.NotesViewModel
import java.util.Locale


class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var adapter: NotesAdapter
    private lateinit var notesViewModel: NotesViewModel
    private lateinit var showNotesRcv: RecyclerView
    private lateinit var filterAllNotesList: List<Notes>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setFilter()
        showNotesRcv = binding.showNotesRcv
        notesViewModel = ViewModelProvider(this)[NotesViewModel::class.java]
        binding.notesAddBtn.setOnClickListener {
            startActivity(Intent(this, InsertNoteActivity::class.java))
        }
        notesViewModel.showAllNotes.observe(this) {
            setAdapter(it)
            filterAllNotesList = it
        }
    }

    private fun setFilter() {
        binding.noFilter.setOnClickListener {
            loadData(0)
            binding.noFilter.setBackgroundResource(R.drawable.filter_selected_shape)
            binding.highToLow.setBackgroundResource(R.drawable.filter_un_shape)
            binding.lowToHigh.setBackgroundResource(R.drawable.filter_un_shape)
        }
        binding.highToLow.setOnClickListener {
            loadData(1)
            binding.noFilter.setBackgroundResource(R.drawable.filter_un_shape)
            binding.highToLow.setBackgroundResource(R.drawable.filter_selected_shape)
            binding.lowToHigh.setBackgroundResource(R.drawable.filter_un_shape)
        }
        binding.lowToHigh.setOnClickListener {
            loadData(2)
            binding.noFilter.setBackgroundResource(R.drawable.filter_un_shape)
            binding.highToLow.setBackgroundResource(R.drawable.filter_un_shape)
            binding.lowToHigh.setBackgroundResource(R.drawable.filter_selected_shape)
        }
    }

    private fun loadData(i: Int) {
        when (i) {
            0 -> {
                notesViewModel.showAllNotes.observe(this) {
                    setAdapter(it)
                    filterAllNotesList = it
                }
            }

            1 -> {
                notesViewModel.highToLow.observe(this) {
                    setAdapter(it)
                    filterAllNotesList = it
                }
            }

            2 -> {
                notesViewModel.lowToHigh.observe(this) {
                    setAdapter(it)
                    filterAllNotesList = it
                }
            }
        }
    }

    private fun setAdapter(notes: List<Notes>) {
        adapter = NotesAdapter(this@MainActivity, notes)
        showNotesRcv.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        showNotesRcv.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_notes, menu)
        val menuItem = menu!!.findItem(R.id.app_bar_search)

        val searchView = menuItem.actionView as SearchView

        searchView.queryHint = "here notes"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                setSearch(newText)
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    private fun setSearch(query: String?) {
        if (query !== null) {
            val filterList = ArrayList<Notes>()
            for (i in filterAllNotesList) {
                if (i.title.lowercase(Locale.ROOT)
                        .contains(query) || i.subtitle.lowercase(Locale.ROOT).contains(query)
                ) {
                    filterList.add(i)
                }
            }
            if (filterList.isEmpty()) {
                Toast.makeText(this@MainActivity, "No Notes found", Toast.LENGTH_LONG).show()
            } else {
                adapter.searchNotes(filterList)
            }
        }
    }

}