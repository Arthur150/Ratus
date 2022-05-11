package fr.univ.lyon1.lpiem.ratus.ui.transaction

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import fr.univ.lyon1.lpiem.ratus.R
import fr.univ.lyon1.lpiem.ratus.model.RecurrenceType

class RecurrenceTypeAdapter(context: Context, private val typeList: List<RecurrenceType>) :
    ArrayAdapter<RecurrenceType>(context, 0,typeList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    private fun initView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.recurrence_type_spinner_item,parent,false)
        val textView = view.findViewById<TextView>(R.id.recurrenceTypeTextView)

        textView.text = context.getString(typeList[position].text)

        return view
    }
}