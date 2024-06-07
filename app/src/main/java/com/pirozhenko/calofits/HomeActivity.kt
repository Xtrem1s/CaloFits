package com.pirozhenko.calofits

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.pirozhenko.calofits.databinding.ActivityHomeBinding
import com.pirozhenko.calofits.databinding.ActivitySignInBinding
import com.qamar.curvedbottomnaviagtion.CurvedBottomNavigation

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        (this.application as MyApplication).trainCreateList.clear()

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        val bottomNavigation = findViewById<CurvedBottomNavigation>(R.id.bottomNavigation)
        bottomNavigation.add(
            CurvedBottomNavigation.Model(1, "Главная", R.drawable.ic_home)
        )
        bottomNavigation.add(
            CurvedBottomNavigation.Model(2, "Тренировка", R.drawable.ic_sports_bank)
        )
        bottomNavigation.add(
            CurvedBottomNavigation.Model(3, "Счетчик калорий", R.drawable.ic_food_bank)
        )
        bottomNavigation.add(
            CurvedBottomNavigation.Model(4, "Статистика", R.drawable.ic_leadbord)
        )
        bottomNavigation.setOnClickMenuListener {
            when(it.id){
                1 -> {
                    replaceFragment(HomeFragment())
                    binding.toolbar.title = "Главная"
                }
                2 -> {
                    replaceFragment(StartTrainFragment())
                    binding.toolbar.title = "План тренировок"

                }
                3 -> {
                    replaceFragment(FoodMenuFragment())
                    binding.toolbar.title = "Меню"
                }
                4 -> {
                    replaceFragment(StatisticsFragment())
                    binding.toolbar.title = "Статистика"
                }
            }
        }
        replaceFragment(HomeFragment())
        bottomNavigation.show(1)

        setSupportActionBar(binding.toolbar)
        val toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.nav_open, R.string.nav_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navigationDrawer.setNavigationItemSelectedListener(this)



    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_profile -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)

            }
            R.id.nav_base_of_food -> {
                val intent = Intent(this, BaseOfFoodActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_base_of_train -> {
                val intent = Intent(this, BaseOfTrainActivity::class.java)
                startActivity(intent)

            }
            R.id.nav_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_exit -> {
                val builder = AlertDialog.Builder(this, R.style.AlertDialogTheme)
                builder.setTitle("Выход")
                builder.setIcon(R.drawable.ic_action_warning)

                builder.setMessage("Вы уверены что хотите выйти?")
                builder.setPositiveButton("Да"){ dialog, which ->
                    auth.signOut()

                    val intent = Intent(this, SignInActivity::class.java)
                    startActivity(intent)

                    finish()
                }
                builder.setNegativeButton("Нет"){dialog, which ->
                    Toast.makeText(this, "Вы нажали нет", Toast.LENGTH_LONG).show()

                }
                builder.setNeutralButton("Отмена") {dialog, which ->
                    Toast.makeText(this, "Вы нажали отмена", Toast.LENGTH_LONG).show()
                }
                val dialog : AlertDialog = builder.create()

                dialog.show()
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK)
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK)
                dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(Color.BLACK)
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}