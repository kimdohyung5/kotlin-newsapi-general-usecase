package com.example.mynewsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.mynewsapp.R
import com.example.mynewsapp.data.local.NewsDatabase
import com.example.mynewsapp.databinding.ActivityMainBinding
import com.example.mynewsapp.repository.NewsRepository

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        val repository = NewsRepository( NewsDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(application, repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory) [NewsViewModel::class.java]

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    companion object{
        const val TAG = "MainActivity"
    }
}