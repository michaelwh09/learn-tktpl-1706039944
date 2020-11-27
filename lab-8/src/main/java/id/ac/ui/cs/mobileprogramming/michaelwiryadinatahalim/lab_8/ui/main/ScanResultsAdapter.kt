package id.ac.ui.cs.mobileprogramming.michaelwiryadinatahalim.lab_8.ui.main

import android.net.wifi.ScanResult
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.michaelwiryadinatahalim.lab_8.R
import id.ac.ui.cs.mobileprogramming.michaelwiryadinatahalim.lab_8.utils.RecyclerViewOnClickListener

class ScanResultsAdapter(private val results: List<ScanResult>, private val listener: RecyclerViewOnClickListener<ScanResult>):
RecyclerView.Adapter<ScanResultsAdapter.ViewHolder>(){

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textView)

        fun bindTo(wifi: ScanResult, itemClickListener: RecyclerViewOnClickListener<ScanResult>) {
            textView.text = wifi.SSID
            textView.setOnClickListener {
                itemClickListener.onItemClicked(itemView, wifi)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.text_row_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(results[position], listener)
    }

    override fun getItemCount(): Int {
        return results.size
    }
}
