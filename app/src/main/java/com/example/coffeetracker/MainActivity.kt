package com.example.coffeetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.threeten.bp.OffsetDateTime

class MainActivity : AppCompatActivity() {
    private lateinit var coffeeViewModel: CoffeeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = CoffeeListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        coffeeViewModel = ViewModelProvider(this).get(CoffeeViewModel::class.java)
        coffeeViewModel.allCoffeeEvents.observe(this, Observer {
            adapter.setCoffeeEvents(it)
        })
    }

    fun addCoffeeEvent(view: View) {
        val coffeeEvent = CoffeeEvent(OffsetDateTime.now(), "americano")
        coffeeViewModel.insert(coffeeEvent)
    }
}
