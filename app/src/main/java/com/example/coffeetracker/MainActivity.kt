package com.example.coffeetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import org.json.JSONObject
import java.io.File
import java.io.FileNotFoundException

class MainActivity : AppCompatActivity() {
    val persistentFileName = "coffeCount.json"
    val jsonCoffeeCountKey = "coffeCount"
    var coffeeCount = 0

    fun readCoffeeCount(): Int {
        try {
            val persistentFile = File(applicationContext.filesDir, persistentFileName)
            val persistedText = persistentFile.readText()
            val persistedJson = JSONObject(persistedText)
            return persistedJson.optInt(jsonCoffeeCountKey)
        }
        catch (exc: FileNotFoundException) {
            return 0
        }
    }

    fun writeCoffeeCount(coffeeCount: Int) {
        val json = JSONObject()
        json.put(jsonCoffeeCountKey, coffeeCount)
        val text = json.toString()
        val file =  File(applicationContext.filesDir, persistentFileName)
        file.writeText(text)
    }

    fun updateCoffeeCountDisplay(coffeeCount: Int) {
        val coffeeCountField = findViewById<EditText>(R.id.coffeCountField)
        coffeeCountField.setText(coffeeCount.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coffeeCount = readCoffeeCount()
        updateCoffeeCountDisplay(coffeeCount)
    }

    override fun onPause() {
        super.onPause()

        writeCoffeeCount(coffeeCount)
    }

    fun addCoffee(view: View) {
        coffeeCount++
        updateCoffeeCountDisplay(coffeeCount)
    }

    fun resetCoffeeCount(view: View) {
        coffeeCount = 0
        updateCoffeeCountDisplay(coffeeCount)
    }
}
