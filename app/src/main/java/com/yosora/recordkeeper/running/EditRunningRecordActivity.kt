package com.yosora.recordkeeper.running

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.yosora.recordkeeper.databinding.ActivityEditRunningRecordBinding

class EditRunningRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditRunningRecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditRunningRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val distance = intent.getStringExtra("Distance")
        title = "$distance Record"

        displayRecord(distance)
        binding.buttonSave.setOnClickListener {
            saveRecord(distance)
            finish()
        }
//        binding.buttonDelete.setOnClickListener {
//          deleteRecord(distance)
//          finish()
//        }

    }

    private fun displayRecord(distance: String?) {
        val runningPreferences = getSharedPreferences("running", Context.MODE_PRIVATE)

        binding.editTextRecord.setText(runningPreferences.getString("$distance record", null))
        binding.editTextDate.setText(runningPreferences.getString("$distance date", null))
    }

    private fun saveRecord(distance: String?) {
        val record = binding.editTextRecord.text.toString()
        val date = binding.editTextDate.text.toString()

        val runningPreferences = getSharedPreferences("running", Context.MODE_PRIVATE)

//        val editor = runningPreferences.edit()
//        editor.putString("$distance record", record)
//        editor.putString("$distance date", date)
//        editor.apply()

        runningPreferences.edit {
            putString("$distance record", record)
            putString("$distance date", date)
        }
    }

//    private fun deleteRecord(distance: String?) {
//        TODO("Not yet implemented")
//    }
}