package com.pirozhenko.calofits

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager


class CreateChangeFoodActivity : AppCompatActivity() {
    private lateinit var toolbarCreateChangeFoodActivity: Toolbar
    private lateinit var buttonSave: Button
    private lateinit var buttonCancel: Button

    private lateinit var newFood: FoodClass


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_change_food)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        clearAllPreferenceValue()
//        changeInputTypePreference()

        buttonCancel = findViewById(R.id.button3)
        buttonCancel.setOnClickListener {
            clearAllPreferenceValue()
            val intent = Intent(this, BaseOfFoodActivity::class.java) // Можно по идеи убрать!!!
            startActivity(intent)
            finish()
        }
        buttonSave = findViewById(R.id.button2)
        buttonSave.setOnClickListener {
//           val name_food: EditTextPreference = findPreference
//            val sharedPrefs: SharedPreferences = getSharedPreferences("settings", 0)
//            val editor: SharedPreferences.Editor = sharedPrefs.edit()
//            editor.putString("name_food", "")
//            editor.apply()
//            editor.apply()
//            clearAllPreferenceValue()
//            Log.v("Салам", "Алейкум")
            var nameFood: String = ""
            var descriptionFood: String = ""
            var proteins: String? = null
            var fats: String? = null
            var carbohydrates: String? = null
            var calories: String? = null
            for (elem in foodListPreference) {
                if (elem.key == "name_food")
                    nameFood = ((elem as EditTextPreference).text.toString())
                else if (elem.key == "description_train")
                    descriptionFood = ((elem as EditTextPreference).text.toString())
                else if (elem.key == "calories_food")
                    calories = (elem as EditTextPreference).text.toString()
                else if (elem.key == "proteins_food")
                    proteins = ((elem as EditTextPreference).text.toString())
                else if (elem.key == "fats_food")
                    fats = ((elem as EditTextPreference).text.toString())
                else
                    carbohydrates = ((elem as EditTextPreference).text.toString())
            }

            newFood = FoodClass(nameFood, descriptionFood)
            if (calories != null && calories != "")
                newFood.calories = calories.toFloatOrNull()
            if (proteins != null && proteins != "")
                newFood.proteins = proteins.toFloatOrNull()
            if (fats != null && fats != "")
                newFood.fats = fats.toFloatOrNull()
            if (carbohydrates != null && carbohydrates != "")
                newFood.carbohydrates = carbohydrates.toFloatOrNull()
            (this.application as MyApplication).foodBaseListNew.add(newFood)

            clearAllPreferenceValue()
            val intent = Intent(this, BaseOfFoodActivity::class.java) // Можно по идеи убрать!!!
            startActivity(intent)
            finish()
        }
        toolbarCreateChangeFoodActivity = findViewById(R.id.toolbar)
        toolbarCreateChangeFoodActivity.setNavigationOnClickListener(View.OnClickListener {
            clearAllPreferenceValue()
            val intent = Intent(this, BaseOfFoodActivity::class.java) // Можно по идеи убрать!!!
            startActivity(intent)
            finish()
//            trainCreateList.clear()
        })

    }

    companion object {
        private var foodListPreference: ArrayList<Preference> = ArrayList()


        /**
         * A preference value change listener that updates the preference's summary
         * to reflect its new value.
         */
        private val sBindPreferenceSummaryToValueListener =
            Preference.OnPreferenceChangeListener { preference, value ->
                val stringValue = value.toString()
                if (preference is EditTextPreference)
                    if (stringValue != "")
                        preference.summary = stringValue
//            if (preference is ListPreference) {
//                // For list preferences, look up the correct display value in
//                // the preference's 'entries' list.
//                val listPreference = preference
//                val index = listPreference.findIndexOfValue(stringValue)
//                // Set the summary to reflect the new value.
//                preference.setSummary(
//                    if (index >= 0)
//                        listPreference.entries[index]
//                    else
//                        null)
//            } else if (preference is RingtonePreference) {
//                // For ringtone preferences, look up the correct display value
//                // using RingtoneManager.
//                if (TextUtils.isEmpty(stringValue)) {
//                    // Empty values correspond to 'silent' (no ringtone).
//                    preference.setSummary("Silent")
//                } else {
//                    val ringtone = RingtoneManager.getRingtone(
//                        preference.getContext(), Uri.parse(stringValue))
//                    if (ringtone == null) {
//                        // Clear the summary if there was a lookup error.
//                        preference.setSummary(null)
//                    } else {
//                        // Set the summary to reflect the new ringtone display
//                        // name.
//                        val name = ringtone.getTitle(preference.getContext())
//                        preference.setSummary(name)
//                    }
//                }
//            }
//            else {
//                // For all other preferences, set the summary to the value's
//                // simple string representation.
//                preference.summary = stringValue
//            }
                true
            }

        private fun bindPreferenceSummaryToValue(preference: Preference) {
            // Set the listener to watch for value changes.
            preference.onPreferenceChangeListener = sBindPreferenceSummaryToValueListener
            // Trigger the listener immediately with the preference's
            // current value.
            sBindPreferenceSummaryToValueListener.onPreferenceChange(
                preference,
                PreferenceManager
                    .getDefaultSharedPreferences(preference.context)
                    .getString(preference.key, "")
            )
        }

        private fun addPreferenceToList(preference: Preference) {
            foodListPreference.add(preference)
        }

        private fun clearAllPreferenceValue() {
            for (elem in foodListPreference) {
                if (elem is EditTextPreference) {
                    elem.text = ""
//                    elem.summary = "Нажмите, чтобы ввести наименование пищи"

//                    if (elem.key == "privet")
//                        elem.text = "sds"

                    if (elem.key == "name_food")
                        elem.summary = "Нажмите, чтобы ввести наименование пищи"
                    else if (elem.key == "description_food")
                        elem.summary = "Нажмите, чтобы ввести описание пищи"
                    else if (elem.key == "calories_food")
                        elem.summary = "Нажмите, чтобы ввести количество калорий пищи"
                    else if (elem.key == "proteins_food")
                        elem.summary = "Нажмите, чтобы ввести количество белков пищи"
                    else if (elem.key == "fats_food")
                        elem.summary = "Нажмите, чтобы ввести количество жиров пищи"
                    else (elem.key == "carbohydrates_food")
                    elem.summary = "Нажмите, чтобы ввести количество углеводов пищи"
//                    else
//                        elem.summary = "Нажмите, чтобы ввести описание упражнения"
                }
            }
        }

        private fun changeInputTypePreference() {
            for (elem in foodListPreference) {
//                val pref = findPreference("key") as EditTextPreference
                if (elem.key != "name_food" && elem.key != "description_food") {
//                    var elemPreference = elem as EditTextPreference
                    (elem as EditTextPreference).setOnBindEditTextListener{ editText ->
                        editText.inputType = InputType.TYPE_NUMBER_FLAG_DECIMAL
                    }
                }
//                val weeklyGoalPref: EditTextPreference = findPreference("weekly_goal")
//                if (weeklyGoalPref != null) {
//                    weeklyGoalPref.setOnBindEditTextListener { editText ->
//                        editText.inputType = InputType.TYPE_CLASS_NUMBER
//                    }
//                }
            }
        }
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.food_preference, rootKey)

            findPreference<Preference>("name_food")?.let { bindPreferenceSummaryToValue(it) }
            findPreference<Preference>("name_food")?.let { addPreferenceToList(it) }

            findPreference<Preference>("description_food")?.let { bindPreferenceSummaryToValue(it) }
            findPreference<Preference>("description_food")?.let { addPreferenceToList(it) }

            findPreference<Preference>("calories_food")?.let { bindPreferenceSummaryToValue(it) }
            findPreference<Preference>("calories_food")?.let { addPreferenceToList(it) }

            findPreference<Preference>("proteins_food")?.let { bindPreferenceSummaryToValue(it) }
            findPreference<Preference>("proteins_food")?.let { addPreferenceToList(it) }

            findPreference<Preference>("fats_food")?.let { bindPreferenceSummaryToValue(it) }
            findPreference<Preference>("fats_food")?.let { addPreferenceToList(it) }

            findPreference<Preference>("carbohydrates_food")?.let { bindPreferenceSummaryToValue(it) }
            findPreference<Preference>("carbohydrates_food")?.let { addPreferenceToList(it) }

//            findPreference("name_food")
//            bindPreferenceSummaryToValue(findPreference("text"))
//            bindPreferenceSummaryToValue(findPreference("list"))
        }
    }
}