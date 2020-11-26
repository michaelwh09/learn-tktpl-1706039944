package id.ac.ui.cs.mobileprogramming.michaelwiryadinatahalim.lab_8.utils

import android.view.View

interface RecyclerViewOnClickListener<T> {
    fun onItemClicked(view: View, data: T)
}
