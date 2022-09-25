package com.example.challengechapterempat.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengechapterempat.AdapterStudent
import com.example.challengechapterempat.R
import com.example.challengechapterempat.StudentViewModel
import com.example.challengechapterempat.databinding.FragmentHomeScreenBinding
import com.example.challengechapterempat.room.DataStudent
import com.example.challengechapterempat.room.StudentDataBase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HomeScreenFragment : Fragment() {

    lateinit var binding : FragmentHomeScreenBinding
    lateinit var sharedHome : SharedPreferences
    var dbStudent : StudentDataBase? = null
    lateinit var adapterStudent : AdapterStudent
    lateinit var vmStudent : StudentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        return binding.root


//
//        StudentDB = StudentDataBase.getInstance(it.context)
//
////        studentVm()
//
////        noteViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)
////        noteViewModel.getAllNoteObservers().observe(this, Observer {
////            adapterNote.setNoteData(it as ArrayList<DataStudent>)
////        })
//
//
//        binding.btnAddStudent.setOnClickListener{
//            findNavController().navigate(R.id.action_homeScreenFragment_to_homeScreenInputDataFragment)
//        }


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedHome = requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)

        var getUser = sharedHome.getString("user","")
        binding.tvWelcome.text = "Welcome, $getUser"

        dbStudent = StudentDataBase.getInstance(requireContext())

        studentVm()
        vmStudent = ViewModelProvider(this).get(StudentViewModel::class.java)

//        vmStudent.getAllNoteObservers().observe(viewLifecycleOwner{
//            adapterNote.setNoteData(it as ArrayList<DataStudent>)
//        })
        vmStudent.getAllStudentObservers().observe(viewLifecycleOwner,{
            adapterStudent.setData(it as ArrayList<DataStudent>)
        })

        binding.btnAddStudent.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_homeScreenFragment_to_homeScreenInputDataFragment)
        }

        binding.tvLogout.setOnClickListener{
            var pref = sharedHome.edit()
            pref.clear()
            pref.clear()
            findNavController().navigate(R.id.action_homeScreenFragment_to_loginFragment)
        }

    }


    //methods
    fun studentVm(){
        adapterStudent = AdapterStudent(ArrayList())
        binding.rvHome.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvHome.adapter = adapterStudent
    }

    fun getAllStudent(){

        GlobalScope.launch {
            var data = dbStudent?.studentDao()?.getDataStudent()

            activity?.runOnUiThread {
                adapterStudent = AdapterStudent(data!!)
                binding.rvHome.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.rvHome.adapter = adapterStudent
            }
        }

    }

    override fun onStart() {
        super.onStart()
        GlobalScope.launch {
            var data =dbStudent?.studentDao()?.getDataStudent()

            activity?.runOnUiThread {
                adapterStudent = AdapterStudent(data!!)
                binding.rvHome.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.rvHome.adapter = adapterStudent
            }
        }
    }

}