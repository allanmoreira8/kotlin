package br.com.kotlindemo.kotlindemo.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [Restaurant::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun placeDAO(): RestaurantDAO
}
