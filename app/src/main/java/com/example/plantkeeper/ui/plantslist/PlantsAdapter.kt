package com.example.plantkeeper.ui.plantslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plantkeeper.R

class PlantsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<PlantListItem>()
    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return if (viewType == PlantListItemType.PLANT.ordinal) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_plant, parent, false)
            PlantViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_watering_label, parent, false)
            LabelViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].itemType.ordinal
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if (item.itemType == PlantListItemType.PLANT) {
            setupPlantItem(holder as PlantViewHolder, item)
        } else {
            setupLabelItem(holder as LabelViewHolder, item)
        }
    }

    private fun setupPlantItem(plantsViewHolder: PlantViewHolder, item: PlantListItem) {
        val plant = (item as PlantItem).plant
        plantsViewHolder.plantNameTextView.text = plant.name
        plantsViewHolder.wateringFrequencyTextView.text = plant.wateringLabel
        plantsViewHolder.wateringCanImageView.visibility =
            if (plant.wateringState == PlantWateringState.WATERING_REQUIRED) View.VISIBLE else View.GONE
    }

    private fun setupLabelItem(labelViewHolder: LabelViewHolder, item: PlantListItem) {
        val label = (item as Label).wateringCategory
        labelViewHolder.wateringCategoryLabelTextView.text = context?.getString(label.resourceId)
    }

    fun updatePlants(items: List<PlantListItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class PlantViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val plantNameTextView: TextView = view.findViewById(R.id.plant_name_text_view)
        val wateringFrequencyTextView: TextView =
            view.findViewById(R.id.watering_frequency_text_view)
        val wateringCanImageView: ImageView = view.findViewById(R.id.watering_can_imageView)
    }

    class LabelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val wateringCategoryLabelTextView: TextView =
            view.findViewById(R.id.watering_label_text_view)
    }
}