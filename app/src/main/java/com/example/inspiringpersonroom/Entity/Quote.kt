package com.example.inspiringpersonroom.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class Quote(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ForeignKey(
        entity = Person::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("personId"),
        onDelete = ForeignKey.CASCADE
    )

    @ColumnInfo(name = "personId") var personId : Int,
    @ColumnInfo(name = "name") val quote : String
)
