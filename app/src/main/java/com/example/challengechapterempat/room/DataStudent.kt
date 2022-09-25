package com.example.challengechapterempat.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class DataStudent(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var nama : String,
    var stambuk : String,
    var jurusan : String
) : Serializable{
}