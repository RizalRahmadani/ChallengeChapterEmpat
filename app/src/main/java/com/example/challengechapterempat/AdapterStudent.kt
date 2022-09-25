package com.example.challengechapterempat

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.challengechapterempat.databinding.FragmentHomeScreenEditDataBinding
import com.example.challengechapterempat.databinding.ItemHomeBinding
import com.example.challengechapterempat.fragment.HomeScreenFragment
import com.example.challengechapterempat.room.DataStudent
import com.example.challengechapterempat.room.StudentDataBase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.util.ArrayList

class AdapterStudent (var listStudent : List<DataStudent>) : RecyclerView.Adapter<AdapterStudent.ViewHolder> () {
        var dbStudent : StudentDataBase? = null
    class ViewHolder (var binding : ItemHomeBinding) : RecyclerView.ViewHolder(binding.root){
        fun dataBinding(itemData : DataStudent){
            binding.student = itemData
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.dataBinding(listStudent[position])
        holder.binding.btnDeleteNote.setOnClickListener{
            dbStudent = StudentDataBase.getInstance(it.context)

            GlobalScope.async {
                StudentViewModel(Application()).deleteStudent(listStudent[position])
                dbStudent?.studentDao()?.deleteStudent(listStudent[position])
//                val del = dbStudent?.studentDao()?.deleteStudent(listStudent[position])
//                (holder.itemView.context as HomeScreenFragment)
//                    (holder.itemView.context as HomeScreenFragment).getAllStudent()
                val nav = Navigation.findNavController(it)
                nav.run{
                    navigate(R.id.homeScreenFragment)
                }
            }
        }

        holder.binding.btnEditNote.setOnClickListener {
//            var move = Intent(it.context, FragmentHomeScreenEditDataBinding::class.java)
//            move.putExtra("dataedit", listStudent[position])
//            it.context.startActivity(move)
            val bundle = Bundle()
            bundle.putSerializable("student", listStudent[position])
            Navigation.findNavController(it).navigate(R.id.action_homeScreenFragment_to_homeScreenEditDataFragment, bundle)

        }
//        holder.binding.klik.setOnClickListener{
//            var detail = Intent(it.context, DetailNoteActivity::class.java)
//            detail.putExtra("detail", listNote[position])
//            it.context.startActivity(detail)
//        }
    }


    override fun getItemCount(): Int {
        return  listStudent.size
    }

    fun setData(listStudent: ArrayList<DataStudent>)
    {
        this.listStudent=listStudent
    }
}