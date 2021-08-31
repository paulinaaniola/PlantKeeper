package com.example.plantkeeper.ui.addeditplant

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.plantkeeper.R
import com.example.plantkeeper.utils.validation.AddPlantValidator
import com.example.plantkeeper.utils.validation.ValidatedField
import com.jakubaniola.pickphotoview.PickPhotoActions
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.widget.ArrayAdapter
import android.widget.AdapterView
import androidx.activity.OnBackPressedCallback
import com.example.plantkeeper.databinding.FragmentAddEditPlantBinding
import com.example.plantkeeper.ui.plantslist.EDIT_PLANT_ID
import com.example.plantkeeper.utils.addOnTextChanged

class AddEditPlantFragment : Fragment(), PickPhotoActions {

    private lateinit var binding: FragmentAddEditPlantBinding
    private val addEditPlantViewModel by viewModel<AddEditPlantViewModel>()
    private val addPlantValidator by inject<AddPlantValidator>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddEditPlantBinding.inflate(inflater, container, false)
        setupViewModelObservers()
        setupInputsChangeListeners()
        setupSavePlantFab()
        setupWateringFrequencyUnitsSpinner()
        setupSpinnerValuesChangeListener()
        handleViewState()
        setupOnBackPressed()
        binding.pickPhotoLayout.setPickPhotoFragment(this)
        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        binding.pickPhotoLayout.onPicturePicked(requestCode, resultCode, data)
        val photoPaths = binding.pickPhotoLayout.getPicturePaths()
        if (photoPaths.isNotEmpty()) {
            addEditPlantViewModel.photoPath = photoPaths[0]
        }
    }

    private fun setupInputsChangeListeners() {
        binding.plantNameEditText.addOnTextChanged {
            addEditPlantViewModel.plantName = it
            binding.newPlantEditTextWrapper.error = null
        }
        binding.wateringFrequencyEditText.addOnTextChanged {
            addEditPlantViewModel.wateringFrequencyInput = if (it.isNotEmpty()) it.toInt() else null
            binding.wateringFrequencyEditTextWrapper.error = null
        }
    }

    private fun setupSavePlantFab() {
        binding.savePlantFabMenuImageView.setOnClickListener { onAddNewPlantClick() }
    }

    private fun onAddNewPlantClick() {
        val missingNewPlantInfo = addPlantValidator.getNewPlantMissingInfo(
            addEditPlantViewModel.plantName,
            addEditPlantViewModel.wateringFrequencyInput
        )
        if (missingNewPlantInfo.isEmpty()) {
            onSaveFabClick()
        } else {
            setErrors(missingNewPlantInfo)
        }
    }

    private fun onSaveFabClick() {
        addEditPlantViewModel.onSaveButtonAction()
        findNavController().navigate(R.id.action_navigate_back_to_plants)
    }

    private fun setErrors(missingInfo: List<ValidatedField>) {
        if (missingInfo.contains(ValidatedField.NAME)) {
            binding.newPlantEditTextWrapper.error = getString(R.string.missing_info)
        }
        if (missingInfo.contains(ValidatedField.WATERING_FREQUENCY)) {
            binding.wateringFrequencyEditTextWrapper.error = getString(R.string.missing_info)
        }
    }

    private fun setupSpinnerValuesChangeListener() {
        binding.wateringSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    pos: Int,
                    id: Long
                ) {
                    val item = parent.getItemAtPosition(pos) as String
                    WateringFrequencyUnit.fromString(item)?.let { wateringFrequency ->
                        addEditPlantViewModel.wateringFrequencyUnit = wateringFrequency
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun setupWateringFrequencyUnitsSpinner() {
        val spinnerValues = WateringFrequencyUnit.values().map { it.text }
        context?.let { context ->
            val spinnerArrayAdapter: ArrayAdapter<String> = ArrayAdapter(
                context, android.R.layout.simple_spinner_item,
                spinnerValues
            )
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.wateringSpinner.adapter = spinnerArrayAdapter
        }
    }

    private fun setupViewModelObservers() {
        addEditPlantViewModel.plantToEdit?.observe(viewLifecycleOwner, { plant ->
            plant?.let { plant ->
                binding.plantNameEditText.setText(plant.name)
                binding.wateringFrequencyEditText.setText(
                    plant.wateringFrequency.toDays().toString()
                )
                binding.pickPhotoLayout.setPictures(listOf(plant.photoPath))
            }
        })
    }

    private fun handleViewState() {
        val editPlantId = arguments?.getSerializable(EDIT_PLANT_ID) as? Int
        val viewState = if (editPlantId != null) ViewState.EDIT else ViewState.ADD
        editPlantId?.let { plantId ->
            addEditPlantViewModel.getPlantToEdit(plantId)
        }
        setupDialogTitle(viewState)
        setupDeleteImageView(viewState)
        addEditPlantViewModel.viewState = viewState
    }

    private fun setupDialogTitle(viewState: ViewState) {
        binding.dialogTitleTextView.text =
            if (viewState == ViewState.ADD) getString(R.string.add_new_plant)
            else getString(R.string.edit_plant)
    }

    private fun setupDeleteImageView(viewState: ViewState) {
        if (viewState == ViewState.EDIT) {
            binding.deleteImageView.visibility = View.VISIBLE
            binding.deleteImageView.setOnClickListener {
                binding.deleteAlertDialog.deleteConfirmationLayout.visibility = View.VISIBLE
            }
            setupDeleteConfirmationDialog()
        }
    }

    private fun setupDeleteConfirmationDialog() {
        binding.deleteAlertDialog.cancelTextView.setOnClickListener {
            binding.deleteAlertDialog.deleteConfirmationLayout.visibility = View.GONE
        }
        binding.deleteAlertDialog.deleteTextView.setOnClickListener {
            addEditPlantViewModel.deletePlant()
            findNavController().navigate(R.id.action_navigate_back_to_plants)
        }
    }

    private fun setupOnBackPressed() {
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    onBackPressed()
                }
            }
        )
    }

    private fun onBackPressed() {
        if (binding.deleteAlertDialog.deleteConfirmationLayout.visibility == View.VISIBLE) {
            binding.deleteAlertDialog.deleteConfirmationLayout.visibility = View.GONE
        } else {
            findNavController().popBackStack()
        }
    }
}