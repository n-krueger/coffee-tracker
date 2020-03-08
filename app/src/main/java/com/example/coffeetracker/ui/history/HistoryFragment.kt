package com.example.coffeetracker.ui.history

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeetracker.R

class HistoryFragment : Fragment() {
    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var _context: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        _context = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        historyViewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_history, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.recyclerView)
        val adapter = HistoryListAdapter(_context)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(_context)

        historyViewModel.allCoffeeEvents.observe(viewLifecycleOwner, Observer {
            adapter.setCoffeeEvents(it)
        })

        return root
    }
}
