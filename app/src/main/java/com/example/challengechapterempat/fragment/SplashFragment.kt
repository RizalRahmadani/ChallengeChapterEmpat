package com.example.challengechapterempat.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.challengechapterempat.R
import com.example.challengechapterempat.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {
    lateinit var binding : FragmentSplashBinding
    lateinit var sharedSplash : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(layoutInflater)
        val view = binding.root

        Handler(Looper.myLooper()!!).postDelayed({

            sharedSplash = requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)

            if(sharedSplash.getString("user", "").equals("")){
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            }else if (sharedSplash.getString("user", "")!!.length !=0){
                findNavController().navigate(R.id.action_splashFragment_to_homeScreenFragment)
            }
        }, 3000)
        return view
    }

}