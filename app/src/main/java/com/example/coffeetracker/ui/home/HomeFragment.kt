package com.example.coffeetracker.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.coffeetracker.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val textCoffeeCount: TextView = root.findViewById(R.id.textCoffeeCount)
        homeViewModel.coffeeCountToday.observe(viewLifecycleOwner, Observer {
            textCoffeeCount.text = it.toString()
        })

        val fab: FloatingActionButton = root.findViewById(R.id.floatingActionButton)
        fab.setOnClickListener { homeViewModel.addCoffee() }

        return root
    }
}
