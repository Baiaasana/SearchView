package com.example.searchview.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs

import com.example.searchview.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private val args: ProfileFragmentArgs by navArgs()

    private var binding: FragmentProfileBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding!!.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.ivUserImage?.setImageResource(args.image)
        binding?.tvUserName?.text = args.name
        binding?.tvUserDescription?.text = args.description


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}