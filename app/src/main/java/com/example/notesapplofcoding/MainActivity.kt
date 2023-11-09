package com.example.notesapplofcoding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.notesapplofcoding.data.NoteDatabase
import com.example.notesapplofcoding.repositories.NotesRepository
import com.example.notesapplofcoding.viewmodel.NoteViewModel
import com.example.notesapplofcoding.viewmodel.provider_factory.NotesViewModelProvider

class MainActivity : AppCompatActivity() {
    val viewModel:NoteViewModel by lazy {
        val database = NoteDatabase.getDatabaseInstance(this)
        val notesRepository = NotesRepository(database)
        val notesProviderFactory = NotesViewModelProvider(notesRepository)
        ViewModelProvider(this, notesProviderFactory)[NoteViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}