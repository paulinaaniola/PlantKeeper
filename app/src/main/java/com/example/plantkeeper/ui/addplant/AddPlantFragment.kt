package com.example.plantkeeper.ui.addplant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.plantkeeper.R
import com.example.plantkeeper.databinding.FragmentAddPlantBinding
import com.example.plantkeeper.ui.validation.AddPlantValidator
import com.example.plantkeeper.ui.validation.ValidatedField
import com.example.plantkeeper.utils.addOnTextChanged
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddPlantFragment : Fragment() {

    private lateinit var binding: FragmentAddPlantBinding
    private val addPlantViewModel by viewModel<AddPlantViewModel>()
    private val addPlantValidator by inject<AddPlantValidator>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddPlantBinding.inflate(inflater, container, false)
        setupInputsChangeListeners()
        setupSavePlantFab()
        return binding.root
    }

    private fun setupInputsChangeListeners() {
        binding.newPlantNameEditText.addOnTextChanged {
            addPlantViewModel.plantName = it
            binding.newPlantEditTextWrapper.error = null
        }
        binding.wateringFrequencyEditText.addOnTextChanged {
            addPlantViewModel.wateringFrequency = it.toInt()
            binding.wateringFrequencyEditTextWrapper.error = null
        }
    }

    private fun setupSavePlantFab() {
        binding.savePlantFabMenuImageView.setOnClickListener { onAddNewPlantClick() }
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
            binding.newPlantEditTextWrapper.error = getString(R.string.missing_info)
        }
        if (missingInfo.contains(ValidatedField.WATERING_FREQUENCY)) {
            binding.wateringFrequencyEditTextWrapper.error = getString(R.string.missing_info)
        }
    }
}