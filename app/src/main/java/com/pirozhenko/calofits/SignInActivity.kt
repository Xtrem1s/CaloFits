package com.pirozhenko.calofits

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.pirozhenko.calofits.databinding.ActivitySignInBinding
import com.pirozhenko.calofits.databinding.ActivitySignUpBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private  lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        auth = Firebase.auth

        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            if(checkAllField()){
                auth.signInWithEmailAndPassword(email, password). addOnCompleteListener{
                    if(it.isSuccessful){
                        Toast.makeText(this, "Успешный вход", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else{
                        Log.e("Ошибка: ", it.exception.toString())
                    }
                }
            }
        }

        binding.tvCreateAccount.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.tvForgotPassword.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun checkAllField(): Boolean {
        val email = binding.etEmail.text.toString()
        if(binding.etEmail.text.toString() == ""){
            binding.textInputLayoutEmail.error = "Это поле обзязательно для заполнения"
            return false
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.textInputLayoutEmail.error = "Проверте корректность почты"
            return false
        }

        if(binding.etPassword.text.toString() == ""){
            binding.textInputLayoutPassword.error = "Это поле обзязательно для заполнения"
            binding.textInputLayoutPassword.errorIconDrawable = null
            return false
        }
        if(binding.etPassword.length()<=6){
            binding.textInputLayoutPassword.error = "Длина пароля должна составлять не менее 8 символов"
            binding.textInputLayoutPassword.errorIconDrawable = null
            return false
        }
        return true
    }
}