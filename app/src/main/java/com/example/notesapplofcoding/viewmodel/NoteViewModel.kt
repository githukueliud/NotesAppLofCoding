package com.example.notesapplofcoding.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapplofcoding.data.Note
import com.example.notesapplofcoding.repositories.NotesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class NoteViewModel(
    private val notesRepository: NotesRepository
): ViewModel() {
    val note = notesRepository.getNotes()
    private val _searchResult = MutableStateFlow<List<Note>>(emptyList())
    val searchResult: StateFlow<List<Note>> = _searchResult

    fun upsertNote(note: Note) = viewModelScope.launch {
        notesRepository.upsertNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        notesRepository.deleteNote(note)
    }

    fun searchNote(searchQuery: String) = viewModelScope.launch{
        notesRepository.searchNote(searchQuery).collect {notesLsit ->
            _searchResult.emit(notesLsit)
        }
    }
}