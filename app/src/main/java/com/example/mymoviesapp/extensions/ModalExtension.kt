package com.example.mymoviesapp.extensions

import android.app.Activity
import android.content.DialogInterface
import androidx.fragment.app.Fragment
import com.example.mymoviesapp.R
import com.example.mymoviesapp.domain.exception.ClientException
import com.example.mymoviesapp.domain.exception.NetworkException
import com.google.android.material.dialog.MaterialAlertDialogBuilder


fun Activity.showErrorModal(
    throwable: Throwable?,
    action: DialogInterface.OnClickListener?
) {
    val message = when (throwable) {
        is NetworkException -> getString(R.string.network_error)
        is ClientException -> getString(R.string.service_unavailable)
        else -> getString(R.string.generic_error_message)
    }
    val dialog = MaterialAlertDialogBuilder(this)
        .setTitle(getString(R.string.something_went_wrong))
        .setMessage(message)
        .setPositiveButton(getString(R.string.try_again), action)
        .setNeutralButton(getString(R.string.ok), null)
    dialog.show()
}

fun Fragment.showErrorModal(
    throwable: Throwable?,
    action: DialogInterface.OnClickListener?
) {
    val message = when (throwable) {
        is NetworkException -> getString(R.string.network_error)
        is ClientException -> getString(R.string.service_unavailable)
        else -> getString(R.string.generic_error_message)
    }
    val dialog = MaterialAlertDialogBuilder(requireContext())
        .setTitle(getString(R.string.something_went_wrong))
        .setMessage(message)
        .setPositiveButton(getString(R.string.try_again), action)
        .setNeutralButton(getString(R.string.ok), null)
    dialog.show()
}
