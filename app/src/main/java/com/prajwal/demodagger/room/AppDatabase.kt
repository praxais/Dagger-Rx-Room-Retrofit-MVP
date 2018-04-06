package com.prajwal.demodagger.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.prajwal.demodagger.feature.shared.model.Data

/**
 * Created by prajwal on 4/6/18.
 */

@Database(entities = [Data::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun demoDao(): DemoDao
}