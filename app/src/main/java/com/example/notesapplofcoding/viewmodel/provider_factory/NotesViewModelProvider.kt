package com.example.notesapplofcoding.viewmodel.provider_factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notesapplofcoding.repositories.NotesRepository
import com.example.notesapplofcoding.viewmodel.NoteViewModel

class NotesViewModelProvider(
    private val notesRepository: NotesRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(notesRepository) as T
    }

}