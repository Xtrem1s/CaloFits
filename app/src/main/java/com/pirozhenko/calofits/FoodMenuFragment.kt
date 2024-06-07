package com.pirozhenko.calofits

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ExpandableListView
import android.widget.SimpleExpandableListAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val REQUEST_CODE_ADD_PRODUCT = 1

class FoodMenuFragment : Fragment() {
    private lateinit var nutritionInfoText: TextView
    private lateinit var expandableListView: ExpandableListView
    private lateinit var adapter: SimpleExpandableListAdapter
    private lateinit var adapter1: SimpleExpandableListAdapter
    private var param1: String? = null
    private var param2: String? = null
    private val groupDataList = ArrayList<Map<String, String>>()
    private val childDataList = ArrayList<ArrayList<Map<String, String>>>()

    private val test1 = arrayListOf("Завтрак")
    private val test2 = arrayListOf(
        "Яйца (ккал: 158, белки: 12г, жиры: 11г, углеводы: 0.7г)",
        "Банан (ккал: 89, белки: 1.5г, жиры: 0.1г, углеводы: 21г)",
        "Овсянка (ккал: 102, белки: 3.2г, жиры: 4.1г, углеводы: 14.2г)",
        "Добавить продукт..."
    )
    private val test3 = arrayListOf("Обед")
    private val test4 = arrayListOf(
        "Варенная курица (ккал: 100, белки: 15г, жиры: 5г, углеводы: 2.3г)",
        "Гречка (ккал: 100, белки: 10г, жиры: 2г, углеводы: 27г)",
        "Добавить продукт..."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_food_menu, container, false)
        nutritionInfoText = view.findViewById(R.id.nutrition_info_text)

        val calories = 1800
        val protein = 155
        val fat = 43
        val carbs = 150
        val nutritionText = "Ккал: $calories, Белки: $protein, Жиры: $fat, Углеводы: $carbs"
        nutritionInfoText.text = nutritionText

        expandableListView = view.findViewById(R.id.expListView)

        setupExpandableListView()

        val fabAdd = view.findViewById<FloatingActionButton>(R.id.fab_add2)
        fabAdd.setOnClickListener {
            showAddMealDialog()
        }

        return view
    }

    private fun setupExpandableListView() {
        var map: MutableMap<String, String>
        for (group in test1) {
            map = HashMap()
            map["groupName"] = group
            groupDataList.add(map)
        }

        val groupFrom = arrayOf("groupName")
        val groupTo = intArrayOf(android.R.id.text1)
        val childFrom = arrayOf("monthName")
        val childTo = intArrayOf(android.R.id.text1)

        val childDataItemList = ArrayList<Map<String, String>>()
        for (item in test2) {
            map = HashMap()
            map["monthName"] = item
            childDataItemList.add(map)
        }

            ///
        var map2: MutableMap<String, String>
        for (group in test3) {
            map2 = HashMap()
            map2["groupName"] = group
            groupDataList.add(map2)
        }

        val groupFrom1 = arrayOf("groupName")
        val groupTo1 = intArrayOf(android.R.id.text1)
        val childFrom1 = arrayOf("monthName")
        val childTo1 = intArrayOf(android.R.id.text1)

        val childDataItemList1 = ArrayList<Map<String, String>>()
        for (item in test4) {
            map = HashMap()
            map["monthName"] = item
            childDataItemList1.add(map)
        }

        ///
        childDataList.add(childDataItemList)
        childDataList.add(childDataItemList1)

        adapter = SimpleExpandableListAdapter(
            activity, groupDataList,
            android.R.layout.simple_expandable_list_item_1, groupFrom,
            groupTo, childDataList, android.R.layout.simple_list_item_1,
            childFrom, childTo
        )
        expandableListView.setAdapter(adapter)

        expandableListView.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            val childText = (adapter.getChild(groupPosition, childPosition) as Map<*, *>)["monthName"] as String
            if (childText == "Добавить продукт...") {
                val intent = Intent(activity, BaseOfFoodActivity::class.java)
                intent.putExtra("mealName", test1[groupPosition])
                startActivityForResult(intent, REQUEST_CODE_ADD_PRODUCT)
            }
            false
        }
        adapter1 = SimpleExpandableListAdapter(
            activity, groupDataList,
            android.R.layout.simple_expandable_list_item_1, groupFrom1,
            groupTo1, childDataList, android.R.layout.simple_list_item_1,
            childFrom1, childTo1
        )
        expandableListView.setAdapter(adapter1)

        expandableListView.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            val childText = (adapter1.getChild(groupPosition, childPosition) as Map<*, *>)["monthName"] as String
            if (childText == "Добавить продукт...") {
                val intent = Intent(activity, BaseOfFoodActivity::class.java)
                intent.putExtra("mealName", test3[groupPosition])
                startActivityForResult(intent, REQUEST_CODE_ADD_PRODUCT)
            }
            false
        }
    }

    private fun showAddMealDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Добавить новый прием пищи")

        val input = EditText(requireContext())
        input.hint = "Введите название приема пищи"
        builder.setView(input)

        builder.setPositiveButton("Добавить") { dialog, which ->
            val mealName = input.text.toString()
            if (mealName.isNotEmpty()) {
                addNewGroup(mealName)
            }
        }
        builder.setNegativeButton("Отмена") { dialog, which -> dialog.cancel() }

        builder.show()
    }

    private fun addNewGroup(mealName: String) {
        val newGroupMap = HashMap<String, String>()
        newGroupMap["groupName"] = mealName

        val newChildDataItemList = ArrayList<Map<String, String>>()
        val defaultChildText = "Добавить продукт..."
        val newChildMap = HashMap<String, String>()
        newChildMap["monthName"] = defaultChildText
        newChildDataItemList.add(newChildMap)

        groupDataList.add(newGroupMap)
        childDataList.add(newChildDataItemList)

        adapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_PRODUCT && resultCode == AppCompatActivity.RESULT_OK) {
            val mealName = data?.getStringExtra("mealName")
            val selectedProducts = data?.getSerializableExtra("selectedProducts") as? ArrayList<FoodClass>
            if (mealName != null && selectedProducts != null) {
                updateMealWithProducts(mealName, selectedProducts)
            }
        }
    }

    private fun updateMealWithProducts(mealName: String, selectedProducts: ArrayList<FoodClass>) {
        val groupPosition = test1.indexOf(mealName)
        if (groupPosition != -1) {
            val currentChildData = childDataList[groupPosition]
            currentChildData.clear()
            for (product in selectedProducts) {
                val map = HashMap<String, String>()
                map["monthName"] = product.toString()
                currentChildData.add(map)
            }
            val map = HashMap<String, String>()
            map["monthName"] = "Добавить продукт..."
            currentChildData.add(map)
            adapter.notifyDataSetChanged()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FoodMenuFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
