package com.example.unitconverterapplication

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var valueEditText: EditText
    private lateinit var fromUnitSpinner: Spinner
    private lateinit var toUnitSpinner: Spinner
    private lateinit var convertButton: Button
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        valueEditText = findViewById(R.id.valueEditText)
        fromUnitSpinner = findViewById(R.id.fromUnitSpinner)
        toUnitSpinner = findViewById(R.id.toUnitSpinner)
        convertButton = findViewById(R.id.convertButton)
        resultTextView = findViewById(R.id.resultTextView)


        val units = arrayOf("Meters", "Feet", "Inches", "Centimeters")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        fromUnitSpinner.adapter = adapter
        toUnitSpinner.adapter = adapter

        convertButton.setOnClickListener {
            convertUnits()
        }
    }

    private fun convertUnits() {
        val inputValue = valueEditText.text.toString().toDoubleOrNull()
        if (inputValue != null) {
            val fromUnit = fromUnitSpinner.selectedItem.toString()
            val toUnit = toUnitSpinner.selectedItem.toString()
            val result = performConversion(inputValue, fromUnit, toUnit)
            resultTextView.text = "$inputValue $fromUnit = $result $toUnit"
        } else {
            resultTextView.text = "Invalid input."
        }
    }

    private fun performConversion(value: Double, fromUnit: String, toUnit: String): Double {

        return when {
            fromUnit == "Meters" && toUnit == "Feet" -> value * 3.28084
            fromUnit == "Feet" && toUnit == "Meters" -> value * 0.3048
            fromUnit == "Meters" && toUnit == "Inches" -> value * 39.3701
            fromUnit == "Inches" && toUnit == "Meters" -> value * 0.0254
            fromUnit == "Centimeters" && toUnit == "Inches" -> value * 0.393701
            fromUnit == "Inches" && toUnit == "Centimeters" -> value * 2.54
            fromUnit == "Meters" && toUnit == "Centimeters" -> value * 100
            fromUnit == "Centimeters" && toUnit == "Meters" -> value * 0.01
            fromUnit == "Feet" && toUnit == "Inches" -> value * 12
            fromUnit == "Feet" && toUnit == "Centimeters" -> value * 30.48
            fromUnit == "Inches" && toUnit == "Feet" -> value * 0.0833
            fromUnit == "Centimeters" && toUnit == "Feet" -> value * 0.0328084
            else -> value
        }
    }
}
