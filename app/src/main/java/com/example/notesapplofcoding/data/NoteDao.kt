package com.example.notesapplofcoding.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertNote(note: Note)


    @Delete
    suspend fun deleteNote(note: Note)


    @Query("SELECT * FROM Note ORDER BY noteId DESC")
    fun selectNotes(): Flow<List<Note>>

    @Query("SELECT * FROM Note WHERE noteTitle LIKE '%'||:searchQuery||'%'")
    fun searchInNotesTitle(searchQuery: String): Flow<List<Note>>


}