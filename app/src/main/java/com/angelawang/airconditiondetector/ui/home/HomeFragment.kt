package com.angelawang.airconditiondetector.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.angelawang.airconditiondetector.R
import com.angelawang.airconditiondetector.database.AirStatus
import com.angelawang.airconditiondetector.databinding.FragmentHomeBinding
import com.angelawang.airconditiondetector.databinding.FragmentSearchBinding
import com.angelawang.airconditiondetector.ui.AirStatusAdapter
import com.angelawang.airconditiondetector.ui.search.SearchViewModel

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private var adapter: AirStatusAdapter? = null

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val fragmentBinding = FragmentHomeBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        val searchButton: ImageView = fragmentBinding.ivSearch
        searchButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_navigation_search)
        }

        adapter = AirStatusAdapter()
        fragmentBinding.rvHome.adapter = adapter

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.getAllStatus().observe(viewLifecycleOwner, {list ->
            adapter?.setList(list)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}