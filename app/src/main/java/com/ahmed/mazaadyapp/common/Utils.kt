package com.ahmed.mazaadyapp.common

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ahmed.mazaadyapp.R

object Utils {

    fun showErrorDialog(message: String,context:Context, retryFun: () -> Unit = { }) {
        MaterialAlertDialogBuilder(context)
            .setMessage("Error message: $message")
            .setTitle("An error has occurred")
            .setPositiveButton("Retry!") { _, _ ->
                retryFun.invoke()
            }
            .setNegativeButton("Dismiss!", null)
            .show().run {
                getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.red
                    )
                )
                getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.red
                    )
                )
            }
    }
}