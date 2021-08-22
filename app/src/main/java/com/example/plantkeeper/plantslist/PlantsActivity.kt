package com.example.plantkeeper.plantslist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plantkeeper.R
import kotlinx.android.synthetic.main.activity_plants.*

class PlantsActivity : AppCompatActivity() {

    private lateinit var plantsViewModel: PlantsViewModel
    private val plantsAdapter = PlantsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plants)
        plantsViewModel = ViewModelProviders.of(this).get(PlantsViewModel::class.java)
        setupRecyclerView()
        configureDataObservers()
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