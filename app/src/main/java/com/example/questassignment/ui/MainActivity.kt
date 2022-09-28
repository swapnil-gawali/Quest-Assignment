package com.example.questassignment.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.questassignment.databinding.ActivityMainBinding
import com.example.questassignment.util.NetworkState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        bindObservers()
    }

    private fun init() = binding.apply {
        btnFetchUser.setOnClickListener {

            val etUserId = tilUserId.editText?.text

            if (etUserId.isNullOrBlank()) {
                Toast.makeText(
                    this@MainActivity,
                    "Please enter user id between 1 to 10",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            viewModel.getUserDetails(etUserId.toString())
        }
    }

    @SuppressLint("SetTextI18n")
    private fun bindObservers() = binding.apply {
        viewModel.userResponse.observe(this@MainActivity) {
            when (it) {
                is NetworkState.Loading -> {
                    pb.visibility = View.VISIBLE
                }
                is NetworkState.Error -> {
                    pb.visibility = View.GONE
                    Toast.makeText(this@MainActivity, it.errorMessage, Toast.LENGTH_LONG).show()
                }
                is NetworkState.Success -> {
                    pb.visibility = View.GONE

                    Toast.makeText(
                        this@MainActivity,
                        "User data fetched successfully",
                        Toast.LENGTH_SHORT
                    ).show()

                    tvUserDetails.text =
                        "Name: ${it.data.name}\nEmail: ${it.data.email}\nPhone: ${it.data.phone}\nWebsite: ${it.data.website}"
                }
            }
        }
    }


}