package com.example.mymoviesapp.base

import android.content.Context
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetFragment : BottomSheetDialogFragment() {
    private var listener: BottomSheetEvents? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = if (context is BottomSheetEvents) context else throw RuntimeException()
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    protected fun dismissWithArguments(arguments: Map<String, Any>) {
        listener?.onBottomSheetClose(arguments)
        dismiss()
    }
}