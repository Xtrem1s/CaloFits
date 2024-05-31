package com.pirozhenko.calofits

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {
//    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        var splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        auth = Firebase.auth
        hideSystemUI()
        //Начало блока отвечающего за анимацию названия и градиента
        val splashScreenView = findViewById<ConstraintLayout>(R.id.splashScreenView)
        val drawable: AnimationDrawable = splashScreenView.background as AnimationDrawable

        drawable.setEnterFadeDuration(1500)
        drawable.setExitFadeDuration(2000)
        drawable.start()

        val topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        val middleAnimation = AnimationUtils.loadAnimation(this, R.anim.middle_animation)
        val bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        val topView: TextView = findViewById(R.id.topTextView)
        topView.startAnimation(topAnimation)
        val middleView: TextView = findViewById(R.id.middleTextView)
        middleView.startAnimation(middleAnimation)
        val bottomView: TextView = findViewById(R.id.bottomTextView)
        bottomView.startAnimation(bottomAnimation)
        //Конец блока отвечающего за анимацию названия и градиента

        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
//            val user = auth.currentUser
//            if(user != null){
//                val intent = Intent(this, HomeActivity::class.java)
//                startActivity(intent)
//
//                finish()
//            }else{
//                val intent = Intent(this, SignInActivity::class.java)
//                startActivity(intent)
//
//                finish()
//            }
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()

        },3000)

    }
    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(
            window,
            window.decorView.findViewById(android.R.id.content)
        ).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())

            // When the screen is swiped up at the bottom
            // of the application, the navigationBar shall
            // appear for some time
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}