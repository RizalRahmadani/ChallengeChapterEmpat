package com.example.challengechapterempat.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.challengechapterempat.R
import com.example.challengechapterempat.StudentViewModel
import com.example.challengechapterempat.databinding.FragmentHomeScreenEditDataBinding
import com.example.challengechapterempat.room.DataStudent
import com.example.challengechapterempat.room.StudentDataBase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async


class HomeScreenEditDataFragment : Fragment() {
    lateinit var binding : FragmentHomeScreenEditDataBinding
    var dbStudent : StudentDataBase? = null
    lateinit var vmStudent : StudentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeScreenEditDataBinding.inflate(layoutInflater, container,false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dbStudent = StudentDataBase.getInstance(requireContext())
        vmStudent = ViewModelProvider(this).get(StudentViewModel::class.java)

        var getData = arguments?.getSerializable("student") as DataStudent
        binding.etNama.setText(getData.nama)
        binding.etStambuk.setText(getData.stambuk)
        binding.etJurusan.setText(getData.jurusan)

        binding.btnEdit.setOnClickListener{
            EditStudent()
            Toast.makeText(context, "Edit data student berhasil", Toast.LENGTH_SHORT).show()
        }
    }

    fun EditStudent(){
        GlobalScope.async {
            var nama = binding.etNama.text.toString()
            var stambuk = binding.etStambuk.text.toString()
            var jurusan = binding.etJurusan.text.toString()

            dbStudent!!.studentDao().insertStudent(DataStudent(0,nama,stambuk, jurusan))

        }
    }

}