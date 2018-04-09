package com.andrew.chopik.notepadkotlin.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AppCompatDialogFragment
import android.view.Menu
import android.view.MenuItem
import com.andrew.chopik.notepadkotlin.*
import com.andrew.chopik.notepadkotlin.fragment.DeleteDataDialog
import com.andrew.chopik.notepadkotlin.fragment.SaveDataDialog
import kotlinx.android.synthetic.main.activity_edit_note.*

class EditNoteActivity : AppCompatActivity(), SaveDataDialog.SaveDialogListener, DeleteDataDialog.DeleteDialogListener {

    companion object {
        fun getIntent(context: Context, id: Int = DEFAULT_ID, title: String = DEFAULT_LABEL, text: String = DEFAULT_LABEL) =
                Intent(context, EditNoteActivity::class.java).apply {
                    putExtra(EXTRA_REPLY_ID, id)
                    putExtra(EXTRA_REPLY_TITLE, title)
                    putExtra(EXTRA_REPLY_TEXT, text)
                    setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                }
    }

    private var noteId = DEFAULT_ID
    private var noteTitle: String = DEFAULT_LABEL
    private var noteText: String = DEFAULT_LABEL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)

        setSupportActionBar(toolbarEditNote)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        if (intent.extras != null) {
            noteId = intent.getIntExtra(EXTRA_REPLY_ID, DEFAULT_ID)
            noteTitle = intent.getStringExtra(EXTRA_REPLY_TITLE)
            noteText = intent.getStringExtra(EXTRA_REPLY_TEXT)

            noteTitleET.setText(noteTitle)
            noteTextET.setText(noteText)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.edit_note_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item_save -> saveData()
            R.id.menu_item_delete ->  showDeleteDialog()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        checkChanges()
    }

    private fun checkChanges() {
        if (noteTitleET.text.isEmpty() && noteTextET.text.isEmpty()) {
            setResult(Activity.RESULT_CANCELED)
            super.onBackPressed()
        } else {
            val title = noteTitleET.text.toString()
            val text = noteTextET.text.toString()
            if (title == noteTitle && text == noteText) {
                super.onBackPressed()
            } else {
                showSaveDialog()
            }
        }
    }

    private fun showSaveDialog() {
        val dialog = SaveDataDialog()
        dialog.show(supportFragmentManager, "SaveDataDialog")
    }

    override fun onSaveDialogPositiveClick(dialog: AppCompatDialogFragment) {
        saveData()
    }

    override fun onSaveDialogNegativeClick(dialog: AppCompatDialogFragment) {
        super.onBackPressed()
    }

    override fun onSaveDialogNeutralClick(dialog: AppCompatDialogFragment) {}

    private fun showDeleteDialog() {
        val dialog = DeleteDataDialog()
        dialog.show(supportFragmentManager, "DeleteDataDialog")
    }

    override fun onDeleteDialogPositiveClick(dialog: AppCompatDialogFragment) {}

    override fun onDeleteDialogNegativeClick(dialog: AppCompatDialogFragment) {
        val title = noteTitleET.text.toString()
        val text = noteTextET.text.toString()

        saveChanges(RESULT_DELETE, title, text, noteId)
    }

    private fun saveData() {
        val title = noteTitleET.text.toString()
        val text = noteTextET.text.toString()

        if (noteId != DEFAULT_ID) {
            saveChanges(RESULT_UPDATE, title, text, noteId)
        } else {
            saveChanges(RESULT_INSERT, title, text)
        }
    }

    private fun saveChanges(result: Int, title: String, text: String, id: Int = DEFAULT_ID) {
        val replyIntent = Intent().apply {
            putExtra(EXTRA_REPLY_TITLE, title)
            putExtra(EXTRA_REPLY_TEXT, text)
            putExtra(EXTRA_REPLY_ID, id)
        }

        setResult(result, replyIntent)
        finish()
    }
}
