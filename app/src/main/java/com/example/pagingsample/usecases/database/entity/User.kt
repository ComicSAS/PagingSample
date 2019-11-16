package com.example.pagingsample.usecases.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class that represents our items.
 */
@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)