package com.pirozhenko.calofits

//import android.R

//import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.pirozhenko.calofits.databinding.ActivityBaseOfTrainBinding


class BaseOfTrainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBaseOfTrainBinding
    private lateinit var trainLW: ListView
    private lateinit var addBtn: View
    private lateinit var removeBtn: ImageView
    private lateinit var searchView: SearchView
    private lateinit var trainList: ArrayList<ExerciseTrainClass>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseOfTrainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {

            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        trainLW = findViewById(R.id.idLVBase)
//        trainLW.setOnItemLongClickListener()
        trainLW.choiceMode = ListView.CHOICE_MODE_MULTIPLE

        addBtn = findViewById(R.id.fab_add)
        removeBtn = findViewById(R.id.imageView)
        removeBtn.visibility = View.INVISIBLE
//        removeBtn.hide
        searchView = findViewById(R.id.idSV)

        trainList = (this.application as MyApplication).trainBaseListNew
//        trainList.addAll((this.application as MyApplication).trainBaseListNew)

        var trainChoiceList: ArrayList<ExerciseTrainClass>? = ArrayList()

        var adapter = ArrayAdapter(
            this@BaseOfTrainActivity,
            android.R.layout.simple_list_item_1,
            trainList
        )

        var adapterChoice = ArrayAdapter(
            this@BaseOfTrainActivity,
            android.R.layout.simple_list_item_multiple_choice,
            trainList
        )

        adapter.filter.filter("")
        adapter.filter.filter(null)

        adapterChoice.filter.filter("")


        trainLW.adapter = adapter
//        Log.v("test", "${trainLW.count}")
//        Log.v("testadapter", "${(adapter.getItem(0) as ExerciseTrain).name}")
//        Log.v("testadapter", "${adapter.count}")

        registerForContextMenu(trainLW)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                for (train in trainList) {
                    if (train.name == query) {
//                    Log.v("query", "${query}")

//                        if
//                        if (trainChoiceList!!.size != 0) {
//                            adapterChoice.filter.filter(query)
//                            trainLW.clearChoices()
//                            for (idTrainLW in 0..<trainLW.count) {
//                                val trainTemp = trainLW.getItemAtPosition(idTrainLW) as ExerciseTrain
//                                if (trainChoiceList.contains(trainTemp))
//                                    trainLW.setItemChecked(idTrainLW, true)
//                            }
//                        }
//                        else
                            adapter.filter.filter(query)
//                        adapter.notifyDataSetChanged()
//                        Log.v("test", "${adapter.count}")
//                        trainLW.setItemChecked()
//                        for (train in trainLW.count)
//                        Log.v("test", "${(trainLW.getItemAtPosition(0) as ExerciseTrain).name}")
//                        Log.v("test", "${trainLW.count}")
//                        Log.v("testadapter", "${(adapterChoice.getItem(0) as ExerciseTrain).name}")
//                        Log.v("testadapter", "${adapterChoice.count}")

//                        val f: Filter = adapter.filter
//            f.filter("") {
//                trainLW.setItemChecked(1, true)
                //                    val count2 = adapter.count
                //                    mainText.setText("Count is: $count")
                //                    adapter.add("hellraiser")
//            }


                        break
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                for (train in trainList) {
//                    if (train.name == newText) {
//                        if (trainChoiceList!!.size != 0) {
//                            adapterChoice.filter.filter(newText)
//                            adapterChoice.notifyDataSetChanged()
//                            trainLW.clearChoices()
//                            trainLW.adapter = adapterChoice
//                            trainLW.adapter.count
//                            for (idTrainLW in 0..<trainLW.count) {
//                                Log.v("testid", "${idTrainLW}")

//                                val trainTemp =
//                                    trainLW.getItemAtPosition(idTrainLW) as ExerciseTrain
//                                Log.v("testidExercise", "${trainTemp.idExercise}")

//                                if (trainChoiceList.contains(trainTemp))
//                                    trainLW.setItemChecked(idTrainLW, true)
//                            }
//                        } else
//                            adapter.filter.filter(newText)
//                adapter.notifyDataSetChanged()
//                adapter.
//                adapter.notifyDataSetInvalidated()
//                trainLW.fil
//                adapter.setNotifyOnChange(true)
//                        Log.v("test", "${(trainLW.getItemAtPosition(0) as ExerciseTrain).name}")
//                        Log.v("test", "${trainLW.count}")
//                Log.v("testadapter", "${(adapterChoice.getItem(0) as ExerciseTrain).name}")
//                Log.v("testadapter", "${ trainLW.adapter.count}")

//                    }
//                    break
//                }
                return false
            }
        })

        trainLW.onItemClickListener = OnItemClickListener { l, v, position, _ ->
            if (trainChoiceList!!.size != 0) {

//                val train = adapterChoice.getItem(position)
                val train = trainLW.getItemAtPosition(position) as ExerciseTrainClass
                if (trainLW.isItemChecked(position)) {
                    if (train != null) {

                        if (!trainChoiceList.contains(train)) trainChoiceList!!.add(train)
                   }
                } else {
                    trainChoiceList!!.remove(train)

                    if (trainChoiceList!!.size == 0) {
//                        trainLW.setItemChecked(adapterChoice.getPosition(train), false)
                        trainLW.clearChoices()
                        adapter.filter.filter("")
                        adapterChoice.filter.filter("")
                        trainLW.adapter = adapter
                        searchView.visibility = View.VISIBLE
                        removeBtn.visibility = View.INVISIBLE

                    }
                }
            }
            else
                openContextMenu(l)
        }


        trainLW.onItemLongClickListener =
            OnItemLongClickListener { arg0, arg1, pos, id -> // TODO Auto-generated method stub
                if (trainChoiceList!!.size == 0) {
//                    searchView.isIconified = true
                    if (!searchView.isIconified) searchView.onActionViewCollapsed()
                    searchView.visibility = View.INVISIBLE
                    removeBtn.visibility = View.VISIBLE
                    val train = trainLW.getItemAtPosition(pos) as ExerciseTrainClass

                    if (train != null) {
                        trainLW.adapter = adapterChoice
                        trainLW.setItemChecked(adapterChoice.getPosition(train), true)
                        trainChoiceList!!.add(train)
                    }
                }
                true
            }

//        registerForContextMenu(trainLW)

        addBtn.setOnClickListener {

            val intent = Intent(this, CreateChangeTrainActivity::class.java)
            startActivity(intent)
            finish()
////            searchView.setQuery("", false)
////            adapter.notifyDataSetInvalidated()
////            adapter.
//
////            adapter.addAll(trainList)
////            adapter = ArrayAdapter<String>(     // Это не есть норм, исправить!!!
////                this@BaseOfTrainActivity,
////                android.R.layout.simple_list_item_activated_1,
////                trainList
////            )
////            trainLW.adapter = adapter
//            val alertDialog: android.app.AlertDialog.Builder =
//                android.app.AlertDialog.Builder(this)
//            alertDialog.setTitle("Упражнение")
//
//            val input = EditText(this)
//            val lp = LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT
//            )
//            input.layoutParams = lp
//            alertDialog.setView(input)
//            alertDialog.setPositiveButton(
//                "Ок"
//            ) { _, _ ->
//                val item = input.text.toString()
//                if (item.isNotEmpty()) {
////                    searchView.isIconified = true
////                    adapter.resetValue
////                    trainLW.isTextFilterEnabled = false
////                    adapter.filter.filter()
////                    adapter.autofillOptions
//                    searchView.onActionViewCollapsed()
//
//                    adapter.clear()
//                    (this.application as MyApplication).trainBaseList.add(item)
//                    trainList.clear()
//                    trainList.addAll((this.application as MyApplication).trainBaseListNew)
////                    Log.v("long clicked", "test = ${trainList.size}")
//
////                    adapter.addAll(trainList)
//                    adapter.filter.filter("")
//
////                    adapter.notifyDataSetChanged()
////                    trainLW.adapter = adapter
//                }
//            }
//            alertDialog.show()
        }

        removeBtn.setOnClickListener {
//            adapter = ArrayAdapter<String>(   // Это не есть норм, исправить!!!
//                this@BaseOfTrainActivity,
//                android.R.layout.simple_list_item_activated_1,
//                trainList
//            )
//            trainLW.adapter = adapter
            for (i in 0 until trainChoiceList!!.size) {
                trainList.remove(trainChoiceList!![i])
                adapter.remove(trainChoiceList!![i])
                adapterChoice.remove(trainChoiceList!![i])
            }
            trainLW.clearChoices()
//            adapter.remove()
//            adapterChoice.clear()
            trainChoiceList!!.clear()
//            adapterChoice.clear()
//            adapterChoice.notifyDataSetChanged()
//            adapter.notifyDataSetChanged()

            trainLW.adapter = adapter

            adapter.filter.filter("")
            adapterChoice.filter.filter("")
//            val f: Filter = adapter.filter
//            f.filter("", object : FilterListener() {
//                fun onFilterComplete(count: Int) {
//                    val count2 = adapter.count
//                    mainText.setText("Count is: $count")
//                    adapter.add("hellraiser")
//                }
//            })
//            trainLW.adapter = adapter
            searchView.visibility = View.VISIBLE
            removeBtn.visibility = View.INVISIBLE

        }
    }
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)


        //Set Header of Context Menu
        menu!!.setHeaderTitle("Select Option")

        menu.add(0, v!!.id, 0, "Открыть")
        menu.add(0, v.id, 1, "Удалить")
        menu.add(0, v.id, 2, "Выделить")
        menu.add(0, v.id, 3, "Отмена")


        /*
             menu.add get 4 Parameters

             1. grouId if you want to add multiple Group than for every group Id is Diffrent Here
                we have only One Group so We take 0(Zero) as GroupId

             2.v.id is our Item Id

             3. Set Order of Our Item(Position Of Item) if you Change order of Call to 1 and SMS to 0
                  than in Menu SMS Display First.

             4. Title to Display on Context menu
         */

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        //Get Order of Selected Item
        val selectedItemOrder = item!!.order

        //Get Title Of Selected Item
        val selectedItemTitle = item.title

        //To get Name of Person Click on ListView
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val listPosition = info.position
        val train = trainList[listPosition] as ExerciseTrainClass

        if (selectedItemOrder == 0){

        }
        else if (selectedItemOrder == 1){
        }
        else if (selectedItemOrder == 2){
        }
        else
            closeContextMenu()

//        Toast.makeText(this@BaseOfTrainActivity, " " + selectedItemTitle + " " + name, Toast.LENGTH_LONG).show()

        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}