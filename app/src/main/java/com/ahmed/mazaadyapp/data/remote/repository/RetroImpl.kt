package com.ahmed.mazaadyapp.data.remote.repository

import com.ahmed.mazaadyapp.data.remote.RetroService
import com.ahmed.mazaadyapp.data.remote.dto.CategoriesDTO
import com.ahmed.mazaadyapp.data.remote.dto.OptionsDTO
import com.ahmed.mazaadyapp.data.remote.dto.PropertiesDTO
import com.ahmed.mazaadyapp.domain.repository.RetroRepo
import javax.inject.Inject

class RetroImpl@Inject constructor(
    private val retroService: RetroService
): RetroRepo {
    override suspend fun getCategories(): CategoriesDTO {
        return retroService.getCategories()
    }

    override suspend fun getProperties(id: Int): PropertiesDTO {
        return retroService.getProperties(id)
    }

    override suspend fun getOptions(id: Int): OptionsDTO {
        return retroService.getOptions(id)
    }
}