package br.com.kotlindemo.kotlindemo.model

import android.arch.persistence.room.Room
import android.content.Context

class FactoryDAO {

    companion object {
        private fun getAppDatabase(applicationContext: Context): AppDatabase {
            return newInstance(applicationContext)
        }

        private fun newInstance(applicationContext: Context) =
                Room.databaseBuilder(applicationContext,
                        AppDatabase::class.java, "kotlerdemo.db").build()

        fun getPlaceDatabase(applicationContext: Context): RestaurantDAO {
            return getAppDatabase(applicationContext).placeDAO()
        }
    }
}
