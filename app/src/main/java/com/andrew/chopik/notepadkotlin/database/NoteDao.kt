package com.andrew.chopik.notepadkotlin.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.andrew.chopik.notepadkotlin.model.Note

/**
 * Created by Andrew on 22.02.2018.
 */

@Dao
interface NoteDao {

    @get:Query("SELECT * FROM notes")
    val allNotes: LiveData<List<Note>>

    @Insert
    fun insert(note: Note)

    @Delete
    fun delete(note: Note)

    @Update
    fun update(note: Note)
}

