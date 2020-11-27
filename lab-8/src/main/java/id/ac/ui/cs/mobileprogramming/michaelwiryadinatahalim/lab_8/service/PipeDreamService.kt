package id.ac.ui.cs.mobileprogramming.michaelwiryadinatahalim.lab_8.service

import id.ac.ui.cs.mobileprogramming.michaelwiryadinatahalim.lab_8.model.Message
import id.ac.ui.cs.mobileprogramming.michaelwiryadinatahalim.lab_8.model.Response
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PipeDreamService {
    @POST(".")
    fun  sendWifi(@Body message: Message): Call<Response>
}
