package com.example.challengechapterempat

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.challengechapterempat.room.DataStudent
import com.example.challengechapterempat.room.StudentDataBase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StudentViewModel(app : Application): AndroidViewModel(app) {

    lateinit var allStudent : MutableLiveData<List<DataStudent>>

    init{
        allStudent = MutableLiveData()
        getAllStudent()
    }
    fun getAllStudentObservers(): MutableLiveData<List<DataStudent>> {
        return allStudent
    }



    fun getAllStudent() {
        GlobalScope.launch {
            val userDao = StudentDataBase.getInstance(getApplication())!!.studentDao()
            val listStudet = userDao.getDataStudent()
            allStudent.postValue(listStudet)
        }
    }

    fun insertStudent(entity: DataStudent){
        val studentDao = StudentDataBase.getInstance(getApplication())?.studentDao()
        studentDao!!.insertStudent(entity)
        getAllStudent()
    }

    fun deleteStudent(entity: DataStudent){
        val userDao = StudentDataBase.getInstance(getApplication())!!.studentDao()
        userDao?.deleteStudent(entity)
        getAllStudent()
    }

    fun updateStudent(entity: DataStudent){
        val userDao = StudentDataBase.getInstance(getApplication())!!.studentDao()
        userDao?.updateStudent(entity)
        getAllStudent()
    }

}