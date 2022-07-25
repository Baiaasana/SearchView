package com.example.searchview.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.searchview.adapter.UserAdapter
import com.example.searchview.data.User
import com.example.searchview.data.filteredList
import com.example.searchview.data.listOfUsers
import com.example.searchview.databinding.FragmentRecyclerBinding

class RecyclerFragment : Fragment() {

    private var binding: FragmentRecyclerBinding? = null
    private var userAdapter = UserAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        listeners()
    }

    private fun init() {
        binding?.recycler?.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = userAdapter
        }
        userAdapter.submitList(listOfUsers.toList())
    }

    private fun listeners() {
        userAdapter.setOnItemClickListener(object : UserAdapter.OnItemClickListener {
            override fun onItemClick(position: User) {

                val action1 = RecyclerFragmentDirections.actionRecyclerFragmentToProfileFragment(
                    name = position.name,
                    description = position.description,
                    image = position.image)
                findNavController().navigate(action1)
            }
        })

        binding?.searcher?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                userAdapter.filter.filter(charSequence.toString())
                userAdapter.submitList(filteredList.toList())
            }
            override fun afterTextChanged(editable: Editable) {}
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}















































