package com.ahmed.mazaadyapp.presentation.options

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmed.mazaadyapp.common.Resources
import com.ahmed.mazaadyapp.domain.models.Options
import com.ahmed.mazaadyapp.domain.usecases.GetOptionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class OptionsViewModel @Inject constructor(
    private val getOptionsUseCase: GetOptionsUseCase
):ViewModel() {
    private val _state = MutableLiveData<OptionsState>()
    val state:LiveData<OptionsState> = _state

    fun getOptions(id:Int){
        getOptionsUseCase(id).onEach {
            when (it) {
                is Resources.Loading -> {
                    _state.value = OptionsState(isLoading = true)
                }

                is Resources.Success -> {
                    _state.value = OptionsState(options = it.data!!)
                }

                is Resources.Error -> {
                    _state.value = OptionsState(error = it.message)
                }
            }
        }.launchIn(viewModelScope)
    }

    data class OptionsState(
        val isLoading:Boolean = false,
        val options: Options? = null,
        val error:String? = null
    )
}