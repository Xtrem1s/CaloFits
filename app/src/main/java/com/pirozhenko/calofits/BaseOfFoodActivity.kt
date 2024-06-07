package com.pirozhenko.calofits

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.SearchView
import com.pirozhenko.calofits.databinding.ActivityBaseOfFoodBinding

class BaseOfFoodActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBaseOfFoodBinding
    private lateinit var foodLV: ListView
    private lateinit var addBtn: View
    private lateinit var removeBtn: ImageView
    private lateinit var searchView: SearchView
    private lateinit var foodList: ArrayList<FoodClass>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseOfFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        searchView = findViewById(R.id.idSVFood)


        foodLV = findViewById(R.id.idLVFoodBase)
        foodLV.choiceMode = ListView.CHOICE_MODE_MULTIPLE

        addBtn = findViewById(R.id.fab_add)
        removeBtn = findViewById(R.id.imageView)
        removeBtn.visibility = View.INVISIBLE

        searchView = findViewById(R.id.idSVFood)
        foodList = (this.application as MyApplication).foodBaseListNew

        var foodChoiceList: ArrayList<FoodClass>? = ArrayList()

        var adapter = ArrayAdapter(
            this@BaseOfFoodActivity,
            android.R.layout.simple_list_item_1,
            foodList
        )

        var adapterChoice = ArrayAdapter(
            this@BaseOfFoodActivity,
            android.R.layout.simple_list_item_multiple_choice,
            foodList
        )

        adapter.filter.filter("")
        adapterChoice.filter.filter("")

        foodLV.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                for (food in foodList) {
                    if (food.name == query) {
                        adapter.filter.filter(query)
                        break
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })

        foodLV.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            if (foodChoiceList!!.size != 0) {
                val food = foodLV.getItemAtPosition(position) as FoodClass
                if (foodLV.isItemChecked(position)) {
                    if (food != null) {
                        if (!foodChoiceList.contains(food)) foodChoiceList!!.add(food)
                    }
                } else {
                    foodChoiceList!!.remove(food)
                    if (foodChoiceList.size == 0) {
                        foodLV.clearChoices()
                        adapter.filter.filter("")
                        adapterChoice.filter.filter("")
                        foodLV.adapter = adapter
                        searchView.visibility = View.VISIBLE
                        removeBtn.visibility = View.INVISIBLE
                    }
                }
            }
        }

        foodLV.onItemLongClickListener = AdapterView.OnItemLongClickListener { _, _, pos, _ ->
            if (foodChoiceList!!.size == 0) {
                if (!searchView.isIconified) searchView.onActionViewCollapsed()
                searchView.visibility = View.INVISIBLE
                removeBtn.visibility = View.VISIBLE
                val food = foodLV.getItemAtPosition(pos) as FoodClass
                if (food != null) {
                    foodLV.adapter = adapterChoice
                    foodLV.setItemChecked(adapterChoice.getPosition(food), true)
                    foodChoiceList!!.add(food)
                }
            }
            true
        }

        addBtn.setOnClickListener {
            val intent = Intent(this, CreateChangeFoodActivity::class.java)
            startActivity(intent)
            finish()
        }

        removeBtn.setOnClickListener {
            for (i in 0 until foodChoiceList!!.size) {
                foodList.remove(foodChoiceList!![i])
                adapter.remove(foodChoiceList!![i])
                adapterChoice.remove(foodChoiceList!![i])
            }
            foodLV.clearChoices()
            foodChoiceList!!.clear()
            foodLV.adapter = adapter
            adapter.filter.filter("")
            adapterChoice.filter.filter("")
            searchView.visibility = View.VISIBLE
            removeBtn.visibility = View.INVISIBLE
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu!!.setHeaderTitle("Select Option")
        menu.add(0, v!!.id, 0, "Call")
        menu.add(0, v.id, 1, "SMS")
        menu.add(0, v.id, 2, "Email")
        menu.add(0, v.id, 3, "WhatsApp")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val selectedItemOrder = item!!.order
        val selectedItemTitle = item.title
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val listPosition = info.position
        val name = foodList[listPosition]
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}
