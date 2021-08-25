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
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.fragment_plants.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlantsFragment : Fragment() {

    private val plantsViewModel by viewModel<PlantsViewModel>()
    private val plantsAdapter = PlantsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_plants, container, false)
    }

    override fun onStart() {
        setupRecyclerView()
        setupAddPlantFab()
        configureDataObservers()
        super.onStart()
    }

    private fun setupRecyclerView() {
        plants_recycler_view.layoutManager = LinearLayoutManager(context)
        plants_recycler_view.adapter = plantsAdapter
    }

    private fun configureDataObservers() {
        plantsViewModel.plantsList.observe(this, Observer { plants ->
            plants?.let {
                plantsAdapter.updatePlants(it)
            }
        })
    }

    private fun setupAddPlantFab() {
        add_plant_fab_menu_image_view.setOnClickListener {
            findNavController().navigate(R.id.navigation_add_plant)
        }
    }
}