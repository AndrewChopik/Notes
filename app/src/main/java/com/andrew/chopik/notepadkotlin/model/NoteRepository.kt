package com.andrew.chopik.notepadkotlin.model

import android.os.AsyncTask
import com.andrew.chopik.notepadkotlin.database.AppDatabase
import com.andrew.chopik.notepadkotlin.database.NoteDao
import javax.inject.Inject

/**
 * Created by Andrew on 22.02.2018.
 */
class NoteRepository @Inject constructor (database: AppDatabase) {

    private val noteDao = database.noteDao()
    val allNotes = noteDao.allNotes

    fun insert(note: Note) {
        InsertAsyncTask(noteDao).execute(note)
    }

    fun update(note: Note) {
        UpdateAsyncTask(noteDao).execute(note)
    }

    fun delete(note: Note) {
        DeleteAsyncTask(noteDao).execute(note)
    }

    private class InsertAsyncTask internal constructor(private val asyncTaskDao: NoteDao) : AsyncTask<Note, Void, Void>() {
        override fun doInBackground(vararg notes: Note): Void? {
            asyncTaskDao.insert(notes[0])
            return null
        }
    }

    private class UpdateAsyncTask internal constructor(private val asyncTaskDao: NoteDao) : AsyncTask<Note, Void, Void>() {
        override fun doInBackground(vararg notes: Note): Void? {
            asyncTaskDao.update(notes[0])
            return null
        }
    }

    private class DeleteAsyncTask internal constructor(private val asyncTaskDao: NoteDao) : AsyncTask<Note, Void, Void>() {
        override fun doInBackground(vararg notes: Note): Void? {
            asyncTaskDao.delete(notes[0])
            return null
        }
    }
}