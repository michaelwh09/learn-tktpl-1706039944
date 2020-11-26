package id.ac.ui.cs.mobileprogramming.michaelwiryadinatahalim.lab_8.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import android.util.Log
import androidx.lifecycle.LiveData

class WifiListLiveData(private val context: Context) : LiveData<List<ScanResult>>(){
    val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

    companion object {
        private lateinit var instance: WifiListLiveData
        fun get(context: Context): WifiListLiveData {
            instance = if (::instance.isInitialized) {
                instance
            } else {
                WifiListLiveData(context)
            }
            return instance
        }
    }

    private val wifiScanBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val success = intent?.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false)
            if (success == true) {
                onSuccess()
            }

        }

        private fun onSuccess() {
            val results = wifiManager.scanResults
            value = results
        }

    }

    override fun onActive() {
        super.onActive()
        value = emptyList()
        val intentFilter = IntentFilter()
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        context.registerReceiver(wifiScanBroadcastReceiver, intentFilter)
    }

    override fun onInactive() {
        super.onInactive()
        context.unregisterReceiver(wifiScanBroadcastReceiver)
    }
}
