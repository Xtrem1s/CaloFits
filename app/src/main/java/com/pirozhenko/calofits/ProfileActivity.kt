package com.pirozhenko.calofits

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.pirozhenko.calofits.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var heightEditText: EditText
    private lateinit var weightEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var goalRadioGroup: RadioGroup
    private lateinit var loseWeightRadioButton: RadioButton
    private lateinit var gainWeightRadioButton: RadioButton
    private lateinit var saveButton: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        nameEditText = findViewById(R.id.nameEditText)
        heightEditText = findViewById(R.id.heightEditText)
        weightEditText = findViewById(R.id.weightEditText)
        ageEditText = findViewById(R.id.ageEditText)
        goalRadioGroup = findViewById(R.id.goalRadioGroup)
        loseWeightRadioButton = findViewById(R.id.loseWeightRadioButton)
        gainWeightRadioButton = findViewById(R.id.gainWeightRadioButton)
        saveButton = findViewById(R.id.saveButton)

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val height = heightEditText.text.toString()
            val weight = weightEditText.text.toString()
            val age = ageEditText.text.toString()
            val goal = when (goalRadioGroup.checkedRadioButtonId) {
                R.id.loseWeightRadioButton -> "Похудеть"
                R.id.gainWeightRadioButton -> "Масса"
                else -> ""
            }

            // Здесь можно сохранить данные пользователя в базу данных или обработать их другим образом
            Toast.makeText(this, "Данные сохранены:\nИмя: $name\nРост: $height\nВес: $weight\nВозраст: $age\nЦель: $goal", Toast.LENGTH_LONG).show()
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }


}