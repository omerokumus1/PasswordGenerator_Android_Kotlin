package com.example.passwordgenerator_kotlin

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.example.passwordgenerator_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        setupUI()
    }

    private fun setupUI() {
//        setUpSwitches()
        setOnClickListener()
        setSliderListener()
    }

    private fun setSliderListener() {
        binding.slider.addOnChangeListener { _, value, _ ->
            binding.passwordLengthText.text = "LENGTH: ${value.toInt()}"
        }
    }

    private fun setOnClickListener() {
        binding.generateBtn.setOnClickListener {
            binding.run {
                passwordText.text = viewModel.generatePassword(
                    slider.value.toInt(),
                    includeNumbersSwitch.isChecked,
                    includeLettersSwitch.isChecked,
                    includeSymbolsSwitch.isChecked
                )
            }
        }
    }

    private fun setUpSwitches() {
        val switches = arrayOf(binding.includeNumbersSwitch)
        switches.forEach {
            it.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    it.trackTintList = ColorStateList(
                        arrayOf(intArrayOf(1)), intArrayOf(Color.parseColor("FF0C7BF5"))
                    )
                } else {
                    it.trackTintList = ColorStateList(
                        arrayOf(intArrayOf()), intArrayOf(Color.parseColor("0D1F4B"))
                    )
                }
            }
        }
    }
}