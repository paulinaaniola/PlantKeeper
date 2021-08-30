package com.example.plantkeeper.ui.addplant

enum class WateringFrequencyUnit(val text: String) {
    DAYS("Days"),
    WEEKS("Weeks");

    companion object {
        fun fromString(text: String?): WateringFrequencyUnit? {
            return WateringFrequencyUnit.values().firstOrNull { it.text == text }
        }
    }
}