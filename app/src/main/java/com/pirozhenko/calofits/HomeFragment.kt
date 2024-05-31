package com.pirozhenko.calofits

//import dev.abhaycloud.infinitescrollablecalendarview.InfiniteScrollableCalendarView
//import dev.abhaycloud.infinitescrollablecalendarview.listener.CalendarCallBackListener
//import dev.abhaycloud.infinitescrollablecalendarview.model.MonthModel
//import dev.abhaycloud.infinitescrollablecalendarview.model.SpecialDateModel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.Date


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var calendar: CalendarView
    lateinit var button_date: Button
    lateinit var linearLayout: LinearLayout
//    lateinit var scroll_view_1: Button

    lateinit var text_view: TextView
    lateinit var text_view_2: TextView


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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    //    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        calendar = view.findViewById<CalendarView>(R.id.calendarView)
        button_date = view.findViewById<Button>(R.id.button4)
        text_view = view.findViewById(R.id.textView4)
//        text_view_2 = view.findViewById(R.id.textView5)
        linearLayout = view.findViewById(R.id.linearLayoutHome)

        calendar.visibility = View.INVISIBLE
//        button_date.text = calendar.
        val sdf = SimpleDateFormat("dd.MM.yyyy")
        val selectedDate: String = sdf.format(Date(calendar.date)).toString()
        button_date.text = selectedDate

        var trainList = (activity?.application as MyApplication).trainClassListNew
        var result = "Тренировка:\n\n\n Нет данных на эту дату..."
        var trainListSize = trainList.size
        for (i in 1..trainListSize){
            if (trainList[trainListSize - i].dateTrain == selectedDate) {
                result = "Тренировка:\n"

                var exercisesList = trainList[trainListSize - i].exercisesList
//                result = result + exercisesList.name + " ("

                for (j in exercisesList)
                {
                    result = result + " - " + j.key.name + " ("
                    var repetitionsDateTmp = j.value.repetitionsDate
                    var approachesDateTmp = j.value.approachesDate
                    var distanceDateTmp = j.value.distanceDate
                    var timeDateTmp = j.value.timeDate
                    var weightTmp = j.value.weightDate

                    if (repetitionsDateTmp != null)
                        result = result + repetitionsDateTmp + " повторения(ий);"
                    if (approachesDateTmp != null)
                        result = result + approachesDateTmp + " подхода(ов);"
                    if (weightTmp != null)
                        result = result + weightTmp + " килограмм;"
                    if (distanceDateTmp != null)
                        result = result + distanceDateTmp + " метра(ов);"
                    if (timeDateTmp != null)
                        result = result + timeDateTmp + " секунд(ы);"
                    result = "$result)\n"
//                    for (k in j.propertyTrainList){
//                        if k
//                    }
                }
                break
            }
        }
        text_view.text = result
        button_date.setOnClickListener {
            calendar.visibility = View.VISIBLE
            linearLayout.visibility = View.INVISIBLE
//            text_view_2.visibility = View.INVISIBLE
        }
        calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            var month_rd = (month + 1).toString()
            if (month_rd.length < 2)
                month_rd = "0" + month_rd

            button_date.text = "$dayOfMonth.${month_rd}.$year"
            calendar.visibility = View.INVISIBLE
            linearLayout.visibility= View.VISIBLE
//            text_view_2.visibility = View.VISIBLE
//            val sdf = SimpleDateFormat("dd.MM.yyyy")
            val selectedDate = "$dayOfMonth.${month_rd}.$year"
//            button_date.text = selectedDate
            val trainList = (activity?.application as MyApplication).trainClassListNew
            var result = "Тренировка:\n\n\n Нет данных на эту дату..."

            var trainListSize = trainList.size
            for (i in 1..trainListSize){
                if (trainList[trainListSize - i].dateTrain == selectedDate) {
                    result = "Тренировка:\n"
                    var exercisesList = trainList[trainListSize - i].exercisesList
//                result = result + exercisesList.name + " ("

                    for (j in exercisesList)
                    {
                        result = result + " - " + j.key.name + " ("
                        var repetitionsDateTmp = j.value.repetitionsDate
                        var approachesDateTmp = j.value.approachesDate
                        var distanceDateTmp = j.value.distanceDate
                        var timeDateTmp = j.value.timeDate
                        if (repetitionsDateTmp != null)
                            result = result + repetitionsDateTmp + " повторений;"
                        if (approachesDateTmp != null)
                            result = result + approachesDateTmp + " подходов;"
                        if (distanceDateTmp != null)
                            result = result + distanceDateTmp + " метров;"
                        if (timeDateTmp != null)
                            result = result + timeDateTmp + " секунд;"
                        result = "$result)\n"
//                    for (k in j.propertyTrainList){
//                        if k
//                    }
                    }
                    break
                }
            }
            text_view.text = result

        }
//        super.onViewCreated(view, savedInstanceState)
//        val infiniteCalendarView = view.findViewById<InfiniteScrollableCalendarView>(R.id.infiniteScrollableCalendarView)
//        infiniteCalendarView.showTitle(true) // by default value is true
//        infiniteCalendarView.setStartDate("2023-01-01")
//        val specialDateList = ArrayList<SpecialDateModel>()
//        specialDateList.add(SpecialDateModel(LocalDate.parse("2023-01-10")))
//        specialDateList.add(SpecialDateModel(LocalDate.parse("2023-01-21")))
//
//        infiniteCalendarView.setSpecialDateList(specialDateList)
//        infiniteCalendarView.setOnCalendarListener(object : CalendarCallBackListener {
//            override fun onDateChange(date: LocalDate) {
//                 do something
//            }
//
//            override fun onMonthChange(month: MonthModel) {
        // do something
        // binding.textView.text = "${month.monthName} ${month.year}"
//            }
//        })

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}