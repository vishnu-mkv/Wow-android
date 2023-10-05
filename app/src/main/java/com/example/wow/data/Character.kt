package com.example.wow.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Character (
    var name : String = "",
    var anime : String = "",
    var year : Int? = null
    ) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}

