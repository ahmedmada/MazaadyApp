package com.ahmed.mazaadyapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ahmed.mazaadyapp.R
import com.ahmed.mazaadyapp.common.Constants
import com.ahmed.mazaadyapp.common.Utils
import com.ahmed.mazaadyapp.databinding.MainFragmentBinding
import com.ahmed.mazaadyapp.domain.models.AllCategories
import com.ahmed.mazaadyapp.presentation.categories.CategoriesViewModel
import com.ahmed.mazaadyapp.presentation.options.OptionsViewModel
import com.ahmed.mazaadyapp.presentation.properties.PropertiesAdapter
import com.ahmed.mazaadyapp.presentation.properties.PropertiesViewModel

class MainFragment : Fragment(R.layout.main_fragment) {

    private val binding: MainFragmentBinding by lazy { MainFragmentBinding.inflate(layoutInflater) }


    private val categoriesViewModel: CategoriesViewModel by activityViewModels()
    private val propertiesViewModel: PropertiesViewModel by activityViewModels()
    private val optionsViewModel: OptionsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var allCategories = AllCategories(emptyList())
        var subCategories = emptyList<AllCategories.Category.Children>()
        val actionSecondary = MainFragmentDirections.actionMainFragmentToSecondaryFragment()


        categoriesViewModel.state.observe(viewLifecycleOwner) { state ->
            binding.loadingMain.visibility = if (state.isLoading) View.VISIBLE else View.INVISIBLE
            if (state.categories != null) {
                allCategories = state.categories
                binding.spinCategories.setItems(allCategories.categories.map { it.name }
                    .toTypedArray())
            }

            if (state.error != null) {
                Utils.showErrorDialog(
                    state.error,
                    requireContext()
                ) { categoriesViewModel.getCategories() }
            }
        }

        binding.spinCategories.setOnItemClickListener { index ->
            subCategories = allCategories.categories[index].children
            Constants.arrAllItems["التصنيف"] = allCategories.categories[index].name
            binding.spinSubCategories.apply {
                clear()
                setText("التصنيف الفرعي")
                setItems(subCategories.map { it.name }.toTypedArray())
            }
        }

        binding.spinSubCategories.setOnItemClickListener { index ->
            Constants.arrAllItems.clear()
            Constants.arrAllItems["التصنيف"] = allCategories.categories[index].name
            Constants.arrAllItems["التصنيف الفرعي"] = subCategories[index].name
            propertiesViewModel.getProperties(subCategories[index].id)
        }

        propertiesViewModel.state.observe(viewLifecycleOwner) { state ->
            binding.loadingMain.visibility = if (state.isLoading) View.VISIBLE else View.INVISIBLE
            binding.recyclerChildren.adapter =
                state.properties?.data?.let { PropertiesAdapter(it, optionsViewModel, this) }
            if (state.error != null) {
                Utils.showErrorDialog(
                    state.error,
                    requireContext()
                ) { propertiesViewModel.getProperties(binding.spinSubCategories.id) }
            }
        }


        optionsViewModel.state.observe(viewLifecycleOwner) { state ->
            binding.loadingMain.visibility = if (state.isLoading) View.VISIBLE else View.INVISIBLE
        }

        binding.btnSecondary.setOnClickListener {
            findNavController().navigate(actionSecondary)
        }
        return binding.root
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Constants.arrAllItems.clear()
        binding.apply {
            spinCategories.clear()
            spinSubCategories.clear()
            spinCategories.setItems(emptyArray<String>())
            spinSubCategories.setItems(emptyArray<String>())
        }
        propertiesViewModel.resetData()
    }
}