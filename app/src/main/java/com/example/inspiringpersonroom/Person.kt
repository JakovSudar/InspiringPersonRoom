package com.example.inspiringpersonroom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persons")

data class Person (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "imgUrl") val imgUrl: String
)