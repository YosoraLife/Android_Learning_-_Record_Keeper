package com.yosora.recordkeeper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yosora.recordkeeper.databinding.ActivityEditRunningRecordBinding

class EditRunningRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditRunningRecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditRunningRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val distance = intent.getStringExtra("Distance")

        title = "$distance Record"

    }
}