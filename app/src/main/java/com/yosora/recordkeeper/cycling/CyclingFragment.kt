package com.yosora.recordkeeper.cycling

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yosora.recordkeeper.databinding.FragmentCyclingBinding
import com.yosora.recordkeeper.editrecord.EditRecordActivity
import com.yosora.recordkeeper.editrecord.INTENT_EXTRA_SCREEN_DATA

class CyclingFragment : Fragment() {

    private lateinit var binding: FragmentCyclingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCyclingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    override fun onResume() {
        super.onResume()
        displayRecord()
    }

    private fun setupClickListeners() {
        binding.containerLongestRide.setOnClickListener { launchCyclingRecordScreen(RECORD_VALUE_LONGEST, "Distance") }
        binding.containerBiggestClimb.setOnClickListener { launchCyclingRecordScreen(RECORD_VALUE_BIGGEST, "Height") }
        binding.containerAverageSpeed.setOnClickListener { launchCyclingRecordScreen(RECORD_VALUE_BEST, "Average Speed") }
    }

    private fun displayRecord() {
        val cyclingPreferences = requireContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE )

        binding.textViewLongestRideValue.text = cyclingPreferences.getString("$RECORD_VALUE_LONGEST ${EditRecordActivity.SHARED_PREFERENCE_RECORD_KEY}", null)
        binding.textViewLongestRideDate.text = cyclingPreferences.getString("$RECORD_VALUE_LONGEST ${EditRecordActivity.SHARED_PREFERENCE_DATE_KEY}", null)
        binding.textViewBiggestClimbValue.text = cyclingPreferences.getString("$RECORD_VALUE_BIGGEST ${EditRecordActivity.SHARED_PREFERENCE_RECORD_KEY}", null)
        binding.textViewBiggestClimbDate.text = cyclingPreferences.getString("$RECORD_VALUE_BIGGEST ${EditRecordActivity.SHARED_PREFERENCE_DATE_KEY}", null)
        binding.textViewAverageSpeedValue.text = cyclingPreferences.getString("$RECORD_VALUE_BEST ${EditRecordActivity.SHARED_PREFERENCE_RECORD_KEY}", null)
        binding.textViewAverageSpeedDate.text = cyclingPreferences.getString("$RECORD_VALUE_BEST ${EditRecordActivity.SHARED_PREFERENCE_DATE_KEY}", null)
    }

    private fun launchCyclingRecordScreen(record: String, recordFieldHint: String) {
        val intent = Intent(context, EditRecordActivity::class.java)
        intent.putExtra(INTENT_EXTRA_SCREEN_DATA, EditRecordActivity.ScreenData(record, FILENAME, recordFieldHint))
        startActivity(intent)
    }

    companion object {

        const val FILENAME = "cycling"
        const val RECORD_VALUE_LONGEST = "Longest Ride"
        const val RECORD_VALUE_BIGGEST = "Biggest Climb"
        const val RECORD_VALUE_BEST = "Best Average Speed"

    }
}