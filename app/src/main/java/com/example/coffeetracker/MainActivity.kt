package com.example.coffeetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    var coffeeCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coffeeCount = 0
    }

    fun addCoffee(view: View) {
        coffeeCount++
        val coffeeCountField = findViewById<EditText>(R.id.coffeCountField)
        coffeeCountField.setText(coffeeCount.toString())
    }
}
