package com.example.coffeetracker.ui.history

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeetracker.CoffeeEvent
import com.example.coffeetracker.R

class HistoryListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<HistoryListAdapter.CoffeeViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var coffeeEvents = emptyList<CoffeeEvent>()

    inner class CoffeeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val coffeeItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return CoffeeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CoffeeViewHolder, position: Int) {
        val current = coffeeEvents[position]
        holder.coffeeItemView.text = current.timestamp.toString()
    }

    internal fun setCoffeeEvents(coffeeEvents: List<CoffeeEvent>) {
        this.coffeeEvents = coffeeEvents
        notifyDataSetChanged()
    }

    override fun getItemCount() = coffeeEvents.size
}