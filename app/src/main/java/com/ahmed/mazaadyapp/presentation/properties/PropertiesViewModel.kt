package com.ahmed.mazaadyapp.presentation.properties

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmed.mazaadyapp.common.Resources
import com.ahmed.mazaadyapp.domain.models.Properties
import com.ahmed.mazaadyapp.domain.usecases.GetPropertiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class PropertiesViewModel @Inject constructor(
    private val getPropertiesUseCase: GetPropertiesUseCase
):ViewModel() {
    private val _state = MutableLiveData<PropertiesState>()
    val state:LiveData<PropertiesState> = _state

    fun getProperties(id:Int){
        getPropertiesUseCase(id).onEach {
            when (it) {
                is Resources.Loading -> {
                    _state.value = PropertiesState(isLoading = true)
                }

                is Resources.Success -> {
                    _state.value = PropertiesState(properties = it.data!!)
                }

                is Resources.Error -> {
                    _state.value = PropertiesState(error = it.message)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun resetData(){
        _state.value = PropertiesState()
    }
    data class PropertiesState(
        val isLoading:Boolean = false,
        val properties: Properties? = null,
        val error:String? = null
    )
}