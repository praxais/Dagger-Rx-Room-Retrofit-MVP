package com.prajwal.demodagger.feature.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.prajwal.demodagger.R
import com.prajwal.demodagger.feature.shared.model.Data
import com.squareup.picasso.Picasso

/**
 * Created by prajwal on 4/9/18.
 */

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {
    private val myList: MutableList<Data> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_main, parent, false))

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val data = myList[position]

        holder.txvMain?.text = data.name

        Picasso.get()
                .load(data.imageUrl)
                .into(holder.imgMain)
    }

    override fun getItemCount() = myList.count()

    fun setData(myList: List<Data>) {
        this.myList.clear()
        this.myList.addAll(myList)
        notifyDataSetChanged()
    }
}