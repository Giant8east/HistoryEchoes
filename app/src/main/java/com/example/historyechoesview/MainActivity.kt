package com.example.historyechoesview

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.historyechoesview.databinding.ActivityMainBinding
import com.example.historyechoesview.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val simpleDateFormat = SimpleDateFormat("MM月dd日", Locale.getDefault())
        val currentDate = simpleDateFormat.format(Date())
        binding.tvDate.text = currentDate

        mainViewModel.getHistoryEchoesList(binding.tvDate.text.toString())
        mainViewModel.historyEchoesListLiveData.observe(this){
            Log.i("TAG", "it: ${it}")
        }
    }
}