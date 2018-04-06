package com.prajwal.demodagger.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.prajwal.demodagger.feature.shared.model.Data
import io.reactivex.Single

/**
 * Created by prajwal on 4/6/18.
 */

@Dao
interface DemoDao {
    @Query("SELECT * FROM data")
    fun getAllDemo(): Single<List<Data>>

    @Insert
    fun insetDemo(demoData: List<Data>)
}