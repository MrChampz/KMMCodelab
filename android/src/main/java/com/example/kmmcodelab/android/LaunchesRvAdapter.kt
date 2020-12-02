package com.example.kmmcodelab.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.kmmcodelab.shared.entity.RocketLaunch

class LaunchesRvAdapter(var launches: List<RocketLaunch>):
    RecyclerView.Adapter<LaunchesRvAdapter.LaunchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.item_launch, parent, false)
            .run(::LaunchViewHolder)
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        holder.bindData(launches[position])
    }

    override fun getItemCount(): Int = launches.count()

    inner class LaunchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val missionNameTV = itemView.findViewById<TextView>(R.id.missionName)
        private val launchYearTV = itemView.findViewById<TextView>(R.id.launchYear)
        private val launchSuccessTV = itemView.findViewById<TextView>(R.id.launchSuccess)
        private val missionDetailsTV = itemView.findViewById<TextView>(R.id.details)

        fun bindData(launch: RocketLaunch) {
            val ctx = itemView.context

            missionNameTV.text = ctx.getString(R.string.mission_name_field, launch.missionName)
            launchYearTV.text = ctx.getString(R.string.launch_year_field, launch.launchYear.toString())
            missionDetailsTV.text = ctx.getString(R.string.details_field, launch.details)

            val launchSuccess = launch.launchSuccess
            if (launchSuccess != null) {
                if (launchSuccess) {
                    launchSuccessTV.text = ctx.getString(R.string.successful)
                    launchSuccessTV.setTextColor(ContextCompat.getColor(ctx, R.color.colorSuccessful))
                } else {
                    launchSuccessTV.text = ctx.getString(R.string.unsuccessful)
                    launchSuccessTV.setTextColor(ContextCompat.getColor(ctx, R.color.colorUnsuccessful))
                }
            } else {
                launchSuccessTV.text = ctx.getString(R.string.no_data)
                launchSuccessTV.setTextColor(ContextCompat.getColor(ctx, R.color.colorNoData))
            }
        }
    }
}