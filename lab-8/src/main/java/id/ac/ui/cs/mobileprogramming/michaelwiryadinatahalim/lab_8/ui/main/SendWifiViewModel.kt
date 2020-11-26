package id.ac.ui.cs.mobileprogramming.michaelwiryadinatahalim.lab_8.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.ac.ui.cs.mobileprogramming.michaelwiryadinatahalim.lab_8.model.Response
import id.ac.ui.cs.mobileprogramming.michaelwiryadinatahalim.lab_8.service.PipeDreamService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.await

class SendWifiViewModel @ViewModelInject constructor(private val pipeDreamService: PipeDreamService): ViewModel() {
    private val _responseValue: MutableLiveData<Response?> = MutableLiveData()
    val responseValue:LiveData<Response?> = _responseValue

    init {
        _responseValue.postValue(null)
    }

    fun sendWifi() {
        viewModelScope.launch(Dispatchers.IO) {
            val resp = pipeDreamService.sendWifi().await()
            _responseValue.postValue(resp)
        }
    }
}
