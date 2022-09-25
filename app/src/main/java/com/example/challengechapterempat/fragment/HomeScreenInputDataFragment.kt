package com.example.challengechapterempat.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.challengechapterempat.R
import com.example.challengechapterempat.databinding.FragmentHomeScreenBinding
import com.example.challengechapterempat.databinding.FragmentHomeScreenInputDataBinding
import com.example.challengechapterempat.room.DataStudent
import com.example.challengechapterempat.room.StudentDataBase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async


class HomeScreenInputDataFragment : Fragment() {

    lateinit var binding : FragmentHomeScreenInputDataBinding
    var dbStudent : StudentDataBase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeScreenInputDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dbStudent = StudentDataBase.getInstance(requireContext())

        binding.btnTambah.setOnClickListener{
            addStudent()
            Navigation.findNavController(view).navigate(R.id.action_homeScreenInputDataFragment_to_homeScreenFragment)
        }
    }

    fun addStudent(){
        GlobalScope.async {
            var nama = binding.etNama.text.toString()
            var stambuk = binding.etStambuk.text.toString()
            var jurusan = binding.etJurusan.text.toString()

            dbStudent!!.studentDao().insertStudent(DataStudent(0,nama,stambuk, jurusan))
        }
//        finish()
    }
}