package com.example.plantkeeper.ui.addplant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.plantkeeper.R
import com.example.plantkeeper.ui.validation.AddPlantValidator
import com.example.plantkeeper.ui.validation.ValidatedField
import com.example.plantkeeper.utils.addOnTextChanged
import kotlinx.android.synthetic.main.fragment_add_plant.*
import kotlinx.android.synthetic.main.item_plant.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddPlantFragment : Fragment() {

    private val addPlantViewModel by viewModel<AddPlantViewModel>()
    private val addPlantValidator by inject<AddPlantValidator>()

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
        setupInputsChangeListeners()
        setupSavePlantFab()
    }

    private fun setupInputsChangeListeners() {
        new_plant_name_edit_text.addOnTextChanged {
            addPlantViewModel.plantName = it
            new_plant_edit_text_wrapper.error = null
        }
        watering_frequency_edit_text.addOnTextChanged {
            addPlantViewModel.wateringFrequency = it.toInt()
            watering_frequency_edit_text_wrapper.error = null
        }
    }

    private fun setupSavePlantFab() {
        save_plant_fab_menu_image_view.setOnClickListener { onAddNewPlantClick() }
    }

    private fun onAddNewPlantClick() {
        val missingNewPlantInfo = addPlantValidator.getNewPlantMissingInfo(
            addPlantViewModel.plantName,
            addPlantViewModel.wateringFrequency
        )
        if (missingNewPlantInfo.isEmpty()) {
            insertNewPlant()
        } else {
            setErrors(missingNewPlantInfo)
        }
    }

    private fun insertNewPlant() {
        addPlantViewModel.insertPlant {
            findNavController().navigate(R.id.action_navigate_back_to_plants)
        }
    }

    private fun setErrors(missingInfo: List<ValidatedField>) {
        if (missingInfo.contains(ValidatedField.NAME)) {
            new_plant_edit_text_wrapper.error = getString(R.string.missing_info)
        }
        if (missingInfo.contains(ValidatedField.WATERING_FREQUENCY)) {
            watering_frequency_edit_text_wrapper.error = getString(R.string.missing_info)
        }
    }
}