package com.andrew.chopik.notepadkotlin.fragment

import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatDialogFragment
import com.andrew.chopik.notepadkotlin.R

/**
 * Created by Andrew on 22.02.2018.
 */
class SaveDataDialog : AppCompatDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialogListener = context

        return if (dialogListener is SaveDialogListener) {
            AlertDialog.Builder(activity)
                    .setMessage(R.string.dialog_message_save)
                    .setPositiveButton(R.string.save, { dialog, id ->
                        dialogListener.onSaveDialogPositiveClick(this)
                    })
                    .setNeutralButton(R.string.cancel, { dialog, i ->
                        dialogListener.onSaveDialogNeutralClick(this)
                    })
                    .setNegativeButton(R.string.delete, { dialog, id ->
                        dialogListener.onSaveDialogNegativeClick(this)
                    })
                    .setCancelable(false)
                    .create()
        } else {
            super.onCreateDialog(savedInstanceState)
        }

    }

    interface SaveDialogListener {

        fun onSaveDialogPositiveClick(dialog: AppCompatDialogFragment)

        fun onSaveDialogNegativeClick(dialog: AppCompatDialogFragment)

        fun onSaveDialogNeutralClick(dialog: AppCompatDialogFragment)
    }
}
