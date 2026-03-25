package com.example.historyechoesview

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.historyechoesview.databinding.ActivityMainBinding
import com.example.historyechoesview.view.adapter.HistoryEchoesAdapter
import com.example.historyechoesview.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private val adapter = HistoryEchoesAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val simpleDateFormat = SimpleDateFormat("MM月dd日", Locale.getDefault())
        val currentDate = simpleDateFormat.format(Date())
        binding.tvDate.text = currentDate
        adapter.setOnItemHistoryEchoesClickListener { view, model ->
            Toast.makeText(this, model.title, Toast.LENGTH_SHORT).show()
        }
        binding.rvHistoryEchoes.adapter = adapter
        mainViewModel.getHistoryEchoesList(binding.tvDate.text.toString())
        mainViewModel.historyEchoesListLiveData.observe(this){
            Log.i("TAG", "it: ${it}")
            adapter.mData = it
        }
    }
}