package id.ac.ui.cs.mobileprogramming.michaelwiryadinatahalim.lab_8.ui.main

import android.net.wifi.ScanResult
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.ac.ui.cs.mobileprogramming.michaelwiryadinatahalim.lab_8.R
import id.ac.ui.cs.mobileprogramming.michaelwiryadinatahalim.lab_8.utils.RecyclerViewOnClickListener
import kotlinx.android.synthetic.main.main_fragment.*

@AndroidEntryPoint
class MainFragment : Fragment(), RecyclerViewOnClickListener<ScanResult> {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by activityViewModels()
    private val sendViewModel: SendWifiViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.wifiResults.observe(viewLifecycleOwner, { wifi ->
            val adapter = ScanResultsAdapter(wifi, this)
            rv_main.layoutManager = LinearLayoutManager(requireContext())
            rv_main.adapter = adapter
        })
    }

    override fun onItemClicked(view: View, data: ScanResult) {
        TODO("Not yet implemented")
    }
}
