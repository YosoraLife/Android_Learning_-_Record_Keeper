package com.yosora.recordkeeper

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yosora.recordkeeper.databinding.FragmentRunningBinding

class RunningFragment : Fragment() {

    private lateinit var binding: FragmentRunningBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRunningBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()

    }

    private fun setupClickListeners() {
        binding.container5km.setOnClickListener { launchRunningRecordScreen() }
        binding.container10km.setOnClickListener { launchRunningRecordScreen() }
        binding.containerHalfMarathon.setOnClickListener { launchRunningRecordScreen() }
        binding.containerMarathon.setOnClickListener { launchRunningRecordScreen() }
    }

    private fun launchRunningRecordScreen() {
        val intent = Intent(context, EditRunningRecordActivity::class.java)
        startActivity(intent)
    }

}