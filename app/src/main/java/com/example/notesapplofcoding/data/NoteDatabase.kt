package com.example.notesapplofcoding.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase: RoomDatabase() {
    abstract val noteDao: NoteDao



    companion object {
        var INSTANCE: NoteDatabase? = null


        fun getDatabaseInstance(context: Context): NoteDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    NoteDatabase::class.java,
                    "Notes_db"
                ).fallbackToDestructiveMigration().build()
            }
            return INSTANCE !!
        }


    }


}