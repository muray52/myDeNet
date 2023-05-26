package com.vostrikov.mydenet.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.vostrikov.mydenet.R
import com.vostrikov.mydenet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: NodesViewModel

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw RuntimeException("ActivityMainBinding is null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[NodesViewModel::class.java]

        onClickButtonListener()
        launchFragment()
    }

    private fun onClickButtonListener() {
        binding.buttonRoot.setOnClickListener {
            viewModel.goToRoot()
        }
        binding.buttonAddChild.setOnClickListener {
            viewModel.addChild()
        }
    }

    private fun launchFragment() {
        val fragment = NodesFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment)
            .commit()
    }
}