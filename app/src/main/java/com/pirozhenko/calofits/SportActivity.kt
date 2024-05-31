package com.pirozhenko.calofits

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.SearchView
import androidx.appcompat.widget.Toolbar
import java.text.SimpleDateFormat
import java.util.Date

class SportActivity : AppCompatActivity() {
    private lateinit var addBtn: View
    private lateinit var trainLW: ListView
    private lateinit var searchView: SearchView

    //    private lateinit var backBtn: ImageView
    private lateinit var removeBtn: ImageView
    private lateinit var saveTrain: Button
    private lateinit var toolbarSportActivity: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sport)

        addBtn = findViewById(R.id.fab_add_train)
//        backBtn = findViewById(R.id.imageView4)
        toolbarSportActivity = findViewById(R.id.toolbar2)


        trainLW = findViewById(R.id.idLVTrain)
        trainLW.choiceMode = ListView.CHOICE_MODE_MULTIPLE

        searchView = findViewById(R.id.idSVTrain)
        removeBtn = findViewById(R.id.imageView)
        saveTrain = findViewById(R.id.button)

        var trainExercisesListNew = (this.application as MyApplication).trainExercisesListNew


        var adapter = ArrayAdapter<ExerciseTrainClass>(
            this,
            android.R.layout.simple_list_item_activated_1,
            trainExercisesListNew
        )

        trainLW.adapter = adapter

        addBtn.setOnClickListener {
            val intent = Intent(this, AddTrainActivity::class.java)
            startActivity(intent)
            finish()
        }

        toolbarSportActivity.setNavigationOnClickListener(View.OnClickListener {
//            trainList.remove(trainNew)
            trainExercisesListNew.clear()
            val intent = Intent(this, HomeActivity::class.java) // Можно по идеи убрать!!!
            startActivity(intent)
            finish()
//            trainCreateList.clear()
        })
//        backBtn.setOnClickListener {
//            val intent = Intent(this, HomeActivity::class.java)
//            startActivity(intent)
//            finish()
//            trainCreateList.clear()
//        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                for (train in trainExercisesListNew) {
                    if (train.name == query)
                        adapter.filter.filter(query)

//                    if (trainNewExercise.contains(query)) {
//                    adapter.filter.filter(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                adapter.filter.filter(newText)
                return false
            }
        })

        removeBtn.setOnClickListener {
//            adapter = ArrayAdapter<String>(
//                this,
//                android.R.layout.simple_list_item_activated_1,
//                trainNewExercise
//            )
            adapter.filter.filter("")

            trainLW.adapter = adapter
            for (i in trainExercisesListNew) {
                if (trainLW.isItemChecked(adapter.getPosition(i))) {
                    adapter.remove(i)
                    Log.v("test", "remove")
                }
            }
            trainLW.clearChoices()
//            trainNewExercise.clear()
            adapter.notifyDataSetChanged()
        }

        saveTrain.setOnClickListener {
//            activity?.let {
//                val intent = Intent(it, AddTrainActivity::class.java)
//                it.startActivity(intent)
//            }

            val alertDialog: android.app.AlertDialog.Builder =
                android.app.AlertDialog.Builder(this)
            alertDialog.setTitle("Тренировка записана")

            var result: String = "Упражнения:\n"
            for (trainTmp in trainExercisesListNew!!) {
                result = result + " - " + trainTmp + ";\n"
            }
            alertDialog.setMessage(result)
            alertDialog.setPositiveButton(
                "Ок"
            ) { _, _ ->
//                trainExercisesListNew.clear()
                var trainList = (this.application as MyApplication).trainClassListNew
                val sdf = SimpleDateFormat("dd.MM.yyyy")
                val currentDate = sdf.format(Date())
                var trainNew = TrainClass(currentDate.toString())
//                trainNew.exercisesList.addAll(trainExercisesListNew)
                for (i in trainExercisesListNew) {
                    val newPropertyTrain = PropertyTrain()
//                    newPropertyTrain.dateExercise = currentDate
//                    if (i.approaches != null)
                    newPropertyTrain.approachesDate = i.approaches
//                    if (i.repetitions != null)
                    newPropertyTrain.repetitionsDate = i.repetitions
//                    if (i)
                    newPropertyTrain.distanceDate = i.distance
                    newPropertyTrain.timeDate = i.time

                    newPropertyTrain.weightDate = i.weight

                    trainNew.exercisesList.put(i,newPropertyTrain)
//                    i.propertyTrainList.add(newPropertyTrain)
                }

                trainExercisesListNew.clear()
                trainList.add(trainNew)
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
            alertDialog.setNegativeButton(
                "Отмена"
            ) { dialog, _ ->
                dialog.dismiss()
            }
            alertDialog.show()
        }
    }
}