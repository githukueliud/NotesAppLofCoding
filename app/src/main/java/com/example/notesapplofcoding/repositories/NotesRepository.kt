package com.example.notesapplofcoding.repositories

import com.example.notesapplofcoding.data.Note
import com.example.notesapplofcoding.data.NoteDatabase

class NotesRepository(
    noteDatabase: NoteDatabase
) {
    val noteDao = noteDatabase.noteDao


    suspend fun upsertNote(note: Note) = noteDao.upsertNote(note)

    suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)


    fun getNotes() = noteDao.selectNotes()


    fun searchNote(searchQuery: String) = noteDao.searchInNotesTitle(searchQuery)

}