package com.example.challengechapterempat.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.challengechapterempat.fragment.HomeScreenInputDataFragment

@Database(entities = [DataStudent::class], version = 1)
abstract class StudentDataBase : RoomDatabase(){

    abstract fun studentDao() : StudentDAO

    companion object{
        private var INSTANCE : StudentDataBase? = null

        fun getInstance(context: Context):StudentDataBase? {
            if (INSTANCE == null){
                synchronized(StudentDataBase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        StudentDataBase::class.java,"Student.db").build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }


    }
}