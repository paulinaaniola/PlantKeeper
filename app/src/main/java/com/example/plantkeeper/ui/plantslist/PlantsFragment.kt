package com.example.plantkeeper.ui.plantslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
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
        configureDataObservers()
        //plantsViewModel.addPlant()
        super.onStart()
    }

    private fun setupRecyclerView() {
        plantsRecyclerView.layoutManager = LinearLayoutManager(context)
        plantsRecyclerView.adapter = plantsAdapter
    }

    private fun configureDataObservers() {
        plantsViewModel.plantsList.observe(this, Observer { plants ->
            plants?.let {
                plantsAdapter.updatePlants(it)
            }
        })
    }
}