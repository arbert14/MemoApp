package com.example.practicememoapp.data.entites

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class User(
    @PrimaryKey(autoGenerate = true)
    val idx: Int = 0,
    val id: String,
    val pw: String
): Parcelable
