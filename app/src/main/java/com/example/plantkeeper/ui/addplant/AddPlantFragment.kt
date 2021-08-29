package com.example.plantkeeper.ui.addplant

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.plantkeeper.R
import com.example.plantkeeper.databinding.FragmentAddPlantBinding
import com.example.plantkeeper.utils.validation.AddPlantValidator
import com.example.plantkeeper.utils.validation.ValidatedField
import com.example.plantkeeper.utils.addOnTextChanged
import com.jakubaniola.pickphotoview.PickPhotoActions
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.widget.ArrayAdapter

import android.widget.Spinner
import android.widget.SpinnerAdapter


class AddPlantFragment : Fragment(), PickPhotoActions {

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
        binding.pickPhotoLayout.setPickPhotoFragment(this)
        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        binding.pickPhotoLayout.onPicturePicked(requestCode, resultCode, data)
        val picturePaths = binding.pickPhotoLayout.getPicturePaths()
        if (picturePaths.isNotEmpty()) {
            addPlantViewModel.photoPath = picturePaths[0]
        }
    }

    private fun setupInputsChangeListeners() {
        binding.newPlantNameEditText.addOnTextChanged {
            addPlantViewModel.plantName = it
            binding.newPlantEditTextWrapper.error = null
        }
        binding.wateringFrequencyEditText.addOnTextChanged {
            addPlantViewModel.wateringFrequency = if (it.isNotEmpty()) it.toInt() else null
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