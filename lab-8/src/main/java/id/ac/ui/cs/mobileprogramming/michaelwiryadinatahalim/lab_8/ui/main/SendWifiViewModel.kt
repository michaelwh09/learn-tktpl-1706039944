package id.ac.ui.cs.mobileprogramming.michaelwiryadinatahalim.lab_8.ui.main

import android.net.wifi.ScanResult
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.ac.ui.cs.mobileprogramming.michaelwiryadinatahalim.lab_8.model.Message
import id.ac.ui.cs.mobileprogramming.michaelwiryadinatahalim.lab_8.model.Response
import id.ac.ui.cs.mobileprogramming.michaelwiryadinatahalim.lab_8.model.Wifi
import id.ac.ui.cs.mobileprogramming.michaelwiryadinatahalim.lab_8.service.PipeDreamService
import id.ac.ui.cs.mobileprogramming.michaelwiryadinatahalim.lab_8.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.await

class SendWifiViewModel @ViewModelInject constructor(private val pipeDreamService: PipeDreamService): ViewModel() {
    private val _responseValue: MutableLiveData<State<Response>> = MutableLiveData()
    val responseValue:LiveData<State<Response>> = _responseValue

    init {
        _responseValue.postValue(State.loading())
    }

    fun sendWifi(wifis: List<ScanResult>) {
        viewModelScope.launch(Dispatchers.IO) {
            val datas: List<Wifi> = wifis.map { Wifi(it.SSID, it.BSSID, it.capabilities) }
            _responseValue.postValue(State.loading())
            val message = Message(datas)
            try {
                val resp = pipeDreamService.sendWifi(message).await()
                _responseValue.postValue(State.success(resp))
            } catch (e: Exception) {
                Log.e("VM", e.message.toString())
                _responseValue.postValue(State.failed())
            }
        }
    }
}
