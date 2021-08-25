package com.example.plantkeeper.ui.addplant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.plantkeeper.R
import com.example.plantkeeper.utils.addOnTextChanged
import kotlinx.android.synthetic.main.fragment_add_plant.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddPlantFragment : Fragment() {

    private val addPlantViewModel by viewModel<AddPlantViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_add_plant, container, false)
    }

    override fun onStart() {
        super.onStart()
        setupPlantNameChangeListener()
        setupSavePlantFab()
    }

    private fun setupPlantNameChangeListener() {
        new_plant_name_edit_text.addOnTextChanged {
            addPlantViewModel.plantName = it
        }
    }

    private fun setupSavePlantFab() {
        save_plant_fab_menu_image_view.setOnClickListener {
            addPlantViewModel.insertPlant()
        }
    }
}