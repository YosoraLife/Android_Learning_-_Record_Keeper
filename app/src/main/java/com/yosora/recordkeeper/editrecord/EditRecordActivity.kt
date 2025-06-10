package com.yosora.recordkeeper.editrecord

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.yosora.recordkeeper.databinding.ActivityEditRecordBinding
import java.io.Serializable

const val INTENT_EXTRA_SCREEN_DATA = "screen_data"

class EditRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditRecordBinding

    private val screenData: ScreenData by lazy {
        intent.getSerializableExtra(INTENT_EXTRA_SCREEN_DATA) as ScreenData
    }

    private val recordPreferences by lazy { getSharedPreferences(screenData.sharedPreferencesName,
        Context.MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        binding = ActivityEditRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUi()
        displayRecord()
    }

    private fun setupUi() {
        title = "${screenData.record} Record"
        binding.textInputRecord.hint = screenData.recordFieldHint
        binding.buttonSave.setOnClickListener {
            saveRecord()
            finish()
        }
        binding.buttonDelete.setOnClickListener {
            clearRecord()
            finish()
        }
    }

    private fun displayRecord() {
        val runningPreferences = getSharedPreferences(screenData.sharedPreferencesName, MODE_PRIVATE)

        binding.editTextRecord.setText(runningPreferences.getString("${screenData.record} $SHARED_PREFERENCE_RECORD_KEY", null))
        binding.editTextDate.setText(runningPreferences.getString("${screenData.record} $SHARED_PREFERENCE_DATE_KEY", null))
    }

    private fun saveRecord() {
        val record = binding.editTextRecord.text.toString()
        val date = binding.editTextDate.text.toString()

        val runningPreferences = getSharedPreferences(screenData.sharedPreferencesName, MODE_PRIVATE)

        runningPreferences.edit {
            putString("${this@EditRecordActivity.screenData.record} $SHARED_PREFERENCE_RECORD_KEY", record)
            putString("${this@EditRecordActivity.screenData.record} $SHARED_PREFERENCE_DATE_KEY", date)
        }
    }

    private fun clearRecord() {
        recordPreferences.edit {
            remove("${screenData.record} record")
            remove("${screenData.record} date")
        }
    }

    data class ScreenData(
        val record: String,
        val sharedPreferencesName: String,
        val recordFieldHint: String
    ): Serializable

    companion object {

        const val SHARED_PREFERENCE_RECORD_KEY = "record"
        const val SHARED_PREFERENCE_DATE_KEY = "date"

    }
}

