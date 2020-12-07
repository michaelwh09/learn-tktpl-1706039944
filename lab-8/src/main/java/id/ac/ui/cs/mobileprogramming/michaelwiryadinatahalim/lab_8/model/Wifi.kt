package id.ac.ui.cs.mobileprogramming.michaelwiryadinatahalim.lab_8.model

import kotlinx.serialization.Serializable

@Serializable
data class Wifi (val ssid: String, val bssid: String, val capabilities: String)
