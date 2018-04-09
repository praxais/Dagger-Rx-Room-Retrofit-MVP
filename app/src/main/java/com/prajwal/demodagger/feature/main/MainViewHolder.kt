package com.prajwal.demodagger.feature.main

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.adapter_main.view.*

/**
 * Created by prajwal on 4/9/18.
 */

class MainViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    val crvMain = itemView?.crvMain
    val imgMain = itemView?.imgMain
    val txvMain = itemView?.txvMain
}