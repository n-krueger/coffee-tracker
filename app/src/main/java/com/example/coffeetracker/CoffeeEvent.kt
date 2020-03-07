package com.example.coffeetracker

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.OffsetDateTime

@Entity
data class CoffeeEvent (
    @PrimaryKey val timestamp: OffsetDateTime,
    @ColumnInfo(name = "type") val type: String
)
