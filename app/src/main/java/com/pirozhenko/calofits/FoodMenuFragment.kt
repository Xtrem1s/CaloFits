package com.pirozhenko.calofits

//import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.SimpleExpandableListAdapter
import androidx.fragment.app.Fragment


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FoodMenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FoodMenuFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    val test1 = arrayOf("Завтрак")

    val test2 = arrayOf("Яйца (ккал: 158, белки: 12г, жиры: 11г, углеводы: 0.7г)", "Банан (ккал: 89, белки: 1.5г, жиры: 0.1г, углеводы: 21г)", "Овсянка (ккал: 102, белки: 3.2г, жиры: 4.1г, углеводы: 14.2г)", "Добавить продукт...")

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
        return inflater.inflate(R.layout.fragment_food_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var map: MutableMap<String, String>
        // коллекция для групп
        // коллекция для групп
        val groupDataList = ArrayList<Map<String, String>>()
        // заполняем коллекцию групп из массива с названиями групп

        // заполняем коллекцию групп из массива с названиями групп
        for (group in test1) {
            // заполняем список атрибутов для каждой группы
            map = HashMap()
            map["groupName"] = group // время года
            groupDataList.add(map)
        }

        // список атрибутов групп для чтения

        // список атрибутов групп для чтения
        val groupFrom = arrayOf("groupName")
        // список ID view-элементов, в которые будет помещены атрибуты групп
        // список ID view-элементов, в которые будет помещены атрибуты групп
        val groupTo = intArrayOf(android.R.id.text1)

        // создаем общую коллекцию для коллекций элементов

        // создаем общую коллекцию для коллекций элементов
        val сhildDataList = ArrayList<ArrayList<Map<String, String>>>()

        // в итоге получится сhildDataList = ArrayList<сhildDataItemList>

        // создаем коллекцию элементов для первой группы

        // в итоге получится сhildDataList = ArrayList<сhildDataItemList>

        // создаем коллекцию элементов для первой группы
        val сhildDataItemList = ArrayList<Map<String, String>>()
        // заполняем список атрибутов для каждого элемента
        // заполняем список атрибутов для каждого элемента
        for (month in test2) {
            map = HashMap()
            map["monthName"] = month // название месяца
            сhildDataItemList.add(map)
        }
        // добавляем в коллекцию коллекций
        // добавляем в коллекцию коллекций
        сhildDataList.add(сhildDataItemList)
        // список атрибутов элементов для чтения
        // список атрибутов элементов для чтения
        val childFrom = arrayOf("monthName")
        // список ID view-элементов, в которые будет помещены атрибуты
        // элементов
        // список ID view-элементов, в которые будет помещены атрибуты
        // элементов
        val childTo = intArrayOf(android.R.id.text1)

        val adapter = SimpleExpandableListAdapter(
            activity, groupDataList,
            android.R.layout.simple_expandable_list_item_1, groupFrom,
            groupTo, сhildDataList, android.R.layout.simple_list_item_1,
            childFrom, childTo
        )

        val expandableListView = view.findViewById(R.id.expListView) as ExpandableListView
        expandableListView.setAdapter(adapter)

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
         * @return A new instance of fragment FoodMenuFragment.
         */
        // TODO: Rename and change types and number of parameters
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