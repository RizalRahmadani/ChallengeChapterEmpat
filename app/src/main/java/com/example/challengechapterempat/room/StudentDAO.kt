package com.example.challengechapterempat.room

import androidx.room.*


@Dao
interface StudentDAO {

    @Insert
    fun insertStudent(student : DataStudent)

    @Query("SELECT * FROM DataStudent ORDER BY id DESC ")
    fun getDataStudent() : List<DataStudent>

    @Delete
    fun deleteStudent (student: DataStudent)

    @Update
    fun updateStudent(student: DataStudent)

}