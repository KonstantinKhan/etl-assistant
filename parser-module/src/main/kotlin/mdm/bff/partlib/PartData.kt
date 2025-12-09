package com.khan366kos.mdm.bff.partlib

data class PartData(
    val coatingThickness: String,
    val coating: String,
    val material: String,
    val length: String,
    val threadDiameter: String,
    val wrenchSize: String,
    val threadPitch: String,
    val strengthGrade: String
) {
    fun toFormattedString(): String {
        val materialDisplay = if (material.isEmpty()) "Без указания материала" else material
        return "[$coatingThickness;$coating;$materialDisplay;$length;$threadDiameter;$wrenchSize;$threadPitch;$strengthGrade]"
    }
}
