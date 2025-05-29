package com.yosora.recordkeeper

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yosora.recordkeeper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // binding.bottomNav.setOnNavigationItemSelectedListener(this) -> deprecated
        binding.bottomNav.setOnItemSelectedListener(this)

    }

    private fun onRunningClicked() {
        supportFragmentManager.commit {
            replace(R.id.frame_content, RunningFragment())
        }
    }

    private fun onCyclingClicked() {
        supportFragmentManager.commit {
            replace(R.id.frame_content, CyclingFragment())
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.nav_cycling) {
            onCyclingClicked()
            return true
        } else if (item.itemId == R.id.nav_running) {
            onRunningClicked()
            return true
        } else {
            return false
        }
    }

}