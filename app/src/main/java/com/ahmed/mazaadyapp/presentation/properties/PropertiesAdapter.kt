package com.ahmed.mazaadyapp.presentation.properties

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.ahmed.mazaadyapp.common.Constants
import com.ahmed.mazaadyapp.databinding.PropertiesRecyclerBinding
import com.ahmed.mazaadyapp.domain.models.Properties
import com.ahmed.mazaadyapp.presentation.options.OptionsAdapter
import com.ahmed.mazaadyapp.presentation.options.OptionsViewModel

class PropertiesAdapter(
    private val itemList: List<Properties.Data>,
    private val optionsViewModel: OptionsViewModel,
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<PropertiesAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val binding =
            PropertiesRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int = itemList.size

    override fun getItemViewType(position: Int): Int = position

    override fun getItemId(position: Int): Long = position.toLong()


    inner class ItemViewHolder(private val binding: PropertiesRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: Properties.Data
        ) {
            binding.apply {
                spinChildren.setTitle(item.name)
                inputLayout.hint = item.name + " "
                spinChildren.setItems(item.options.map { it.name }.toTypedArray())
                spinChildren.setOnItemClickListener { index ->
                    if (item.options[index].id == -1) {
                        otherOptionTextLayout.visibility = View.VISIBLE
                        otherOptionText.addTextChangedListener {
                                Constants.arrAllItems[item.name + " ${item.options[index].name}: " ] =
                                    otherOptionText.text.toString()
                        }
                        recyclerOptions.adapter = null
                    } else {
                        otherOptionTextLayout.visibility = View.GONE
                        Constants.arrAllItems[item.name] = item.options[index].name
                        otherOptionText.setText("")
                        optionsViewModel.getOptions(item.options[index].id)
                        var observer: Observer<OptionsViewModel.OptionsState>? = null
                        observer = Observer {
                            if (it.options != null) {
                                recyclerOptions.adapter = OptionsAdapter(
                                    it.options.data,
                                    optionsViewModel,
                                    this@PropertiesAdapter.lifecycleOwner
                                )
                                recyclerOptions.visibility = View.VISIBLE
                                optionsViewModel.state.removeObserver(observer!!)
                            }
                        }
                        optionsViewModel.state.observe(
                            this@PropertiesAdapter.lifecycleOwner,
                            observer
                        )
                    }
                }
            }
        }
    }

}