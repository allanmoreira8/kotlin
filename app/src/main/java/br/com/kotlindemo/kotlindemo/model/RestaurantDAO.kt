package br.com.kotlindemo.kotlindemo.model

import android.arch.persistence.room.*
import io.reactivex.Flowable

@Dao
interface RestaurantDAO {

    @Query("SELECT * FROM Restaurant")
    fun getAll(): Flowable<List<Restaurant>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(restaurant: Restaurant): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg restaurant:Restaurant) : List<Long>

    @Delete
    fun delete(restaurant: Restaurant)


}