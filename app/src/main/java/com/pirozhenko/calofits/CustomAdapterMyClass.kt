package com.pirozhenko.calofits

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import java.lang.String


class CustomAdapterMyClass {
}

//class MyClassAdapter(context: Context?, textViewResourceId: Int, items: ArrayList<ExerciseTrain?>?) :
//    ArrayAdapter<ExerciseTrain?>(context!!, textViewResourceId, items!!) {
//    private class ViewHolder {
//        private val itemView: TextView? = null
//    }
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        var convertView = convertView
//        if (convertView == null) {
//            convertView = LayoutInflater.from(this.context)
//                .inflate(R.layout.listview_association, parent, false)
//            var viewHolder = ViewHolder()
//            viewHolder.itemView = convertView!!.findViewById<View>(R.id.ItemView) as TextView
//            convertView.setTag(viewHolder)
//        } else {
//            viewHolder = convertView.tag as ViewHolder
//        }
//        val item: ExerciseTrain? = getItem(position)
//        if (item != null) {
//             My layout has only one TextView
//             do whatever you want with your string and long
//            viewHolder.itemView.setText(String.format("%s %d", item.reason, item.long_val))
//        }
//        return convertView
//    }
//}