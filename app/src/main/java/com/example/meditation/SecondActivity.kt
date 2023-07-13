package com.example.meditation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.meditation.databinding.ActivitySecondBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


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