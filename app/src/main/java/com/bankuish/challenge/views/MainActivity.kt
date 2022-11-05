package com.bankuish.challenge.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bankuish.challenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityMainBinding.inflate(this.layoutInflater)
        this.setContentView(binding.root)
   }

}