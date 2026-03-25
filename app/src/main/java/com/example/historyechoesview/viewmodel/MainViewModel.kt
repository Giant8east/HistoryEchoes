package com.example.historyechoesview.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.scopeNetLife
import com.drake.net.Post
import com.example.historyechoesview.model.HistoryEchoes
import com.example.historyechoesview.network.Api

class MainViewModel: ViewModel() {
    val historyEchoesListLiveData = MutableLiveData<List<HistoryEchoes.Data>>()
    fun getHistoryEchoesList(time: String) {
        val pair = extractMonthAndDay(time)
        var month = pair.first
        var day = pair.second
        scopeNetLife {
            val model = Post<HistoryEchoes>(Api.HISTORY_ECHOES) {
                param("month", month)
                param("day", day)
            }.await()
            historyEchoesListLiveData.postValue(model.data)
        }
    }

    private fun extractMonthAndDay(time: String): Pair<Int, Int> {
        val split = time.split("月", "日")
        val month = split[0].toInt()
        val day = split[1].toInt()
        return Pair(month, day)
    }
}