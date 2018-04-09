package com.andrew.chopik.notepadkotlin.model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.andrew.chopik.notepadkotlin.DEFAULT_ID
import javax.inject.Inject

/**
 * Created by Andrew on 22.02.2018.
 */
class NoteListViewModel @Inject constructor (application: Application, private val repository: NoteRepository) : AndroidViewModel(application) {

    val allNotes: LiveData<List<Note>> = repository.allNotes

    fun insert(note: Note) {
        repository.insert(note)
    }

    fun update(note: Note) {
        if (note.id != DEFAULT_ID) {
            repository.update(note)
        }
    }

    fun delete(note: Note) {
        if (note.id != DEFAULT_ID) {
            repository.delete(note)
        }
    }
}

class NoteListViewModelFactory @Inject constructor (private val application: Application, private val repository: NoteRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NoteListViewModel(application, repository) as T
    }
}