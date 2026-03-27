package com.example.historyechoesview.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.drake.net.Get
import com.drake.net.utils.scopeNetLife
import com.example.historyechoesview.databinding.ActivityHistoryEchoesDetailBinding
import com.example.historyechoesview.model.HistoryEchoesDetailModel
import com.example.historyechoesview.network.Api

class HistoryEchoesDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryEchoesDetailBinding
    private lateinit var id: String
    companion object{
        fun start(context: Context, id: String){
            val intent = Intent(context, HistoryEchoesDetailActivity::class.java)
            intent.putExtra("id", id)
            context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryEchoesDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        id = intent.getStringExtra("id")!!
        scopeNetLife {
            val model = Get<HistoryEchoesDetailModel>(Api.HISTORY_ECHOES_DETAIL){
                param("id", id)
            }.await()
            binding.tvYear.text = model.data.year.toString()
            binding.tvDate.text = "${model.data.month}月${model.data.day}日"
            binding.tvTitle.text = model.data.title
            binding.tvContent.text = model.data.content
        }
    }
}