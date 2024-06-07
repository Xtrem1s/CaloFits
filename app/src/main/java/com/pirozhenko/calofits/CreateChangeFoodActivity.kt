package com.pirozhenko.calofits

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pirozhenko.calofits.databinding.ActivityCreateChangeFoodBinding

class CreateChangeFoodActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateChangeFoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateChangeFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        binding.button2.setOnClickListener {
            val name = binding.foodNameEditText.text.toString()
            val description = binding.foodDescriptionEditText.text.toString()
            val calories = binding.caloriesEditText.text.toString().toFloatOrNull()
            val proteins = binding.proteinsEditText.text.toString().toFloatOrNull()
            val fats = binding.fatsEditText.text.toString().toFloatOrNull()
            val carbs = binding.carbsEditText.text.toString().toFloatOrNull()

            val food = FoodClass(name, description).apply {
                this.calories = calories
                this.proteins = proteins
                this.fats = fats
                this.carbohydrates = carbs
            }

            (application as MyApplication).foodBaseListNew.add(food)
            finish()
        }

        binding.button3.setOnClickListener {
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
