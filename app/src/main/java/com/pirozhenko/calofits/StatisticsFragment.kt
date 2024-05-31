package com.pirozhenko.calofits

//import android.R
//import android.R
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StatisticsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StatisticsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var barChart: BarChart

    // on below line we are creating
    // a variable for bar data
    lateinit var barData: BarData

    // on below line we are creating a
    // variable for bar data set
    lateinit var barDataSet: BarDataSet
    lateinit var barDataSet2: BarDataSet
    lateinit var barDataSet3: BarDataSet
    lateinit var barDataSet4: BarDataSet


    // on below line we are creating array list for bar data
    lateinit var barEntriesList: ArrayList<BarEntry>
    lateinit var barEntriesList2: ArrayList<BarEntry>
    lateinit var barEntriesList3: ArrayList<BarEntry>
    lateinit var barEntriesList4: ArrayList<BarEntry>


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
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }
//    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        barChart = view.findViewById(R.id.idBarChart)

        // on below line we are calling get bar
        // chart data to add data to our array list
        getBarChartData()

        // on below line we are initializing our bar data set
        barDataSet = BarDataSet(barEntriesList, "Ккал")
        barDataSet2 = BarDataSet(barEntriesList2, "Белки")
        barDataSet3 = BarDataSet(barEntriesList3, "Жиры")
        barDataSet4 = BarDataSet(barEntriesList4, "Углеводы")
//        barDataSet1 = BarDataSet(getBarEntriesOne(), "First Set")
//        barDataSet1.setColor(getApplicationContext().getResources().getColor(R.color.purple_200))
//        barDataSet2 = BarDataSet(getBarEntriesTwo(), "Second Set")
//        barDataSet2.setColor(Color.BLUE)

        // on below line we are initializing our bar data
        barData = BarData(barDataSet, barDataSet2, barDataSet3, barDataSet4)

        // on below line we are setting data to our bar chart
        barChart.data = barData

        // on below line we are setting colors for our bar chart text
        barDataSet.valueTextColor = Color.BLACK
        // on below line we are setting color for our bar data set
//        barDataSet.setColor(resources.getColor(android.R.color.holo_purple))
        barDataSet.setColors(Color.BLUE)
        // on below line we are setting text size
        barDataSet.valueTextSize = 16f

        barDataSet2.valueTextColor = Color.BLACK
        barDataSet2.setColors(Color.YELLOW)
        barDataSet2.valueTextSize = 16f

        barDataSet3.valueTextColor = Color.BLACK
        barDataSet3.setColors(Color.GREEN)
        barDataSet3.valueTextSize = 16f

        barDataSet4.valueTextColor = Color.BLACK
        barDataSet4.setColors(Color.GRAY)
        barDataSet4.valueTextSize = 16f


        // on below line we are enabling description as false
        barChart.description.isEnabled = false
    }
    private fun getBarChartData() {
        barEntriesList = ArrayList()
        barEntriesList2 = ArrayList()
        barEntriesList3 = ArrayList()
        barEntriesList4 = ArrayList()


        // on below line we are adding data
        // to our bar entries list
        barEntriesList.add(BarEntry(1f, 349f))
        barEntriesList2.add(BarEntry(2f, 17f))
        barEntriesList3.add(BarEntry(3f, 15f))
        barEntriesList4.add(BarEntry(4f, 36f))

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StatisticsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StatisticsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}