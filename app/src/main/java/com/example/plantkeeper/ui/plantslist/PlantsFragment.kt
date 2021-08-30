package com.example.plantkeeper.ui.plantslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plantkeeper.R
import com.example.plantkeeper.databinding.FragmentPlantsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

const val EDIT_PLANT_ID = "edit_plant_id"

class PlantsFragment : Fragment(), PlantItemClickActions {

    private lateinit var binding: FragmentPlantsBinding
    private val plantsViewModel by viewModel<PlantsViewModel>()
    private val plantsAdapter = PlantsAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlantsBinding.inflate(inflater, container, false)
        setupRecyclerView()
        setupAddPlantFab()
        configureDataObservers()
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.plantsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.plantsRecyclerView.adapter = plantsAdapter
    }

    private fun configureDataObservers() {
        plantsViewModel.plantsList.observe(viewLifecycleOwner, Observer { plants ->
            plants?.let {
                setupNoPlantsTextViewVisibility(plants.isEmpty())
                plantsAdapter.updatePlants(it)
            }
        })
    }

    private fun setupNoPlantsTextViewVisibility(isPlantsListEmpty: Boolean) {
        binding.noPlantsTextView.visibility = if (isPlantsListEmpty) View.VISIBLE else View.GONE
    }

    private fun setupAddPlantFab() {
        binding.addPlantFabMenuImageView.setOnClickListener {
            findNavController().navigate(R.id.navigation_add_edit_plant)
        }
    }

    override fun onWateringCanIconClick(plantId: Int) {
        plantsViewModel.updatePlantAsAlreadyWatered(plantId)
    }

    override fun onPlantItemClick(plantId: Int) {
        navigateToEditPlant(plantId)
    }

    private fun navigateToEditPlant(plantId: Int) {
        findNavController().navigate(
            R.id.navigation_add_edit_plant,
            Bundle().apply { putInt(EDIT_PLANT_ID, plantId) }
        )
    }
}