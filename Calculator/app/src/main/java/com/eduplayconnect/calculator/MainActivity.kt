package com.eduplayconnect.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    
    private lateinit var textResult: TextView
    
    // Variables to store operands and pending operation
    private var operand1: Double? = null
    private var pendingOperation: String? = null
    private var newOperation = true
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        
        // Handle edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        // Initialize the result TextView
        textResult = findViewById(R.id.textResult)
        
        // Setup digit buttons (0-9)
        setupDigitButton(R.id.button0, "0")
        setupDigitButton(R.id.button1, "1")
        setupDigitButton(R.id.button2, "2")
        setupDigitButton(R.id.button3, "3")
        setupDigitButton(R.id.button4, "4")
        setupDigitButton(R.id.button5, "5")
        setupDigitButton(R.id.button6, "6")
        setupDigitButton(R.id.button7, "7")
        setupDigitButton(R.id.button8, "8")
        setupDigitButton(R.id.button9, "9")
        
        // Setup operation buttons
        setupOperationButton()
        
        // Setup clear button
        setupClearButton()
        
        // Setup equals button
        setupEqualsButton()
    }
    
    private fun setupDigitButton(buttonId: Int, digit: String) {
        val button = findViewById<Button>(buttonId)
        button.setOnClickListener {
            if (newOperation) {
                textResult.text = digit
                newOperation = false
            } else {
                // Append digit only if current text is not just "0"
                if (textResult.text.toString() == "0") {
                    textResult.text = digit
                } else {
                    textResult.text = textResult.text.toString() + digit
                }
            }
        }
    }
    
    private fun setupOperationButton() {
        val buttonPlus = findViewById<Button>(R.id.buttonPlus)
        buttonPlus.setOnClickListener {
            if (pendingOperation != null && !newOperation) {
                performPendingOperation()
            }
            
            // Store the current value as operand1
            val value = textResult.text.toString()
            if (value.isNotEmpty()) {
                operand1 = value.toDoubleOrNull()
                if (operand1 != null) {
                    pendingOperation = "+"
                    newOperation = true
                    Toast.makeText(this, "Toplama işlemi seçildi", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Geçersiz sayı formatı", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    
    private fun setupClearButton() {
        val buttonClear = findViewById<Button>(R.id.buttonClear)
        buttonClear.setOnClickListener {
            // Reset calculator state
            textResult.text = "0"
            operand1 = null
            pendingOperation = null
            newOperation = true
            
            // Show feedback to the user
            Toast.makeText(this, "Hesap makinesi sıfırlandı", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun setupEqualsButton() {
        val buttonEquals = findViewById<Button>(R.id.buttonEquals)
        buttonEquals.setOnClickListener {
            performPendingOperation()
        }
    }
    
    private fun performPendingOperation() {
        if (pendingOperation != null && operand1 != null) {
            val operand2 = textResult.text.toString().toDoubleOrNull() ?: 0.0
            
            val result = when (pendingOperation) {
                "+" -> operand1!! + operand2
                else -> Double.NaN
            }
            
            if (result.isNaN()) {
                Toast.makeText(this, "Geçersiz işlem", Toast.LENGTH_SHORT).show()
                return
            }
            
            if (result == result.toLong().toDouble()) {
                textResult.text = result.toLong().toString()
            } else {
                textResult.text = result.toString()
            }
            
            operand1 = result
            pendingOperation = null
            newOperation = true
        } else if (operand1 == null) {
            Toast.makeText(this, "Lütfen önce bir sayı girin", Toast.LENGTH_SHORT).show()
        }
    }
}