package com.example.plantkeeper.ui.plantslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plantkeeper.R
import com.jakubaniola.pickphotoview.PickPhotoImageUtil

class PlantsAdapter(val itemClickAction: PlantItemClickActions) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<PlantListItem>()
    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (context == null) context = parent.context
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
        setupPlantTexts(plantsViewHolder, plant)
        setupPlantBackgroundClick(plantsViewHolder, plant)
        setupItemWateringCan(plantsViewHolder, plant)
        setupPlantItemBackgroundColor(plantsViewHolder, plant.wateringState)
        setupPlantPhoto(plantsViewHolder, plant.photoPath)
    }

    private fun setupPlantTexts(
        plantsViewHolder: PlantViewHolder,
        plant: PlantViewState,
    ) {
        val watering = plant.wateringFrequency
        plantsViewHolder.plantNameTextView.text = plant.name
        plantsViewHolder.wateringFrequencyTextView.text = context?.resources?.getString(
            watering.wateringFrequencyLabelResource,
            watering.wateringFrequencyValue
        )
    }

    private fun setupPlantBackgroundClick(
        plantsViewHolder: PlantViewHolder,
        plant: PlantViewState
    ) {
        plantsViewHolder.backgroundLayout.setOnClickListener {
            itemClickAction.onPlantItemClick(
                plant.id
            )
        }
    }

    private fun setupItemWateringCan(
        plantsViewHolder: PlantViewHolder,
        plant: PlantViewState
    ) {
        if (plant.wateringState == PlantWateringState.WATERING_REQUIRED) {
            plantsViewHolder.wateringCanImageView.visibility = View.VISIBLE
            plantsViewHolder.wateringCanImageView.setOnClickListener {
                itemClickAction.onWateringCanIconClick(plant.id)
            }
        } else {
            plantsViewHolder.wateringCanImageView.visibility = View.GONE
            plantsViewHolder.wateringCanImageView.setOnClickListener { }
        }
    }

    private fun setupPlantItemBackgroundColor(
        plantsViewHolder: PlantViewHolder,
        wateringState: PlantWateringState
    ) {
        val background = when (wateringState) {
            PlantWateringState.WATERING_REQUIRED -> context?.getDrawable(R.drawable.rounded_frame_pink)
            PlantWateringState.NEAREST_WATERING -> context?.getDrawable(R.drawable.rounded_frame_beige)
            else -> context?.getDrawable(R.drawable.rounded_frame_green)
        }
        plantsViewHolder.backgroundLayout.background = background
    }

    private fun setupPlantPhoto(
        plantsViewHolder: PlantViewHolder,
        photoPath: String
    ) {
        if (photoPath.isNotEmpty()) {
            context?.let { context ->
                val photoBitmap = PickPhotoImageUtil(context).getBitmapFromPath(photoPath, 90f)
                plantsViewHolder.plantPhotoLayout.setImageDrawable(photoBitmap)
            }
        }
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
        val backgroundLayout: View = view.findViewById(R.id.background_layout)
        val wateringFrequencyTextView: TextView =
            view.findViewById(R.id.watering_frequency_text_view)
        val wateringCanImageView: ImageView = view.findViewById(R.id.watering_can_imageView)
        val plantPhotoLayout: ImageView = view.findViewById(R.id.plant_photo_layout)
    }

    class LabelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val wateringCategoryLabelTextView: TextView =
            view.findViewById(R.id.watering_label_text_view)
    }
}