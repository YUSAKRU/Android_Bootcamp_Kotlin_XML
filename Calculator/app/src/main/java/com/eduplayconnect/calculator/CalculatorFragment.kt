package com.eduplayconnect.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class CalculatorFragment : Fragment() {
    
    private lateinit var textResult: TextView
    private lateinit var textOperation: TextView
    
    // Variables to store operands and pending operation
    private var operand1: Double? = null
    private var pendingOperation: String? = null
    private var newOperation = true
    private var lastOperationText = ""
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculator, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Initialize the result and operation TextViews
        textResult = view.findViewById(R.id.textResult)
        textOperation = view.findViewById(R.id.textOperation)
        
        // Setup digit buttons (0-9)
        setupDigitButton(view, R.id.button0, "0")
        setupDigitButton(view, R.id.button1, "1")
        setupDigitButton(view, R.id.button2, "2")
        setupDigitButton(view, R.id.button3, "3")
        setupDigitButton(view, R.id.button4, "4")
        setupDigitButton(view, R.id.button5, "5")
        setupDigitButton(view, R.id.button6, "6")
        setupDigitButton(view, R.id.button7, "7")
        setupDigitButton(view, R.id.button8, "8")
        setupDigitButton(view, R.id.button9, "9")
        
        // Setup decimal button
        setupDecimalButton(view)
        
        // Setup operation buttons
        setupOperationButtons(view)
        
        // Setup clear button
        setupClearButton(view)
        
        // Setup equals button
        setupEqualsButton(view)
        
        // Setup +/- button
        setupPlusMinusButton(view)
        
        // Setup % button
        setupPercentButton(view)
        
        // Setup () button (parentheses)
        setupParenthesesButton(view)
    }
    
    private fun setupDigitButton(view: View, buttonId: Int, digit: String) {
        val button = view.findViewById<Button>(buttonId)
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
    
    private fun setupDecimalButton(view: View) {
        val buttonDecimal = view.findViewById<Button>(R.id.buttonDecimal)
        buttonDecimal.setOnClickListener {
            val currentText = textResult.text.toString()
            
            if (newOperation) {
                textResult.text = "0."
                newOperation = false
            } else if (!currentText.contains(".")) {
                // Only add decimal if one doesn't already exist
                textResult.text = "$currentText."
            }
        }
    }
    
    private fun setupOperationButtons(view: View) {
        // Setup plus button
        val buttonPlus = view.findViewById<Button>(R.id.buttonPlus)
        buttonPlus.setOnClickListener {
            setupOperation("+")
        }
        
        // Setup minus button
        val buttonMinus = view.findViewById<Button>(R.id.buttonMinus)
        buttonMinus.setOnClickListener {
            setupOperation("-")
        }
        
        // Setup multiply button
        val buttonMultiply = view.findViewById<Button>(R.id.buttonMultiply)
        buttonMultiply.setOnClickListener {
            setupOperation("*")
        }
        
        // Setup divide button
        val buttonDivide = view.findViewById<Button>(R.id.buttonDivide)
        buttonDivide.setOnClickListener {
            setupOperation("/")
        }
    }
    
    private fun setupOperation(operation: String) {
        if (pendingOperation != null && !newOperation) {
            performPendingOperation()
        }
        
        // Store the current value as operand1
        val value = textResult.text.toString()
        if (value.isNotEmpty()) {
            operand1 = value.toDoubleOrNull()
            if (operand1 != null) {
                pendingOperation = operation
                newOperation = true
                
                // Update operation display
                lastOperationText = value
                val operationSymbol = when (operation) {
                    "+" -> "+"
                    "-" -> "-"
                    "*" -> "x"
                    "/" -> "/"
                    else -> operation
                }
                textOperation.text = "$value $operationSymbol"
            } else {
                Toast.makeText(context, "Geçersiz sayı formatı", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private fun setupClearButton(view: View) {
        val buttonClear = view.findViewById<Button>(R.id.buttonClear)
        buttonClear.setOnClickListener {
            // Reset calculator state
            textResult.text = "0"
            textOperation.text = ""
            operand1 = null
            pendingOperation = null
            newOperation = true
            lastOperationText = ""
        }
    }
    
    private fun setupEqualsButton(view: View) {
        val buttonEquals = view.findViewById<Button>(R.id.buttonEquals)
        buttonEquals.setOnClickListener {
            if (performPendingOperation()) {
                // Clear the operation text after successful calculation
                textOperation.text = ""
                lastOperationText = ""
                pendingOperation = null
            }
        }
    }
    
    private fun setupPlusMinusButton(view: View) {
        val buttonPlusMinus = view.findViewById<Button>(R.id.buttonPlusMinus)
        buttonPlusMinus.setOnClickListener {
            val currentValue = textResult.text.toString()
            if (currentValue != "0") {
                if (currentValue.startsWith("-")) {
                    textResult.text = currentValue.substring(1)
                } else {
                    textResult.text = "-$currentValue"
                }
            }
        }
    }
    
    private fun setupPercentButton(view: View) {
        val buttonPercent = view.findViewById<Button>(R.id.buttonPercent)
        buttonPercent.setOnClickListener {
            val currentValue = textResult.text.toString().toDoubleOrNull()
            if (currentValue != null) {
                val result = currentValue / 100.0
                if (result == result.toLong().toDouble()) {
                    textResult.text = result.toLong().toString()
                } else {
                    textResult.text = result.toString()
                }
                newOperation = true
            }
        }
    }
    
    private fun setupParenthesesButton(view: View) {
        // This is a placeholder for parentheses functionality
        // In a real calculator, this would require expression parsing
        val buttonParentheses = view.findViewById<Button>(R.id.buttonParentheses)
        buttonParentheses.setOnClickListener {
            Toast.makeText(context, "Parantez işlevi henüz uygulanmadı", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun performPendingOperation(): Boolean {
        if (pendingOperation != null && operand1 != null) {
            val operand2 = textResult.text.toString().toDoubleOrNull() ?: 0.0
            
            // Update operation display
            val operationSymbol = when (pendingOperation) {
                "+" -> "+"
                "-" -> "-"
                "*" -> "x"
                "/" -> "/"
                else -> pendingOperation
            }
            textOperation.text = "$lastOperationText $operationSymbol $operand2 ="
            
            val result = when (pendingOperation) {
                "+" -> operand1!! + operand2
                "-" -> operand1!! - operand2
                "*" -> operand1!! * operand2
                "/" -> if (operand2 != 0.0) operand1!! / operand2 else Double.NaN
                else -> Double.NaN
            }
            
            if (result.isNaN() || result.isInfinite()) {
                Toast.makeText(context, "Geçersiz işlem", Toast.LENGTH_SHORT).show()
                return false
            }
            
            // Format result - show whole numbers without decimal part
            if (result == result.toLong().toDouble()) {
                textResult.text = result.toLong().toString()
            } else {
                textResult.text = result.toString()
            }
            
            operand1 = result
            newOperation = true
            return true
        } else if (operand1 == null) {
            Toast.makeText(context, "Lütfen önce bir sayı girin", Toast.LENGTH_SHORT).show()
            return false
        }
        return false
    }
    
    companion object {
        fun newInstance() = CalculatorFragment()
    }
} 