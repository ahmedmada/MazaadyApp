package com.ahmed.mazaadyapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ahmed.mazaadyapp.R
import com.ahmed.mazaadyapp.databinding.SecondryFragmentBinding

class SecondaryFragment :Fragment(R.layout.secondry_fragment){

    private val binding: SecondryFragmentBinding by lazy { SecondryFragmentBinding.inflate(layoutInflater) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}