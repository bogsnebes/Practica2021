package com.bogsnebes.studentmotivation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MessageAdapter(itemListItems: ArrayList<ItemList>, context: Context) :
    RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    private val listItemsR = itemListItems
    val contextR = context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvNameList = view.findViewById<TextView>(R.id.tvNameMessages)
        private val tvDescList = view.findViewById<TextView>(R.id.tvDescrMessages)
        private val tvImageList = view.findViewById<ImageView>(R.id.imMessages)

        init {
            itemView.setOnClickListener {
                Toast.makeText(contextR, "${tvNameList.text}", Toast.LENGTH_SHORT).show()
            }
        }

        fun bind(ItemList: ItemList) {
            tvNameList.text = ItemList.textName
            tvDescList.text = ItemList.textDesc
            tvImageList.setImageResource(ItemList.im)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(contextR)
        return ViewHolder(inflater.inflate(R.layout.item_message, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItemsR[position])
    }

    override fun getItemCount(): Int {
        return listItemsR.size
    }
}