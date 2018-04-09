package com.andrew.chopik.notepadkotlin.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.andrew.chopik.notepadkotlin.model.Note

/**
 * Created by Andrew on 22.02.2018.
 */
@Database(entities = [Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
}