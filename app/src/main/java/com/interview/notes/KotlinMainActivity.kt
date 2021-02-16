package com.interview.notes

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class KotlinMainActivity : AppCompatActivity() {

    /*
    *************************************************************************************************************************
        NOTE: BEFORE MAKING CHANGES HERE, MAKE SURE THAT YOU CHANGE ACTIVITY TO KotlinMainActivity IN AndroidManifest.xml
    *************************************************************************************************************************
    */

    private lateinit var notesStore: NotesStore

    private lateinit var notesList: RecyclerView
    private lateinit var addNoteButton: Button

    private val notesAdapter: NotesAdapter = NotesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notesStore = (application as MainApplication).notesStore

        notesList = findViewById(R.id.notes_list)
        addNoteButton = findViewById(R.id.btn_add_note)
        notesList.layoutManager = LinearLayoutManager(this)
        notesList.adapter = notesAdapter
        notesAdapter.setNotes(notesStore.notes)

        addNoteButton.setOnClickListener {
            val addNoteActivityIntent = Intent(this, KotlinAddNoteActivity::class.java)
            startActivity(addNoteActivityIntent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh notes in the RecyclerView
        notesAdapter.setNotes(notesStore.notes)
    }


    private class NotesAdapter : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

        private val notes: MutableList<Note> = ArrayList()

        fun setNotes(freshNotes: List<Note>) {
            notes.clear()
            notes.addAll(freshNotes)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val noteTitleView = holder.itemView.findViewById<TextView>(R.id.note_title)
            noteTitleView.text = notes[position].title
        }

        override fun getItemCount(): Int {
            return notes.size
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    }
}