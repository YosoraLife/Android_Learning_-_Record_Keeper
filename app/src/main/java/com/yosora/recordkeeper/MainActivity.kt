package com.yosora.recordkeeper

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.snackbar.Snackbar
import com.yosora.recordkeeper.cycling.CyclingFragment
import com.yosora.recordkeeper.databinding.ActivityMainBinding
import com.yosora.recordkeeper.running.RunningFragment


class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.setOnItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val menuClickHandled = when (item.itemId) {
            R.id.reset_running -> {
                showConfirmationDialog(RUNNING_DISPLAY_VALUE)
                true
            }
            R.id.reset_cycling -> {
                showConfirmationDialog(CYCLING_DISPLAY_VALUE)
                true
            }
            R.id.reset_all -> {
                showConfirmationDialog(ALL_DISPLAY_VALUE)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

        return menuClickHandled
    }

    private fun showConfirmationDialog(selection: String) {
        AlertDialog.Builder(this)
            .setTitle("Reset $selection records")
            .setMessage("Are you sure you want to clear the records?")
            .setPositiveButton("Yes") { _, _ ->
                when (selection) {
                    ALL_DISPLAY_VALUE -> {
                        getSharedPreferences(RUNNING_DISPLAY_VALUE, MODE_PRIVATE).edit { clear() }
                        getSharedPreferences(CYCLING_DISPLAY_VALUE, MODE_PRIVATE).edit { clear() }
                    }
                    RUNNING_DISPLAY_VALUE -> {
                        getSharedPreferences(RUNNING_DISPLAY_VALUE, MODE_PRIVATE).edit { clear() }
                    }
                    CYCLING_DISPLAY_VALUE -> {
                        getSharedPreferences(CYCLING_DISPLAY_VALUE, MODE_PRIVATE).edit { clear() }
                    }
                }
                refreshCurrentFragment()
                showConfirmation()
            }
            .setNegativeButton("No", null)
            .show()
    }

    private fun showConfirmation() {
        val snackbar = Snackbar.make(
            binding.frameContent,
            "Record cleared successfully!",
            Snackbar.LENGTH_LONG
        )
        snackbar.anchorView = binding.bottomNav
        // snackbar.setAction("Undo") {  }
        snackbar.show()
    }

    private fun refreshCurrentFragment() {
        when (binding.bottomNav.selectedItemId) {
            R.id.nav_running -> onRunningClicked()
            R.id.nav_cycling -> onCyclingClicked()
            else -> {}
        }
    }

    private fun onRunningClicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_content, RunningFragment())
        }
        return true
    }

    private fun onCyclingClicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_content, CyclingFragment())
        }
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.nav_cycling -> onCyclingClicked()
        R.id.nav_running -> onRunningClicked()
        else -> false
    }

    companion object {

        const val RUNNING_DISPLAY_VALUE = "running"
        const val CYCLING_DISPLAY_VALUE = "cycling"
        const val ALL_DISPLAY_VALUE = "all"

    }

}