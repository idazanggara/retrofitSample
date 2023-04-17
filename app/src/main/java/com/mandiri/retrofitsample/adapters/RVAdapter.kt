package com.mandiri.retrofitsample.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mandiri.retrofitsample.api_network_remote.response.Data
import com.mandiri.retrofitsample.api_network_remote.response.DataItem
import com.mandiri.retrofitsample.databinding.ItemsMainBinding

class RVAdapter(private val dataList: ArrayList<DataItem>,private val listener: onAdapterListener): RecyclerView.Adapter<RVAdapter.MyViewHolder>() {

    fun setData(list: List<DataItem>){
        this.dataList.clear()
        this.dataList.addAll(list)
        notifyDataSetChanged()
    }

    inner class MyViewHolder(val binding: ItemsMainBinding): RecyclerView.ViewHolder(binding.root){
//        val tvName = view.findViewById<TextView>(R.id.nameTextView)
//        val tvEmail = view.findViewById<TextView>(R.id.emailTextView)
//        val iVAvatar = view.findViewById<ImageView>(R.id.avatarImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.items_main,parent,false  )
//        return MyViewHolder(layoutInflater)
        val binding = ItemsMainBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.tvName.text = dataList.get(position).firstName +" "+ dataList.get(position).lastName
//        holder.tvEmail.text = dataList.get(position).email
//        holder.iVAvatar.setImageURI(dataList.get(position).avatar)
        val data = dataList[position]
        with(holder){
            with(dataList[position]){
                binding.apply {
                    nameTextView.text = firstName+" "+last_name
                    emailTextView.text = email
                    Glide.with(itemView).load("${avatar}").into(avatarImageView)
                    cvMain.setOnClickListener {
                        listener.onClick(data)
                    }
                }
            }
        }
    }

    interface  onAdapterListener{
        fun onClick(data: DataItem)
    }
}