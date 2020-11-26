package id.ac.ui.cs.mobileprogramming.michaelwiryadinatahalim.lab_8.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data  class Response (
    @SerialName("success") val status: Boolean
)
