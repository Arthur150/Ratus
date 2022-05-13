package fr.univ.lyon1.lpiem.ratus.ui.transaction

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import fr.univ.lyon1.lpiem.ratus.R
import fr.univ.lyon1.lpiem.ratus.model.TransactionCategory

class CategoryAdapter(context: Context, private val categoryList: List<TransactionCategory>) :
    ArrayAdapter<TransactionCategory>(context, 0, categoryList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    private fun initView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.category_spinner_item, parent, false)
        val imageView = view.findViewById<ImageView>(R.id.categoryItemImageView)
        val textView = view.findViewById<TextView>(R.id.categoryItemTextView)

        imageView.setImageResource(categoryList[position].icon)
        textView.apply {
            text = context.getString(categoryList[position].text)
            setTextColor(context.getColor(categoryList[position].color))
        }

        return view
    }
}