package com.kabir.webview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kabir.webview.databinding.UrlHistoryCardBinding
import com.kabir.webview.room.WebPage
import java.text.SimpleDateFormat
import java.util.Locale

class UrlHistoryAdapter: RecyclerView.Adapter<UrlHistoryAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: UrlHistoryCardBinding): RecyclerView.ViewHolder(binding.root)

    private var differCallback = object: DiffUtil.ItemCallback<WebPage>(){
        override fun areItemsTheSame(
            oldItem: WebPage,
            newItem: WebPage
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: WebPage,
            newItem: WebPage
        ): Boolean {
            return oldItem == newItem
        }
    }
    private var differ = AsyncListDiffer(this,differCallback)

    fun update(list: MutableList<WebPage>){
        differ.submitList(list)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UrlHistoryAdapter.ViewHolder {
        return ViewHolder(UrlHistoryCardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: UrlHistoryAdapter.ViewHolder, position: Int) {
        val currentItem = differ.currentList[position]

        val time = convertInTime(currentItem.timestamp)
        holder.binding.searchTime.text = time
        holder.binding.searchUrl.text = currentItem.url
    }

    private fun convertInTime(timestamp: Long): String {
        val sdf = SimpleDateFormat("hh:mm a, dd MMM yyyy", Locale.getDefault())
        return  sdf.format(timestamp)
    }

    override fun getItemCount(): Int = differ.currentList.size




}