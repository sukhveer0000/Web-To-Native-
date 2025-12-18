package com.kabir.webview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kabir.webview.databinding.ItemImageBinding

class ViewPagerAdapter(private val images: List<Int>): RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(ItemImageBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val currentImg = images[position]
        holder.binding.imageView.setImageResource(currentImg)
    }

    override fun getItemCount() = images.size


    inner class ViewHolder(val binding: ItemImageBinding): RecyclerView.ViewHolder(binding.root)
}