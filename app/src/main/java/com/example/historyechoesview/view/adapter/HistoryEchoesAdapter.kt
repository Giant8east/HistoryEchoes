package com.example.historyechoesview.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import com.example.historyechoesview.databinding.ItemHistoryEchoesBinding
import com.example.historyechoesview.model.HistoryEchoes
import okhttp3.MediaType

class HistoryEchoesAdapter: RecyclerView.Adapter<HistoryEchoesAdapter.ViewHolder>() {
    var mData: List<HistoryEchoes.Data> = mutableListOf()
        set(value){
            field = value
            notifyDataSetChanged()
        }
    private var mOnItemHistoryEchoesClickListener:(view: View, model: HistoryEchoes.Data)-> Unit =
        {_,_ ->}
    fun setOnItemHistoryEchoesClickListener(onItemHistoryEchoesClickListener: (view: View, model: HistoryEchoes.Data)-> Unit) {
        mOnItemHistoryEchoesClickListener = onItemHistoryEchoesClickListener
    }
    inner class ViewHolder(private val binding: ItemHistoryEchoesBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(model: HistoryEchoes.Data) {
            binding.tvYear.text = model.year.toString()
            binding.tvTitle.text = model.title
            binding.root.setOnClickListener {
                mOnItemHistoryEchoesClickListener.invoke(it, model)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHistoryEchoesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = mData[position]
        holder.bindData(model)
    }

    override fun getItemCount() = mData.size


}