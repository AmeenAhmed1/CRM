package com.github.ameen.crm.presentation.home

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ameen.crm.databinding.ActivityMainBinding
import com.github.ameen.crm.domain.model.CustomerDomain
import com.github.ameen.crm.presentation.DataState
import com.github.ameen.crm.presentation.dialog.AddCustomerDialog
import com.github.ameen.crm.presentation.hide
import com.github.ameen.crm.presentation.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private val binding
        get() = _binding

    private val mainViewModel: MainViewModel by viewModels()

    private val adapter by lazy { CustomerAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleClicks()
        initAdapter()
        getAllCustomer()
    }

    private fun handleClicks() {
        binding.addCustomerButton.setOnClickListener {
            AddCustomerDialog {
                saveUserData(it)
            }.show(supportFragmentManager, "Customer Dialog")
        }
    }

    private fun saveUserData(customerDomain: CustomerDomain) {
        lifecycleScope.launchWhenResumed {
            mainViewModel.addNewCustomer(customerDomain).collectLatest {
                when (it) {
                    is DataState.Loading -> {
                        binding.progress.show()
                    }
                    is DataState.Success -> {
                        binding.progress.hide()
                        Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
                    }
                    is DataState.Error -> {}
                }
            }
        }
    }

    private fun getAllCustomer() {
        lifecycleScope.launchWhenResumed {
            mainViewModel.getAllCustomer().collectLatest {
                when (it) {
                    is DataState.Loading -> {
                        binding.progress.show()
                    }
                    is DataState.Success -> {
                        binding.progress.hide()
                        adapter.diff.submitList(it.data)
                    }
                    is DataState.Error -> {}
                }
            }
        }
    }

    private fun initAdapter() {
        binding.customerRecycler.adapter = adapter
        binding.customerRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

}