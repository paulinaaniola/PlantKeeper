package com.example.plantkeeper.ui.validation

class AddPlantValidator {

    fun getNewPlantMissingInfo(plantName: String, wateringFrequency: Int?): List<ValidatedField> {
        val missingInfo = mutableListOf<ValidatedField>()
        if (plantName.isEmpty()) missingInfo.add(ValidatedField.NAME)
        if (wateringFrequency == null) missingInfo.add(ValidatedField.WATERING_FREQUENCY)
        return missingInfo
    }
}