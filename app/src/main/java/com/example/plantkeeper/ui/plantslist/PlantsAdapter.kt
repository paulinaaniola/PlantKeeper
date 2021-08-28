package com.example.plantkeeper.ui.plantslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plantkeeper.R
import com.example.plantkeeper.domain.Plant

class PlantsAdapter : RecyclerView.Adapter<PlantsAdapter.PlantViewHolder>() {

    private val plants = mutableListOf<PlantViewState>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_plant, parent, false)
        return PlantViewHolder(view)
    }

    override fun getItemCount(): Int {
        return plants.size
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val plant = plants[position]
        holder.plantNameTextView.text = plant.name
        holder.wateringFrequencyTextView.text = plant.wateringLabel
    }

    fun updatePlants(plants: List<PlantViewState>) {
        this.plants.clear()
        this.plants.addAll(plants)
        notifyDataSetChanged()
    }

    class PlantViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val plantNameTextView: TextView = view.findViewById(R.id.plant_name_text_view)
        val wateringFrequencyTextView: TextView =
            view.findViewById(R.id.watering_frequency_text_view)
    }
}