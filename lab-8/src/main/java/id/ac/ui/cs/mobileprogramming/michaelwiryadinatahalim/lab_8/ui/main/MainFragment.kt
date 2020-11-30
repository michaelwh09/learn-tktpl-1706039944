package id.ac.ui.cs.mobileprogramming.michaelwiryadinatahalim.lab_8.ui.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import id.ac.ui.cs.mobileprogramming.michaelwiryadinatahalim.lab_8.R
import id.ac.ui.cs.mobileprogramming.michaelwiryadinatahalim.lab_8.utils.State
import kotlinx.android.synthetic.main.main_fragment.*

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by activityViewModels()
    private val sendViewModel: SendWifiViewModel by activityViewModels()
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                listenWifiResult()
            }
        }
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rv_main.isVisible = false
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) -> {
                listenWifiResult()
                onSendResponse()
            }
            else -> {
                requestPermissionLauncher.launch(
                    Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    private fun listenWifiResult() {
        viewModel.wifiResults.observe(viewLifecycleOwner, { wifi ->
            wifi_progress_bar.isVisible = false
            when (wifi) {
                is State.Success -> {
                    rv_main.isVisible = true
                    val adapter = ScanResultsAdapter(wifi.data)
                    rv_main.layoutManager = LinearLayoutManager(requireContext())
                    rv_main.adapter = adapter
                    fab_send.setOnClickListener {
                        sendViewModel.sendWifi(wifi.data)
                    }
                }
                is State.Loading -> wifi_progress_bar.isVisible = true
                is State.Failed -> wifi_error.isVisible = true
            }
        })
    }
    
    private fun onSendResponse() {
        sendViewModel.responseValue.observe(viewLifecycleOwner, {response ->
            when (response) {
                is State.Success -> {
                    if (response.data.status) {
                        Snackbar.make(
                            main, "Pengiriman berhasil",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    } else {
                        Snackbar.make(
                            main, "Data salah",
                            Snackbar.LENGTH_SHORT
                        ).show()

                    }
                }
                is State.Failed -> {
                    Snackbar.make(
                        main, "Pengiriman gagal",
                        Snackbar.LENGTH_SHORT
                    ).show()

                }
                else -> {}
            }
        })
    }
}
