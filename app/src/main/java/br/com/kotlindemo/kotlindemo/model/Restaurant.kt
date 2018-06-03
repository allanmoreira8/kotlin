package br.com.kotlindemo.kotlindemo.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity
data class Restaurant (@PrimaryKey @ColumnInfo var name: String, @ColumnInfo var description: String) : Serializable