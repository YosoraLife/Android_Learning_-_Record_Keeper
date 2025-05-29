package com.yosora.recordkeeper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yosora.recordkeeper.databinding.ActivityEditCyclingRecordBinding

class EditCyclingRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditCyclingRecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditCyclingRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val record = intent.getStringExtra("Record")

        title = "$record Record"
    }
}