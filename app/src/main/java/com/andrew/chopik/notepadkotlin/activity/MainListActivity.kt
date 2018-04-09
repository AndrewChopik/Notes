package com.andrew.chopik.notepadkotlin.activity

import android.app.Activity
import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.andrew.chopik.notepadkotlin.*
import com.andrew.chopik.notepadkotlin.adapter.NoteListAdapter
import com.andrew.chopik.notepadkotlin.dagger.ActivityComponent
import com.andrew.chopik.notepadkotlin.dagger.DaggerActivityComponent
import com.andrew.chopik.notepadkotlin.model.Note
import com.andrew.chopik.notepadkotlin.model.NoteListViewModel
import kotlinx.android.synthetic.main.activity_main_list.*
import javax.inject.Inject

class MainListActivity : AppCompatActivity() {

    private val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent.builder()
                .activity(this)
                .application(application)
                .build()
    }

    @Inject
    lateinit var viewModel: NoteListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_list)

        activityComponent.inject(this)

        setSupportActionBar(toolbarList)

        floatingActionButton.setOnClickListener {
            val intent = EditNoteActivity.getIntent(this)
            startActivityForResult(intent, REQUEST_CODE)
        }

        recyclerView.layoutManager = LinearLayoutManager(this).apply {
            reverseLayout = true
            stackFromEnd = true
        }

        val listAdapter = NoteListAdapter(object : NoteListAdapter.ItemClickListener {
            override fun onItemClicked(viewHolder: NoteListAdapter.ViewHolder) {
                val note = viewModel.allNotes.value!![viewHolder.adapterPosition]
                val intent = EditNoteActivity.getIntent(this@MainListActivity, note.id, note.title, note.text)
                startActivityForResult(intent, REQUEST_CODE)
            }
        })
        recyclerView.adapter = listAdapter

        viewModel.allNotes.observe(this, Observer<List<Note>> { notes ->
            listAdapter.setNotes(notes ?: emptyList())
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE) {

            if (resultCode == Activity.RESULT_CANCELED) {
                return
            }

            val note = Note(data?.getStringExtra(EXTRA_REPLY_TITLE) ?: DEFAULT_LABEL,
                    data?.getStringExtra(EXTRA_REPLY_TEXT) ?: DEFAULT_LABEL)
            val id = data?.getIntExtra(EXTRA_REPLY_ID, DEFAULT_ID) ?: DEFAULT_ID

            when (resultCode) {
                RESULT_INSERT -> viewModel.insert(note)
                RESULT_UPDATE -> viewModel.update(note.apply { this.id = id })
                RESULT_DELETE -> viewModel.delete(note.apply { this.id = id })
            }
        }
    }
}

