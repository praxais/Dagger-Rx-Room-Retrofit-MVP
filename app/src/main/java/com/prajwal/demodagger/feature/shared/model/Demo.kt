package com.prajwal.demodagger.feature.shared.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by prajwal on 4/6/18.
 */

data class Demo(val data: List<Data>)

@Entity()
data class Data(
        @PrimaryKey
        val id: Int,
        val name: String,
        val imageUrl: String
)