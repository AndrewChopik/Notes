package com.andrew.chopik.notepadkotlin.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Andrew on 22.02.2018.
 */
@Entity(tableName = "notes")
data class Note(var title: String,
                var text: String,
                @PrimaryKey(autoGenerate = true) var id: Int = 0) {
}