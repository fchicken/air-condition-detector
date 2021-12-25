package com.angelawang.airconditiondetector.ui

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.angelawang.airconditiondetector.R
import com.angelawang.airconditiondetector.databinding.FragmentHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private var adapter: AirStatusAdapter? = null

    private val airStatusViewModel: AirStatusViewModel by lazy {
        ViewModelProvider(requireActivity()).get(AirStatusViewModel::class.java)
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
        
        airStatusViewModel.getAllStatus().observe(viewLifecycleOwner, {list ->
            if (list.isEmpty()) {
                binding?.pbLoading?.visibility = VISIBLE
            } else {
                binding?.pbLoading?.visibility = GONE
            }
            adapter?.setList(list)
        })

        binding?.titleBar?.apply {
            tvSiteId.typeface = Typeface.DEFAULT_BOLD
            tvSiteName.typeface = Typeface.DEFAULT_BOLD
            tvCounty.typeface = Typeface.DEFAULT_BOLD
            tvPm25.typeface = Typeface.DEFAULT_BOLD
            tvStatus.typeface = Typeface.DEFAULT_BOLD
        }
    }

    override fun onResume() {
        super.onResume()

        // Refetch data from remote every time when fragmnet move to foreground
        GlobalScope.launch(Dispatchers.IO) {
            airStatusViewModel.getAllStatusFromWeb()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}