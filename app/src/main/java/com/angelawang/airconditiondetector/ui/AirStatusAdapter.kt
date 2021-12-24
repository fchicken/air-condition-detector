package com.angelawang.airconditiondetector.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.angelawang.airconditiondetector.R
import com.angelawang.airconditiondetector.database.AirStatus

class AirStatusAdapter: RecyclerView.Adapter<AirStatusAdapter.AirStatusViewHolder>() {

    var airStatusList = listOf<AirStatus>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirStatusAdapter.AirStatusViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val layout = inflater.inflate(R.layout.item_air_status, parent, false)
        return AirStatusViewHolder(layout)
    }

    override fun onBindViewHolder(holder: AirStatusAdapter.AirStatusViewHolder, position: Int) {
        holder.bind(airStatusList[position])
    }

    override fun getItemCount(): Int {
        return airStatusList.size
    }

    fun setList(list: List<AirStatus>){
        airStatusList = list
        notifyDataSetChanged()
    }

    inner class AirStatusViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvSiteId: TextView = itemView.findViewById(R.id.tv_site_id)
        private val tvSiteName: TextView = itemView.findViewById(R.id.tv_site_name)
        private val tvCounty: TextView = itemView.findViewById(R.id.tv_county)
        private val tvPM25: TextView = itemView.findViewById(R.id.tv_pm25)
        private val tvStatus: TextView = itemView.findViewById(R.id.tv_status)

        fun bind(airStatus: AirStatus) {
            tvSiteId.text = airStatus.siteId
            tvSiteName.text = airStatus.siteName
            tvCounty.text = airStatus.county
            tvPM25.text = airStatus.pm25
            tvStatus.text = airStatus.status
        }
    }
}