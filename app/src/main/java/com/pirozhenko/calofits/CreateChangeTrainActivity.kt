package com.pirozhenko.calofits

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.preference.EditTextPreference
import androidx.preference.MultiSelectListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager


//class CreateChangeTrainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_create_change_train)
//    }
//}

class CreateChangeTrainActivity : AppCompatActivity() {
    private lateinit var toolbarCreateChangeTrainActivity: Toolbar
    private lateinit var buttonSave: Button
    private lateinit var buttonCancel: Button

    private lateinit var newTrain: ExerciseTrainClass


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_change_train)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbarCreateChangeTrainActivity = findViewById(R.id.toolbar)
        toolbarCreateChangeTrainActivity.setNavigationOnClickListener(View.OnClickListener {
            clearAllPreferenceValue()
            val intent = Intent(this, BaseOfTrainActivity::class.java) // Можно по идеи убрать!!!
            startActivity(intent)
            finish()
//            trainCreateList.clear()
        })
        clearAllPreferenceValue()

        buttonCancel = findViewById(R.id.button3)
        buttonCancel.setOnClickListener {
            clearAllPreferenceValue()
            val intent = Intent(this, BaseOfTrainActivity::class.java) // Можно по идеи убрать!!!
            startActivity(intent)
            finish()
        }

        buttonSave = findViewById(R.id.button2)
        buttonSave.setOnClickListener {
            var nameTrain: String = ""
            var descriptionTrain: String = ""
            var repetitions: Int? = null
            var approaches: Int? = null
            var distance: Int? = null
            var time: Int? = null
            var weight: Int? = null

            for (elem in trainListPreference) {
                if (elem.key == "name_train")
                    nameTrain = ((elem as EditTextPreference).text.toString())
                else if (elem.key == "description_train")
                    descriptionTrain = ((elem as EditTextPreference).text.toString())
                else if (elem.key == "property_train") {
                    for (i in (elem as MultiSelectListPreference).values) {
                        if (i.toString() == "0")
                            approaches = 0
                        else if (i.toString() == "1")
                            repetitions = 0
                        else if (i.toString() == "2")
                            weight = 0
                        else if (i.toString() == "3")
                            distance = 0
                        else if (i.toString() == "4")
                            time = 0

                    }
                }
            }

            newTrain = ExerciseTrainClass(nameTrain, descriptionTrain)
            if (approaches != null)
                newTrain.approaches = approaches
            if (repetitions != null)
                newTrain.repetitions = repetitions
            if (distance != null)
                newTrain.distance = distance
            if (approaches != null)
                newTrain.time = time
            if (weight != null)
                newTrain.weight = weight
            (this.application as MyApplication).trainBaseListNew.add(newTrain)

            clearAllPreferenceValue()
            val intent = Intent(this, BaseOfTrainActivity::class.java) // Можно по идеи убрать!!!
            startActivity(intent)
            finish()
        }

    }

    companion object {
        private var trainListPreference: ArrayList<Preference> = ArrayList()


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

//            if (preference is MultiSelectListPreference) {
                // For list preferences, look up the correct display value in
                // the preference's 'entries' list.
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
            trainListPreference.add(preference)
        }

        private fun clearAllPreferenceValue() {
            for (elem in trainListPreference) {
                if (elem is EditTextPreference) {
                    elem.text = ""
                    if (elem.key == "name_train")
                        elem.summary = "Нажмите, чтобы ввести наименование пищи"
                    else
                        elem.summary = "Нажмите, чтобы ввести описание упражнения"
                } else if (elem is MultiSelectListPreference) {
//                    Log.v()
//                    elem.values.remove("подходы")
//                    elem.values.remove("повторения")
//                    elem.values.remove("0")
//                    elem.li
//                    val multiSelectListPref =
//                        findPreference("pref_name") as MultiSelectListPreference?
//                    if (elem != null) {
//                        elem.onPreferenceChangeListener =
//                            Preference.OnPreferenceChangeListener { preference, _ ->
//                                val mpreference = preference as MultiSelectListPreference
//                                mpreference.summary = newValue.toString()
//                                mpreference.values.clear()
//                                true
//                            }
//                    }
//                    elem.
                    val uncheckValues: Set<String> = HashSet()
                    elem.values = uncheckValues
//                    elem.values.removeAll(arrayListOf("0", "1", "2", "3", "повторения", "подходы"))
//                    elem.notifyDependencyChange(true)

//                    elem.chan
//                    elem.values.removeAll(elem.entryValues)
//                    elem.values = elem.entries.toSet()
//                    Log.v("elemvalues", elem.values.toString())
//                    Log.v("elemvalues", elem.values.size.toString())

//                    elem.summary = "Характеристики упражнения"
                }


            }
        }
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.train_preference, rootKey)

            findPreference<Preference>("name_train")?.let { bindPreferenceSummaryToValue(it) }
            findPreference<Preference>("description_train")?.let { bindPreferenceSummaryToValue(it) }

            findPreference<Preference>("name_train")?.let { addPreferenceToList(it) }
            findPreference<Preference>("description_train")?.let { addPreferenceToList(it) }
            findPreference<Preference>("property_train")?.let { addPreferenceToList(it) }

//            findPreference("name_food")
//            bindPreferenceSummaryToValue(findPreference("text"))
//            bindPreferenceSummaryToValue(findPreference("list"))
        }
    }

//    class SettingsFragment : PreferenceFragmentCompat() {
//        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
//            setPreferencesFromResource(R.xml.train_preference, rootKey)
//        }
//    }
}