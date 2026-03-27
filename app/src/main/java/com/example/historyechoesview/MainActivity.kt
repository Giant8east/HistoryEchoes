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
import com.example.historyechoesview.view.activity.HistoryEchoesDetailActivity
import com.example.historyechoesview.view.adapter.HistoryEchoesAdapter
import com.example.historyechoesview.viewmodel.MainViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private val adapter = HistoryEchoesAdapter()
    private var selectedDateTime = System.currentTimeMillis()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val simpleDateFormat = SimpleDateFormat("MM月dd日", Locale.getDefault())
        val currentDate = simpleDateFormat.format(Date())
        binding.tvDate.text = currentDate
        adapter.setOnItemHistoryEchoesClickListener { view, model ->
            HistoryEchoesDetailActivity.start(this, model.id)
        }
        binding.rvHistoryEchoes.adapter = adapter
        mainViewModel.getHistoryEchoesList(binding.tvDate.text.toString())
        mainViewModel.historyEchoesListLiveData.observe(this){
            Log.i("TAG", "it: ${it}")
            adapter.mData = it
        }
        binding.tvDate.setOnClickListener {
            showDatePickerDialog()
        }
    }

    fun showDatePickerDialog() {
        val picker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("选择日期")
            .setSelection(selectedDateTime)
            .build()
        picker.addOnPositiveButtonClickListener {

            val simpleDateFormat = SimpleDateFormat("MM月dd日", Locale.getDefault())
            val selectDate = simpleDateFormat.format(Date(it))
            mainViewModel.getHistoryEchoesList(selectDate)
            binding.tvDate.text = selectDate
            selectedDateTime = it
        }
        picker.show(supportFragmentManager, "DATE_PICKER")
    }
}