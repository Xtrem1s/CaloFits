package com.pirozhenko.calofits

//import android.R
//import android.annotation.SuppressLint
//import android.annotation.SuppressLint
//import android.R
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity


class AddTrainActivity : AppCompatActivity() {

//    private lateinit var backSportFragment: ImageView
    private lateinit var trainLW: ListView
    private lateinit var searchView: SearchView
    private lateinit var toolBarAddTrain: androidx.appcompat.widget.Toolbar

//    val context: Context = this

    //    @SuppressLint("ResourceType")
//    @SuppressLint("ResourceType", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_train)


//        backSportFragment = findViewById(R.id.imageView4)
        trainLW = findViewById(R.id.AddTrainLW)


        toolBarAddTrain = findViewById(R.id.toolbar2)
//        supportActionBar!!.se(toolBarAddTrain)
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//    toolBarAddTrain.navigationIcon = resources.getDrawable(R.attr.homeAsUpIndicator);
//        toolbar.navigationIcon = getDrawableFromAttribute(R.attr.homeAsUpIndicator)

//        toolBarAddTrain.setNavigationOnClickListener(View.OnClickListener { onBackPressed() })
        toolBarAddTrain.setNavigationOnClickListener(View.OnClickListener {
            val intent = Intent(this, SportActivity::class.java)
            startActivity(intent)
            finish()
        })
//        mActionBar.setNavigationIcon(resources.getDrawable(R.drawable.ic_action_back))
//        mActionBar.setNavigationOnClickListener(View.OnClickListener {
            //What to do on back clicked
//        })


        searchView = findViewById(R.id.AddTrainSW)
        val trainBaseList = (this.application as MyApplication).trainBaseListNew;
//        val trainClassListNew = (this.application as MyApplication).trainClassListNew;
        val trainExercisesListNew = (this.application as MyApplication).trainExercisesListNew

        val adapter = ArrayAdapter<ExerciseTrainClass>(
            this,
            android.R.layout.simple_list_item_1,
            trainBaseList
        )

        trainLW.adapter = adapter

//        backSportFragment.setOnClickListener {
//            val intent = Intent(this, SportActivity::class.java)
//            startActivity(intent)
//            finish()
//        }

        trainLW.onItemClickListener = OnItemClickListener { parent, v, position, id ->
            val selectedTrain = parent.getItemAtPosition(position) as ExerciseTrainClass

            val alertDialog: android.app.AlertDialog.Builder =
                android.app.AlertDialog.Builder(this)
            val result: String = selectedTrain.name

            alertDialog.setTitle("Введите информацию по упражнению '" + result + "':")
//            val result: String = "Упражнение: $selectedTrain"
            val layoutinflater = LayoutInflater.from(this)
            val addTrainAlertDialog: View = layoutinflater.inflate(R.layout.add_train_alert_dialog, null)
//            alertDialog.setMessage(result)
            val train_approaches: EditText = addTrainAlertDialog.findViewById<EditText>(R.id.train_approaches)
            val train_repetitions: EditText = addTrainAlertDialog.findViewById<EditText>(R.id.train_repetitions)
            val train_distance: EditText =  addTrainAlertDialog.findViewById<EditText>(R.id.train_distance)
            val train_time: EditText = addTrainAlertDialog.findViewById<EditText>(R.id.train_time)
            val train_weight: EditText = addTrainAlertDialog.findViewById<EditText>(R.id.train_weight)

            if (selectedTrain.approaches == null)
                train_approaches.visibility = View.GONE
            if (selectedTrain.repetitions == null)
                train_repetitions.visibility = View.GONE
            if (selectedTrain.distance == null)
                train_distance.visibility = View.GONE
            if (selectedTrain.time == null)
                train_time.visibility = View.GONE
            if (selectedTrain.weight == null)
                train_weight.visibility = View.GONE


            alertDialog.setView(addTrainAlertDialog)
            alertDialog.setPositiveButton(
                "Ок"
            ) { _, _ ->
//                val

//                selectedTrain.approaches = train_approaches.text.toString().toInt()
//                val newPropertyTrain = PropertyTrain()
                if (selectedTrain.approaches != null && train_approaches.text.toString()!= "")
                    selectedTrain.approaches = train_approaches.text.toString().toInt()
                if (selectedTrain.repetitions != null && train_repetitions.text.toString()!= "")
                    selectedTrain.repetitions = train_repetitions.text.toString().toInt()
                if (selectedTrain.distance != null && train_distance.text.toString()!= "")
                    selectedTrain.distance = train_distance.text.toString().toInt()
                if (selectedTrain.time != null && train_time.text.toString()!= "")
                    selectedTrain.time  = train_time.text.toString().toInt()
                if (selectedTrain.weight != null && train_weight.text.toString()!= "")
                    selectedTrain.weight  = train_weight.text.toString().toInt()

//                selectedTrain.propertyTrainList.add(newPropertyTrain)
                trainExercisesListNew.add(selectedTrain)
                val intent = Intent(this, SportActivity::class.java)
                startActivity(intent)
                finish()
            }
            alertDialog.setNegativeButton("Отмена"
            ) { dialog, _ ->
                dialog.dismiss()
            }
            alertDialog.show();
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
//                if (trainBaseList.contains(query)) {
                for (train in trainBaseList) {
                    if (train.name == query)
                   adapter.filter.filter(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                adapter.filter.filter(newText)
                return false
            }
        })
    }
}