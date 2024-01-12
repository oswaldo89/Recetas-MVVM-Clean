package com.recetasyape.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.recetasyape.app.databinding.ActivityMainBinding
import com.recetasyape.app.modules.home.presentation.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            val fragment = HomeFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.nav_host_fragment, fragment)
                .commit()
        }
    }
}