package com.example.coffeetracker.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.coffeetracker.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter

private class WeekDayValueFormatter : ValueFormatter() {
    private val days = arrayOf("Mo", "Tu", "Wed", "Th", "Fr", "Sa", "Su")

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return days.getOrNull(value.toInt()) ?: value.toString()
    }
}

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val barChart: BarChart = root.findViewById(R.id.chart)

        barChart.xAxis.valueFormatter = WeekDayValueFormatter()
        barChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        barChart.axisRight.isEnabled = false

        dashboardViewModel.coffeeCountsByDay.observe(viewLifecycleOwner, Observer {
            val barDataList = it.map { (weekDay, count) ->
                BarEntry(weekDay.toFloat(), count.toFloat())
            }
            val barDataSet: BarDataSet = BarDataSet(barDataList, "Coffees/Day");
            barChart.data = BarData(barDataSet);
            barChart.invalidate();
        })

        return root
    }
}
