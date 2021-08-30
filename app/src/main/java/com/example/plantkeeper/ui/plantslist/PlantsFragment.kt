package com.example.plantkeeper.ui.plantslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plantkeeper.R
import com.example.plantkeeper.databinding.FragmentPlantsBinding
import com.example.plantkeeper.ui.plantslist.mapper.PlantsViewStateMapper
import com.example.plantkeeper.utils.sorting.PlantSortingUtil
import org.koin.android.ext.android.bind
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.java.KoinJavaComponent

class PlantsFragment : Fragment() {

    private lateinit var binding: FragmentPlantsBinding
    private val plantsViewModel by viewModel<PlantsViewModel>()
    private val plantsAdapter = PlantsAdapter { onWatteringCanImageViewClick(it) }

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
        plantsViewModel.plantsList.observe(this, Observer { plants ->
            plants?.let {
                setupNoPlantsTextViewVisibility(plants.isEmpty())
                plantsAdapter.updatePlants(it)
            }
        })
    }

    private fun setupAddPlantFab() {
        binding.addPlantFabMenuImageView.setOnClickListener {
            findNavController().navigate(R.id.navigation_add_plant)
        }
    }

    private fun onWatteringCanImageViewClick(plantId: Int) {
        plantsViewModel.updatePlantAsAlreadyWatered(plantId)
    }

    private fun setupNoPlantsTextViewVisibility(isPlantsListEmpty: Boolean) {
        binding.noPlantsTextView.visibility = if (isPlantsListEmpty) View.VISIBLE else View.GONE
    }
}