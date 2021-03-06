package com.angelawang.airconditiondetector.ui

import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.angelawang.airconditiondetector.R
import com.angelawang.airconditiondetector.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private var binding: FragmentSearchBinding? = null
    private var adapter: AirStatusAdapter? = null

    private val airStatusViewModel: AirStatusViewModel by lazy {
        ViewModelProvider(requireActivity()).get(AirStatusViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val fragmentBinding = FragmentSearchBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        adapter = AirStatusAdapter()
        fragmentBinding.rvSearch.adapter = adapter

        fragmentBinding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.etSearch?.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                airStatusViewModel.searchQuery.value = p0.toString()
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        airStatusViewModel.searchResult.observe(viewLifecycleOwner, {list ->
            binding?.apply {
                if (list.isEmpty()) {
                    tvSearchMessage.visibility = VISIBLE
                    val query = airStatusViewModel.searchQuery.value
                    if (query.isEmpty()) {
                        tvSearchMessage.text = getString(R.string.search_hint_message)
                    } else {
                        tvSearchMessage.text = String.format(getString(R.string.search_empty_result_message), query)
                    }
                    titleBar.apply {
                        root.visibility = GONE
                        tvSiteId.typeface = Typeface.DEFAULT_BOLD
                        tvSiteName.typeface = Typeface.DEFAULT_BOLD
                        tvCounty.typeface = Typeface.DEFAULT_BOLD
                        tvPm25.typeface = Typeface.DEFAULT_BOLD
                        tvStatus.typeface = Typeface.DEFAULT_BOLD
                    }
                } else {
                    tvSearchMessage.visibility = GONE
                    titleBar.root.visibility = VISIBLE
                }

            }
            adapter?.setList(list)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        airStatusViewModel.searchQuery.value = ""
        binding = null
    }
}