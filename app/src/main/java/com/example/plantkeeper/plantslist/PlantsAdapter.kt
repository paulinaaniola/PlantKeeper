package com.example.plantkeeper.plantslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.plantkeeper.R
import com.example.plantkeeper.data.database.entities.PlantDBO
import com.example.plantkeeper.model.views.PoppinsLightTextView

class PlantsAdapter : RecyclerView.Adapter<PlantsAdapter.PlantViewHolder>() {

    private val plants = mutableListOf<PlantDBO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_plant, parent)
        return PlantViewHolder(view)
    }

    override fun getItemCount(): Int {
        return plants.size
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val plant = plants[position]
        holder.plantNameTextView.text = plant.name
    }

    fun updatePlants(plants: List<PlantDBO>) {
        this.plants.clear()
        this.plants.addAll(plants)
        notifyDataSetChanged()
    }

    class PlantViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val plantNameTextView: PoppinsLightTextView = view.findViewById(R.id.plantNameTextView)
    }
}