package id.ac.ui.cs.mobileprogramming.michaelwiryadinatahalim.lab_8.ui.main

import android.content.Context
import android.net.wifi.ScanResult
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import id.ac.ui.cs.mobileprogramming.michaelwiryadinatahalim.lab_8.data.WifiListLiveData

class MainViewModel @ViewModelInject constructor(@ApplicationContext application: Context) : ViewModel() {
    val wifiResults: LiveData<List<ScanResult>> = WifiListLiveData.get(application)
}
