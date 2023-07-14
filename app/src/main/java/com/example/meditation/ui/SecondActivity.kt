package com.example.meditation.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.fragment.app.Fragment
import com.example.meditation.viewModel.MainViewModel
import com.example.meditation.R
import com.example.meditation.Service.RunningService
import com.example.meditation.databinding.ActivitySecondBinding


class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private val mainVM by viewModels<MainViewModel>()
    private lateinit var dataStore: DataStore<androidx.datastore.preferences.core.Preferences>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firstFragment = HomeFragment()
        val secondFragment = ProfileFragment()
        val thirdFragment = BadgesFragment()

        //Default fragment
        setCurrentFragment(firstFragment)

        binding.bottomNavigationView.setOnItemSelectedListener{
            Toast.makeText(this, "Your points ${mainVM.progress}", Toast.LENGTH_SHORT).show()
            when(it.itemId){
                R.id.iHome -> setCurrentFragment(firstFragment)
                R.id.iProfile -> setCurrentFragment(secondFragment)
                R.id.iBadge -> setCurrentFragment(thirdFragment)
            }
            true
        }
        Log.d("SecondActivity", "onCreate")
        mainVM.readProgressFromFile(this)
        Intent(applicationContext, RunningService::class.java).also {
            it.action = RunningService.Actions.STOP.toString()
            startService(it)
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("SecondActivity", "onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.d("SecondActivity", "onStop")
        Toast.makeText(this, "SecondActivity points ${mainVM.progress}", Toast.LENGTH_SHORT)
            .show()
        mainVM.saveProgressToFile(this)
        Intent(applicationContext, RunningService::class.java).also {
            it.action = RunningService.Actions.START.toString()
            startService(it)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("SecondActivity", "onDestroy")
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(binding.frameLayout.id, fragment)
            commit()
        }
}