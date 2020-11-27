package id.ac.ui.cs.mobileprogramming.michaelwiryadinatahalim.lab_8.ui.main

import android.Manifest
import android.content.pm.PackageManager
import android.net.wifi.ScanResult
import android.os.Bundle
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
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import id.ac.ui.cs.mobileprogramming.michaelwiryadinatahalim.lab_8.R
import id.ac.ui.cs.mobileprogramming.michaelwiryadinatahalim.lab_8.utils.RecyclerViewOnClickListener
import id.ac.ui.cs.mobileprogramming.michaelwiryadinatahalim.lab_8.utils.State
import kotlinx.android.synthetic.main.main_fragment.*

@AndroidEntryPoint
class MainFragment : Fragment(), RecyclerViewOnClickListener<ScanResult> {

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
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) -> {
                listenWifiResult()
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
                    val adapter = ScanResultsAdapter(wifi.data, this)
                    rv_main.layoutManager = LinearLayoutManager(requireContext())
                    rv_main.adapter = adapter
                }
                is State.Loading -> wifi_progress_bar.isVisible = true
                is State.Failed -> wifi_error.isVisible = true
            }
        })
    }

    override fun onItemClicked(view: View, data: ScanResult) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Kirim data wifi?")
            .setMessage("SSID: ${data.SSID}, BSSID: ${data.BSSID},CAP: ${data.capabilities}")
            .setPositiveButton("Kirim") {
                    dialog, _ -> dialog.dismiss()
            }
            .setNegativeButton("Tutup") {
                    dialog, _ -> dialog.dismiss()
            }.show()
    }
}
