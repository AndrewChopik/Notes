package com.andrew.chopik.notepadkotlin.fragment

import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatDialogFragment
import com.andrew.chopik.notepadkotlin.R

/**
 * Created by Andrew on 22.02.2018.
 */
class DeleteDataDialog : AppCompatDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialogListener = context

        return if (dialogListener is DeleteDialogListener) {
            AlertDialog.Builder(activity)
                    .setMessage(R.string.dialog_message_delete)
                    .setPositiveButton(R.string.cancel, { dialog, id ->
                        dialogListener.onDeleteDialogPositiveClick(this)
                    })
                    .setNegativeButton(R.string.delete, { dialog, id ->
                        dialogListener.onDeleteDialogNegativeClick(this)
                    })
                    .setCancelable(false)
                    .create()
        } else {
            super.onCreateDialog(savedInstanceState)
        }
    }

    interface DeleteDialogListener {

        fun onDeleteDialogPositiveClick(dialog: AppCompatDialogFragment)

        fun onDeleteDialogNegativeClick(dialog: AppCompatDialogFragment)
    }
}
