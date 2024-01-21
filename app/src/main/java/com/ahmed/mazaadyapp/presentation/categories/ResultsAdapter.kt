package com.ahmed.mazaadyapp.presentation.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmed.mazaadyapp.databinding.RecyclerResultsBinding

class ResultsAdapter(
    private val itemList: HashMap<String, String>,
) :
    RecyclerView.Adapter<ResultsAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val binding =
            RecyclerResultsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItemKey = itemList.keys.elementAt(position)
        val currentItemValue = itemList[currentItemKey]
        holder.bind(currentItemKey,currentItemValue)
    }

    override fun getItemCount(): Int = itemList.size

    override fun getItemViewType(position: Int): Int = position

    override fun getItemId(position: Int): Long = position.toLong()


    inner class ItemViewHolder(private val binding: RecyclerResultsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(key: String,value : String?) {
            binding.apply {
                keyText.text = key
                valText.text = value
            }
        }
    }

}

