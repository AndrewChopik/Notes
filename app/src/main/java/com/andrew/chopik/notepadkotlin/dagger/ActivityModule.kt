package com.andrew.chopik.notepadkotlin.dagger

import android.app.Application
import android.arch.lifecycle.ViewModelProviders
import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import com.andrew.chopik.notepadkotlin.database.AppDatabase
import com.andrew.chopik.notepadkotlin.model.NoteListViewModel
import com.andrew.chopik.notepadkotlin.model.NoteListViewModelFactory
import com.andrew.chopik.notepadkotlin.model.NoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Andrew on 02.03.2018.
 */
@Module
class ActivityModule() {
    @Provides
    @Singleton
    fun provideDatabase(application: Application) =
            Room.databaseBuilder(application, AppDatabase::class.java, "notes").build()

    @Provides
    @Singleton
    fun provideRepository(database: AppDatabase) = NoteRepository(database)

    @Provides
    @Singleton
    fun provideViewModelFactory(application: Application, repository: NoteRepository) =
            NoteListViewModelFactory(application, repository)

    @Provides
    @Singleton
    fun provideViewModel(activity: AppCompatActivity, viewModelFactory: NoteListViewModelFactory) =
            ViewModelProviders.of(activity, viewModelFactory).get(NoteListViewModel::class.java)
}