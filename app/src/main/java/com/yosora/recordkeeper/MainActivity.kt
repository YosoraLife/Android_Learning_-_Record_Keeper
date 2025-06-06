package com.yosora.recordkeeper

import android.content.Context
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationBarView
import com.yosora.recordkeeper.cycling.CyclingFragment
import com.yosora.recordkeeper.databinding.ActivityMainBinding
import com.yosora.recordkeeper.running.RunningFragment

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
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
                getSharedPreferences("running", Context.MODE_PRIVATE).edit { clear() }
                true
            }

            R.id.reset_cycling -> {
                getSharedPreferences("cycling", Context.MODE_PRIVATE).edit { clear() }
                true
            }

            R.id.reset_all -> {
                getSharedPreferences("running", Context.MODE_PRIVATE).edit { clear() }
                getSharedPreferences("cycling", Context.MODE_PRIVATE).edit { clear() }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

        when (binding.bottomNav.selectedItemId) {
            R.id.nav_running -> onRunningClicked()
            R.id.nav_cycling -> onCyclingClicked()
            else -> {}
        }

        return menuClickHandled
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

}