package com.angelawang.airconditiondetector.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.angelawang.airconditiondetector.data.model.AirStatus
import com.angelawang.airconditiondetector.databinding.ItemAirStatusBinding

class AirStatusAdapter: RecyclerView.Adapter<AirStatusAdapter.AirStatusViewHolder>() {

    var airStatusList = listOf<AirStatus>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirStatusAdapter.AirStatusViewHolder {
        val binding = ItemAirStatusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AirStatusViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AirStatusAdapter.AirStatusViewHolder, position: Int) {
        holder.bind(airStatusList[position], position)
    }

    override fun getItemCount(): Int {
        return airStatusList.size
    }

    fun setList(list: List<AirStatus>){
        airStatusList = list
        notifyDataSetChanged()
    }

    inner class AirStatusViewHolder(private val binding: ItemAirStatusBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(airStatus: AirStatus, position: Int) {
            binding.apply {
                tvSiteId.text = airStatus.siteId
                tvSiteName.text = airStatus.siteName
                tvCounty.text = airStatus.county
                tvPm25.text = airStatus.pm25
                tvStatus.text = airStatus.status
            }
        }
    }
}