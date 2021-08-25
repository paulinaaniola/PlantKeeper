package com.example.plantkeeper.plantslist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plantkeeper.R
import kotlinx.android.synthetic.main.activity_plants.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlantsActivity : AppCompatActivity() {

    private val plantsViewModel by viewModel<PlantsViewModel>()
    private val plantsAdapter = PlantsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plants)
        setupRecyclerView()
        configureDataObservers()
    }

    override fun onStart() {
        //plantsViewModel.addPlant()
        super.onStart()
    }

    private fun setupRecyclerView() {
        plantsRecyclerView.layoutManager = LinearLayoutManager(this)
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